package com.ethanzyc.commons.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethanzyc.commons.base.constants.Constants;
import com.ethanzyc.commons.base.exception.CustomException;
import com.ethanzyc.commons.base.response.ResultCode;
import com.ethanzyc.commons.base.util.DateUtil;
import com.ethanzyc.commons.base.util.IPUtil;
import com.ethanzyc.commons.base.util.sms.TencentSmsUtil;
import com.ethanzyc.commons.business.domain.Employee;
import com.ethanzyc.commons.business.domain.dbenum.EmployeeDeleteState;
import com.ethanzyc.commons.business.domain.dbenum.EmployeeState;
import com.ethanzyc.commons.business.domain.request.EmployeeRegisterDto;
import com.ethanzyc.commons.business.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author ethan
 * @date 2019/9/20 09:13
 */
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public boolean sendCode(String phone, int expireTime, String randomCode) {
        String redisKey = Constants.RedisPrefix.VERIFY_CODE + phone;
        // 先看redis中有没有，有的话提示，没有的话存一下
        String valInRedis = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotEmpty(valInRedis)) {
            throw new CustomException(ResultCode.FREQUENTLY_SEND);
        }

        String ipAddr = IPUtil.getIpAddr(request);
        log.info("========== 获取注册验证码ip:{} ==========", ipAddr);
        String sendTimeKey = Constants.RedisPrefix.VERIFY_CODE_TIMES + ipAddr;
        String count = redisTemplate.opsForValue().get(sendTimeKey);
        if (count != null && Integer.valueOf(count) > Constants.TimeLimit.REGISTER_SMS_TIMES) {
            throw new CustomException(ResultCode.SEND_SMS_TIMES_TOO_MUCH);
        }

        boolean sendSuccess = TencentSmsUtil.sendVerifyCode(phone, randomCode, String.valueOf(expireTime));
        if (sendSuccess) {
            redisTemplate.opsForValue().set(redisKey, randomCode, expireTime, TimeUnit.MINUTES);
            if (StringUtils.isNotEmpty(ipAddr)) {
                if (count == null) {
                    redisTemplate.opsForValue().set(sendTimeKey, "1", DateUtil.getRemainSecondsOneDay(new Date()), TimeUnit.SECONDS);
                } else {
                    redisTemplate.opsForValue().increment(sendTimeKey);
                }
            }
            return true;
        } else {
            throw new CustomException(ResultCode.SMS_FAIL);
        }
    }

    @Override
    public boolean register(EmployeeRegisterDto registerDto) {
        // 先校验库里有没有这个用户
        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq(Employee.PHONE_NUMBER, registerDto.getPhoneNumber()));
        if (employee != null) {
            throw new CustomException(ResultCode.PHONE_HAS_REGISTER);
        }
        checkVerityCode(registerDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Employee newEmployee = Employee.builder()
                .gender(registerDto.getGender())
                .isActive(EmployeeState.CHECKING.getState())
                .isDelete(EmployeeDeleteState.UN_DELETE.getState())
                .jobCode(registerDto.getJobCode())
                .passWord(encoder.encode(registerDto.getPassWord()))
                .phoneNumber(registerDto.getPhoneNumber())
                .userName(registerDto.getUserName())
                .userImageUrl(registerDto.getUserImageUrl())
                .build();
        boolean save = employeeService.save(newEmployee);
        if (save) {
            return true;
        }
        return false;
    }

    private void checkVerityCode(EmployeeRegisterDto registerDto) {
        String codeKey = Constants.RedisPrefix.VERIFY_CODE + registerDto.getPhoneNumber();
        // 校验验证码
        String codeInRedis = redisTemplate.opsForValue().get(codeKey);
        if (StringUtils.isEmpty(codeInRedis)) {
            throw new CustomException(ResultCode.VERITY_CODE_TIME_OUT);
        }
        if (!StringUtils.equals(registerDto.getVerityCode(), codeInRedis)) {
            throw new CustomException(ResultCode.VERITY_CODE_ERROR);
        }
        redisTemplate.delete(codeKey);
    }
}

package com.ethanzyc.commons.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethanzyc.commons.base.exception.CustomException;
import com.ethanzyc.commons.base.response.Result;
import com.ethanzyc.commons.base.response.ResultCode;
import com.ethanzyc.commons.base.util.CheckUtil;
import com.ethanzyc.commons.base.util.RandomUtil;
import com.ethanzyc.commons.business.domain.Employee;
import com.ethanzyc.commons.business.domain.request.EmployeeRegisterDto;
import com.ethanzyc.commons.business.service.RegisterService;
import com.ethanzyc.commons.business.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ethanzyc.commons.base.response.ResultCode.PHONE_ERROR;
import static com.ethanzyc.commons.base.response.ResultCode.PHONE_HAS_REGISTER;

/**
 * 注册
 *
 * @author ethan
 * @date 2019/9/20 08:42
 */
@RestController
@RequestMapping("register")
@Slf4j
public class RegisterController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private RegisterService registerService;

    /**
     * 获取验证码
     * 1.通过正则校验手机号
     * 2.去库里查是否已注册
     * 3.发送验证码，并返回发送验证码的结果
     *
     * @param phone 注册手机号
     * @return 验证码
     */
    @GetMapping("send_code")
    public Result<String> sendCode(@RequestParam("phone") String phone) {
        // 校验手机号
        boolean checkSuccess = CheckUtil.checkPhone(phone);
        if (!checkSuccess) {
            throw new CustomException(PHONE_ERROR);
        }
        // 校验完成，去库里查询
        Employee employee = employeeService.getOne(new QueryWrapper<Employee>().eq(Employee.PHONE_NUMBER, phone));
        if (employee != null) {
            throw new CustomException(PHONE_HAS_REGISTER);
        }
        int expireTime = 1;
        // 生成随机码
        String randomCode = RandomUtil.randomCode(4);
        boolean sendSuccess = registerService.sendCode(phone, expireTime, randomCode);

        if (sendSuccess) {
            return Result.success(randomCode);
        } else {
            return Result.fail(ResultCode.SMS_FAIL);
        }
    }

    /**
     * 注册
     * @param registerDto
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> registerNewEmployee(@RequestBody @Validated EmployeeRegisterDto registerDto) {
        if (!StringUtils.equals(registerDto.getPassWord(), registerDto.getPassWordAgain())) {
            return Result.fail("前后密码必须保持一致");
        }
        boolean add = registerService.register(registerDto);
        return Result.success(add);
    }


}

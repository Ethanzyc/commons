package com.ethanzyc.commons.business.service;

import com.ethanzyc.commons.business.domain.request.EmployeeRegisterDto;

/**
 * @author ethan
 * @date 2019/9/20 09:13
 */
public interface RegisterService {
    /**
     * 发送验证码
     * @param phone
     * @param expireTime
     * @param randomCode
     * @return
     */
    boolean sendCode(String phone, int expireTime, String randomCode);

    boolean register(EmployeeRegisterDto registerDto);
}

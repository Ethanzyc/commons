package com.ethanzyc.commons.base.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ethan
 * @date 2019/9/20 09:51
 */
@Slf4j
public class CheckUtil {

    /**
     * 校验手机号
     * @param phone
     */
    public static boolean checkPhone(String phone) {
        if (phone.length() != 11) {
            log.error("========== 手机号不是十一位:{} ==========", phone);
            return false;
        } else {
            String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                log.error("========== 您的手机号 {} 是错误格式 ==========", phone);
                return false;
            }
        }
        return true;
    }

}

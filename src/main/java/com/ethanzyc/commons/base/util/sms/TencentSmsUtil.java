package com.ethanzyc.commons.base.util.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ethan
 * @date 2019/9/20 08:02
 */
@Slf4j
public class TencentSmsUtil {

    /**
     * 短信应用SDK AppID 1400开头
     */
    private static final int APP_ID = 1400257706;

    /**
     * 短信应用SDK AppKey
     */
    private static final String APP_KEY = "7d5ecb81e9de5e0e6206e2ba62868a59";

    /**
     * 短信模板ID，需要在短信应用中申请
     */
    private static final int VERIFY_CODE_TEMPLATE_ID = 423323;

    //
    /**
     * 签名
     * NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
     */
    private static final String SMS_SIGN = "常用汇率查询";

    // todo 学完多线程这里加上连接池
    /**
     * 指定模板发送验证码
     * @param phone 发送的手机号
     * @param code  验证码
     * @param expireTime 过期时间，分钟
     */
    public static boolean sendVerifyCode(String phone, String code, String expireTime) {
        String[] params = {code, expireTime};
        SmsSingleSender ssender = new SmsSingleSender(APP_ID, APP_KEY);
        SmsSingleSenderResult result = null;
        try {
            // todo 结果加mongo记录
            log.info("========== 发送验证码参数  phone:{},code:{},expireTime:{}分钟 ==========", phone, code, expireTime);
            result = ssender.sendWithParam("86", phone, VERIFY_CODE_TEMPLATE_ID, params, SMS_SIGN, "", "");
            if (result.result == 0) {
                log.info("========== 发送成功,发送结果:{} ==========", result);
                return true;
            } else {
                log.error("========== 发送失败:{} ==========", result);
            }
        } catch (HTTPException e) {
            log.error("发送验证码时http异常", e);
        } catch (IOException e) {
            log.error("发送验证码时io异常", e);
        }
        return false;
    }
}

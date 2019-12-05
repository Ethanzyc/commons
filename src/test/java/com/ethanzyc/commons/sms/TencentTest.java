package com.ethanzyc.commons.sms;

import cn.hutool.crypto.digest.DigestUtil;
import com.ethanzyc.commons.CommonsApplicationTests;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ethan
 * @date 2019/9/18 08:28
 */
public class TencentTest extends CommonsApplicationTests {

    @Autowired private RestTemplate restTemplate;

    // 短信应用SDK AppID
    int appid = 1400257706; // 1400开头

    // 短信应用SDK AppKey
    String appkey = "7d5ecb81e9de5e0e6206e2ba62868a59";

    // 需要发送短信的手机号码
    String[] phoneNumbers = {"15751000946"};

    // 短信模板ID，需要在短信应用中申请
    int templateId = 423323; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

    // 签名
    String smsSign = "常用汇率查询"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`

    @Test
    public void testSend() {
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[0],
                    "【腾讯云】您的验证码是: 5678", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    @Test
    public void testSendTemplate() {
        try {
            String[] params = {"5678", "1"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    @Test
    public void testSendWithRestTemplate() {
        String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid={sdkappid}&random={random}";
        Map<String, Object> map = new HashMap<>();
        map.put("ext", "");
        map.put("extend", "");
        map.put("params", new String[]{"1111", "1"});
        long l = System.currentTimeMillis()/1000;
        String sig = DigestUtil.sha256Hex("appkey="+appkey+"&random="+123+"&time="+l+"&mobile="+"15751000946");
        map.put("sig", sig);
        map.put("sign", "常用汇率查询");
        Map<String, String> phoneMap = new HashMap<>();
        phoneMap.put("mobile", "15751000946");
        phoneMap.put("nationcode", "86");
        map.put("tel", phoneMap);
        map.put("time", l);
        map.put("tpl_id", templateId);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("sdkappid", String.valueOf(appid));
        map1.put("random", String.valueOf(123));
        HttpEntity<Map> httpEntity = new HttpEntity<>(map);
        Map map2 = restTemplate.postForObject(url, httpEntity, Map.class, map1);
        System.out.println(map2);
    }
}

package com.ethanzyc.commons.rest;

import cn.hutool.core.map.MapUtil;
import com.ethanzyc.commons.CommonsApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author ethan
 * @date 2019/9/18 09:27
 */
public class RestTemplateTest extends CommonsApplicationTests {
    @Autowired
    RestTemplate restTemplate;

    @Test public void testApi() {
        MultiValueMap httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/json");
        httpHeaders.add("test-header", "123");
        Map<String, String> map = MapUtil.of("id", "123");
        HttpEntity<Map> httpEntity = new HttpEntity<>(map, httpHeaders);
        Map<String, String> map1 = MapUtil.of("pa", "ppa");
        String s = restTemplate.postForObject("http://localhost:8088/api/mixed?pa={pa}", httpEntity, String.class, map1);
        System.out.println(s);
    }
}

package com.ethanzyc.commons.business.service;

import com.ethanzyc.commons.business.domain.TTest;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/27 09:34
 */
public interface StartService {

    /**
     * 测试sql查询
     * @param start
     * @param end
     * @return
     */
    List<TTest> find(Integer start, Integer end);

    /**
     * 测试mybatis-plus
     * @param start
     * @param end
     * @return
     */
    List<TTest> findByPlus(Integer start, Integer end);

    /**
     * 测试存redis
     * @param key
     * @param value
     * @return
     */
    boolean testRedis(String key, String value);

    /**
     * 测试取redis
     * @param key
     * @return
     */
    String getRedis(String key);

    /**
     * 测试存取hash
     * @param key
     * @param test
     */
    void testHashRedis(String key, TTest test);

    /**
     * 测试读取hash
     * @param key
     */
    TTest testGetHashRedis(String key);
}

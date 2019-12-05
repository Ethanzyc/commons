package com.ethanzyc.commons.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethanzyc.commons.business.domain.TTest;
import com.ethanzyc.commons.business.mapper.TestMapper;
import com.ethanzyc.commons.business.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/25 12:06
 */
@Service
public class StartServiceImpl implements StartService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, TTest> tTestRedisTemplate;

    @Override
    public List<TTest> find(Integer start, Integer end) {
        return testMapper.findPage(start, end);
    }

    @Override
    public List<TTest> findByPlus(Integer start, Integer end) {
        return testMapper.selectList(new QueryWrapper<TTest>().eq("id", 1000));
    }

    @Override
    public boolean testRedis(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return true;
    }

    @Override
    public String getRedis(String key) {
        String s = stringRedisTemplate.opsForValue().get(key);
        return s;
    }

    @Override
    public void testHashRedis(String key, TTest test) {
        tTestRedisTemplate.opsForValue().set(key, test);
    }

    @Override
    public TTest testGetHashRedis(String key) {
        TTest tTest = tTestRedisTemplate.opsForValue().get(key);
        return tTest;
    }
}

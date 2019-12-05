package com.ethanzyc.commons.redis;

import com.ethanzyc.commons.CommonsApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/28 09:20
 */
public class RedisTest extends CommonsApplicationTests {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("a", "b");
        redisTemplate.opsForHash().put("hash1", "a", "aValue");
        redisTemplate.opsForHash().put("hash1", "b", "bValue");
        testGetHash();
    }

    @Test
    public void testGetHash() {
        Object o = redisTemplate.opsForHash().get("hash1", "a");
        List hash1 = redisTemplate.opsForHash().values("hash1");
        BoundHashOperations hash11 = redisTemplate.boundHashOps("hash1");
        System.out.println();
    }
}

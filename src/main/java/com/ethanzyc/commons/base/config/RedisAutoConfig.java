package com.ethanzyc.commons.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ethan
 * @date 2019/8/28 07:44
 */
@Configuration
public class RedisAutoConfig {

    @Value("${spring.redis.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private Integer port;
    @Value("${spring.redis.password:}")
    private String password;
    @Value("${spring.redis.database:0}")
    private Integer database;

    @Value("${spring.redis.jedis.pool.max-active:8}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-idle:8}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait:-1ms}")
    private String maxWait;
    @Value("${spring.redis.jedis.pool.min-idle:0}")
    private Integer minIdle;

    private static final String NEGATIVE = "-";

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);

        int maxWaitValue;
        String regEx="[^0-9]";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(maxWait);
        maxWaitValue = Integer.valueOf(m.replaceAll("").trim());
        //todo 优化为正则
        if (maxWait.startsWith(NEGATIVE)) {
            maxWaitValue = 0-maxWaitValue;
        }

        jedisPoolConfig.setMaxWaitMillis(maxWaitValue);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        return jedisPoolConfig;
    }

    @Bean
    public JedisClientConfiguration jedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        return builder.usePooling()
                .poolConfig(jedisPoolConfig())
                .build();
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setDatabase(database);
        config.setPassword(RedisPassword.of(password));
        return config;
    }

    /**
     * 设置redis连接工厂
     * @return JedisConnectionFactory
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(redisStandaloneConfiguration(), jedisClientConfiguration());
    }

    /**
     * 序列化设置
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate() {

        RedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //定义连接RedisTemplate,并设置连接工程
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        //设置序列化器
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

        return redisTemplate;
    }
}

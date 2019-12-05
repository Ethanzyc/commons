package com.ethanzyc.commons.base.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 测试 CommandLineRunner Order
 * @author ethan
 * @date 2019/9/2 23:49
 */
@Component
@Order(value=1)
@Slf4j
public class OrderTest1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("========== order 1 ==========");
    }
}

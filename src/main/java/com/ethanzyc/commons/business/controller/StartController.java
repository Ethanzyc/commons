package com.ethanzyc.commons.business.controller;

import com.ethanzyc.commons.base.response.Result;
import com.ethanzyc.commons.business.domain.TTest;
import com.ethanzyc.commons.business.service.StartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试用
 * @author ethan
 * @date 2019/8/25 12:06
 */
@RestController
@RequestMapping("start")
@Slf4j
public class StartController {

    @Autowired private StartService startService;

    @GetMapping("test")
    public Result<String> test() {
        throw new ArrayIndexOutOfBoundsException();
//        return Result.success("started");
    }

    @GetMapping("test-sql")
    public Result<List<TTest>> testMybatis(
            @RequestParam(required = false, defaultValue = "10") Integer start,
            @RequestParam(required = false, defaultValue = "20") Integer end) {
        return Result.success(startService.find(start, end));
    }

    @GetMapping("test-log")
    public Result<TTest> testLog() {

        TTest tTest = new TTest();
        tTest.setId(1);

        // 级别由低到高 trace<debug<info<warn<error
        log.trace("这是一个trace日志...");
        log.debug("这是一个debug日志...");

        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        log.info("这是一个info日志...");
        log.warn("这是一个warn日志...");
        log.error("这是一个error日志...");
        return Result.success(tTest);
    }

    @GetMapping("test-mybatis-plus")
    public Result<List<TTest>> testMybatisPlus(
            @RequestParam(required = false, defaultValue = "10") Integer start,
            @RequestParam(required = false, defaultValue = "20") Integer end) {
        return Result.success(startService.findByPlus(start, end));
    }

    @GetMapping("test-redis")
    public Result<Boolean> testRedis(@RequestParam String key, @RequestParam String value) {
        return Result.success(startService.testRedis(key, value));
    }

    @GetMapping("test-get-redis")
    public Result<String> testRedis(@RequestParam String key) {
        return Result.success(startService.getRedis(key));
    }

    @GetMapping("test-redis-hash")
    public Result testHash(@RequestParam String key) {

        List<TTest> byPlus = startService.findByPlus(200, 300);
        startService.testHashRedis(key, byPlus.get(0));
        return Result.success();

    }

    @GetMapping("test-get-redis-hash")
    public Result<TTest> testgetHash(@RequestParam String key) {

        TTest tTest = startService.testGetHashRedis(key);
        return Result.success(tTest);

    }

}

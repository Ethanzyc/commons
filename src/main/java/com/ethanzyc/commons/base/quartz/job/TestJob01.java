package com.ethanzyc.commons.base.quartz.job;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ethan
 * @date 2019/9/2 22:03
 */
@Component("testJob01")
@Transactional(rollbackFor = Exception.class)
public class TestJob01 {

    public void execute() {
        System.out.println("-------------------TestJob01任务执行开始-------------------");
        System.out.println(new Date());
        System.out.println("-------------------TestJob01任务执行结束-------------------");
    }
}

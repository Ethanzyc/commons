package com.ethanzyc.commons.base.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 开始执行定时任务
 * @author ethan
 * @date 2019/9/2 22:02
 */
@Component
@Slf4j
public class JobSchedule implements CommandLineRunner {

    @Autowired
    private QuartzService taskService;

    /**
     * 任务调度开始
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        log.info("==========任务调度开始==========");
        taskService.timingTask();
        log.info("==========任务调度结束==========");
    }
}

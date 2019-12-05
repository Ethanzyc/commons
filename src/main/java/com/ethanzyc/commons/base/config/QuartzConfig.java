package com.ethanzyc.commons.base.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz配置
 * @author ethan
 * @date 2019/9/2 12:39
 */
@Configuration
public class QuartzConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延时20秒启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        schedulerFactoryBean.setStartupDelay(5);
        return schedulerFactoryBean;
    }

    /**
     * 创建schedule
     * @return
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}

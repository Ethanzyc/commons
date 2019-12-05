package com.ethanzyc.commons.base.quartz;

import com.ethanzyc.commons.base.util.SpringUtil;
import com.ethanzyc.commons.business.domain.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

/**
 * 定时任务方法的工厂类，利用反射
 * @author ethan
 * @date 2019/9/2 20:53
 */
public class QuartzFactory implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取调度数据
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        // 获取对应的Bean
        Object object = SpringUtil.getBean(scheduleJob.getBeanName());
        try {
            // 利用反射执行对应方法
            Method method = object.getClass().getMethod(scheduleJob.getMethodName());
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.ethanzyc.commons.base.quartz;

import com.ethanzyc.commons.business.domain.ScheduleJob;
import org.quartz.SchedulerException;

/**
 * @author ethan
 * @date 2019/9/2 20:59
 */
public interface QuartzService {
    /**
     * 查询数据库是否存在需要定时的任务
     */
    void timingTask();

    /**
     * 增加定时任务
     * @param job
     */
    void addJob(ScheduleJob job);

    /**
     * 启动、暂停、删除任务
     * @param jobOperateEnum
     * @param job
     * @throws SchedulerException
     */
    void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob job) throws SchedulerException;

    /**
     * 启动所有定时任务
     * @throws SchedulerException
     */
    void startAllJob() throws SchedulerException;

    /**
     * 暂停所有定时任务
     * @throws SchedulerException
     */
    void pauseAllJob() throws SchedulerException;

    /**
     * 判断是否启动（是否加入定时任务中，不管状态是启动还是暂停）
     * @return
     */
    boolean isStarted() throws SchedulerException;
}

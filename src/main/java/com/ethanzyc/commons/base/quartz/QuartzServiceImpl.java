package com.ethanzyc.commons.base.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethanzyc.commons.business.domain.ScheduleJob;
import com.ethanzyc.commons.business.service.impl.ScheduleJobServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ethan
 * @date 2019/9/2 20:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuartzServiceImpl implements QuartzService {
    /**
     * 调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobServiceImpl jobService;

    @Override
    public void timingTask() {
        // 查询数据库是否存在需要定时的任务
        List<ScheduleJob> scheduleJobs = jobService.list(new QueryWrapper<ScheduleJob>().eq(ScheduleJob.STATUS, JobOperateEnum.START.getValue()).eq(ScheduleJob.DELETE_FLAG, 0));
        if (scheduleJobs != null) {
            scheduleJobs.forEach(this::addJob);
        }
    }

    @Override
    public void addJob(ScheduleJob job) {
        try {
            //创建触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .startNow()
                    .build();

            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzFactory.class)
                    .withIdentity(job.getJobName())
                    .build();

            //传入调度的数据，在QuartzFactory中需要使用
            jobDetail.getJobDataMap().put("scheduleJob", job);

            //调度作业
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob job) throws SchedulerException {
        JobKey jobKey = new JobKey(job.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            addJob(job);
        }
        switch (jobOperateEnum) {
            case START:
                scheduler.resumeJob(jobKey);
                break;
            case PAUSE:
                scheduler.pauseJob(jobKey);
                break;
            case DELETE:
                scheduler.deleteJob(jobKey);
                break;
            default:
                break;
        }
    }

    @Override
    public void startAllJob() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void pauseAllJob() throws SchedulerException {
        scheduler.standby();
    }

    @Override
    public boolean isStarted() throws SchedulerException {
        return scheduler.isStarted();
    }
}

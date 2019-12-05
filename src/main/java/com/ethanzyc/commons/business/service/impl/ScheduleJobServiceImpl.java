package com.ethanzyc.commons.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethanzyc.commons.base.exception.CustomException;
import com.ethanzyc.commons.base.quartz.JobOperateEnum;
import com.ethanzyc.commons.base.quartz.QuartzService;
import com.ethanzyc.commons.base.response.ResultCode;
import com.ethanzyc.commons.business.domain.ScheduleJob;
import com.ethanzyc.commons.business.mapper.ScheduleJobMapper;
import com.ethanzyc.commons.business.service.ScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 *  服务实现类
 * </p>
 *  todo 待完善逻辑
 * @author ethan
 * @since 2019-09-02
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {

    @Autowired
    ScheduleJobMapper schedulejobMapper;

    @Autowired
    private QuartzService quartzService;

    @Override
    public void add(ScheduleJob job) {

        //此处省去数据验证
        this.save(job);

        //加入job
        try {
            quartzService.addJob(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(int id) {
        //此处省去数据验证
        ScheduleJob job = this.getById(id);
        if (job == null) {
            throw new CustomException(ResultCode.TASK_NOT_EXIST);
        }
        job.setStatus(1);
        this.updateById(job);

        //执行job
        try {
            quartzService.operateJob(JobOperateEnum.START, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(int id) {
        //此处省去数据验证
        ScheduleJob job = this.getById(id);
        job.setStatus(2);
        this.updateById(job);

        //执行job
        try {
            quartzService.operateJob(JobOperateEnum.PAUSE, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        //此处省去数据验证
        ScheduleJob job = this.getById(id);
        this.removeById(id);

        //执行job
        try {
            quartzService.operateJob(JobOperateEnum.DELETE, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startAllJob() {
        //此处省去数据验证
        ScheduleJob job = new ScheduleJob();
        job.setStatus(1);
        this.update(job, new QueryWrapper<>());

        //执行job
        try {
            quartzService.startAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseAllJob() {
        //此处省去数据验证
        ScheduleJob job = new ScheduleJob();
        job.setStatus(2);
        this.update(job, new QueryWrapper<>());

        //执行job
        try {
            quartzService.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

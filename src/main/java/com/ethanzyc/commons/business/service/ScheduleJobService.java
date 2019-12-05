package com.ethanzyc.commons.business.service;

import com.ethanzyc.commons.business.domain.ScheduleJob;

/**
 * <p>
 *   定时任务服务类
 * </p>
 *
 * @author ethan
 * @since 2019-09-02
 */
public interface ScheduleJobService {
     /**
      * 新增任务
      * @param job
      */
     void add(ScheduleJob job);

     /**
      * 启动任务
      * @param id
      */
     void start(int id);

     /**
      * 暂停任务
      * @param id
      */
     void pause(int id);

     /**
      * 删除任务
      * @param id
      */
     void delete(int id);

     /**
      * 启动所有任务
      */
     void startAllJob();

     /**
      * 暂停所有任务
      */
     void pauseAllJob();

}

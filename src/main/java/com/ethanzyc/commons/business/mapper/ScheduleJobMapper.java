package com.ethanzyc.commons.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethanzyc.commons.business.domain.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * <p>
 *  Mapper接口
 * </p>
 *
 * @author ethan
 * @since 2019-09-02
 */
@Mapper
@Repository
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

}

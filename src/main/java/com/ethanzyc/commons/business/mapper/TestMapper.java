package com.ethanzyc.commons.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethanzyc.commons.business.domain.TTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ethan
 * @date 2019/8/25 13:20
 */
@Mapper
@Repository
public interface TestMapper extends BaseMapper<TTest> {
    /**
     * 查找
     * @param start
     * @param end
     * @return
     */
    List<TTest> findPage(@Param("start") Integer start, @Param("end") Integer end);
}

package com.ethanzyc.commons.business.mapper;

import com.ethanzyc.commons.business.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 员工信息表 Mapper接口
 * </p>
 *
 * @author ethan
 * @since 2019-09-20
 */
@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}

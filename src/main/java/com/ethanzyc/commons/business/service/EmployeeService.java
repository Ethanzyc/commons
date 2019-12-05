package com.ethanzyc.commons.business.service;

import com.ethanzyc.commons.business.domain.Employee;
/**
 * <p>
 * 员工信息表  服务类
 * </p>
 *
 * @author ethan
 * @since 2019-09-20
 */
public interface EmployeeService {

    Employee getById(Long id);

}

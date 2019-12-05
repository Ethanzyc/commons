package com.ethanzyc.commons.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethanzyc.commons.business.service.EmployeeService;
import com.ethanzyc.commons.business.domain.Employee;
import com.ethanzyc.commons.business.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author ethan
 * @since 2019-09-20
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee getById(Long id) {
        return employeeMapper.selectById(id);
    }
}

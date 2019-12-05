package com.ethanzyc.commons.business.controller;

import com.ethanzyc.commons.base.response.Result;
import com.ethanzyc.commons.business.controller.base.BaseController;
import com.ethanzyc.commons.business.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工信息表  前端控制器
 *
 * @author ethan
 * @since 2019-09-20
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/{id}")
	public Result get(@PathVariable("id") Long id) {
        return Result.success(employeeService.getById(id));
	}
}

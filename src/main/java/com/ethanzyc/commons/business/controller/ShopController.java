package com.ethanzyc.commons.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethanzyc.commons.base.response.Result;
import com.ethanzyc.commons.business.controller.base.BaseController;
import com.ethanzyc.commons.business.domain.Shop;
import com.ethanzyc.commons.business.domain.dbenum.ShopState;
import com.ethanzyc.commons.business.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公司表  前端控制器
 *
 * @author ethan
 * @since 2019-09-17
 */
@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@Autowired
	ShopServiceImpl shopService;

	@GetMapping(value = "/{id}")
	public Result get(@PathVariable("id") Long id) {
        return Result.success(shopService.getById(id));
	}

	@GetMapping(value = "list")
	public Result<List<Shop>> list() {
		return Result.success(shopService.list(new QueryWrapper<Shop>().eq(Shop.STATE, ShopState.NORMAL.getState())));
	}
}

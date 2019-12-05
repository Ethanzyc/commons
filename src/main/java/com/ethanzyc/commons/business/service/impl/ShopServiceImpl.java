package com.ethanzyc.commons.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ethanzyc.commons.business.service.ShopService;
import com.ethanzyc.commons.business.domain.Shop;
import com.ethanzyc.commons.business.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author ethan
 * @since 2019-09-17
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    public Shop getById(Long id) {
        return shopMapper.selectById(id);
    }
}

package com.ethanzyc.commons.business.mapper;

import com.ethanzyc.commons.business.domain.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 公司表 Mapper接口
 * </p>
 *
 * @author ethan
 * @since 2019-09-17
 */
@Mapper
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

}

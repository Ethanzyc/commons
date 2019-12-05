package com.ethanzyc.commons.business.domain.dbenum;

import lombok.Getter;

/**
 * @author ethan
 * @date 2019/9/17 08:54
 */
public enum ShopState {

    /**
     * 可用
     */
    NORMAL(1, "可用"),
    /**
     * 冻结，被禁用
     */
    FREEZE(0, "禁用");

    @Getter
    private Integer state;
    @Getter
    private String remark;

    ShopState(Integer state, String remark) {
        this.state = state;
        this.remark = remark;
    }
}

package com.ethanzyc.commons.business.domain.dbenum;

import lombok.Getter;

/**
 * 员工is_active:0：未启用；1：已启用；2：待审核
 * @author ethan
 * @date 2019/9/17 08:54
 */
public enum EmployeeState {

    /**
     * 冻结，被禁用
     */
    FREEZE(0, "未启用"),
    /**
     * 可用
     */
    NORMAL(1, "已启用"),
    /**
     * 冻结，被禁用
     */
    CHECKING(2, "待审核");

    @Getter
    private Integer state;
    @Getter
    private String remark;

    EmployeeState(Integer state, String remark) {
        this.state = state;
        this.remark = remark;
    }
}

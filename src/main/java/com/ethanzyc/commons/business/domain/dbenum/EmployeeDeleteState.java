package com.ethanzyc.commons.business.domain.dbenum;

import lombok.Getter;

/**
 * 员工is_delete:0：未启用；1：已启用；2：待审核
 * @author ethan
 * @date 2019/9/17 08:54
 */
public enum EmployeeDeleteState {

    /**
     * 未删除
     */
    UN_DELETE(0, "未删除"),
    /**
     * 删除
     */
    DELETE(1, "删除");

    @Getter
    private Integer state;
    @Getter
    private String remark;

    EmployeeDeleteState(Integer state, String remark) {
        this.state = state;
        this.remark = remark;
    }
}

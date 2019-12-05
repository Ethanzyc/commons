package com.ethanzyc.commons.base.quartz;

import java.io.Serializable;

/**
 * 定时任务操作
 * @author ethan
 * @date 2019/9/2 20:52
 */
public enum  JobOperateEnum {
    /**
     * 启动
     */
    START(1, "启动"),
    /**
     * 暂停
     */
    PAUSE(2, "暂停"),
    /**
     * 删除
     */
    DELETE(3, "删除");

    private final Integer value;
    private final String desc;

    JobOperateEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Serializable getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getEnumName() {
        return name();
    }
}

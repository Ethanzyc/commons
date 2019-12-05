package com.ethanzyc.commons.base.enums;

import lombok.Getter;

/**
 * @author ethan
 * @date 2019/8/26 13:34
 */
public enum Env {
    /**
     * 开发
     */
    DEV("dev"),
    /**
     * 测试
     */
    TEST("test"),
    /**
     * 生产
     */
    PROD("prod");

    @Getter
    private String name;

    Env(String name) {
        this.name = name;
    }


}

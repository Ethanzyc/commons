package com.ethanzyc.commons.base.constants;

public interface Constants {
    /**
     * redis前缀，也可以理解为文件名
     */
    class RedisPrefix {
        /**
         * 验证码
         */
        public static final String VERIFY_CODE = "VERIFY_CODE:";
        /**
         * 今日验证码发送次数
         */
        public static final String VERIFY_CODE_TIMES = "VERIFY_CODE_TIMES:";
    }

    class TimeLimit {
        /**
         * 每天发送注册验证码次数
         */
        public static final Integer REGISTER_SMS_TIMES = 10;
    }
}

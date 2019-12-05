package com.ethanzyc.commons.base.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ethan
 * @date 2019/9/20 08:57
 */
public class RandomUtil {

    /**
     * 生成随机码
     * @param length 随机码长度
     * @return
     */
    public static String randomCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int val = ThreadLocalRandom.current().nextInt(9);
            sb.append(val);
        }
        return sb.toString();

    }
}

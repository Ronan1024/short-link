package com.ronan.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * 分组ID随机分成器
 *
 * @program: short-link
 * @author: L.J.Ran
 * @create: 2026/3/3
 */
public final class RandomGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandom() {
        return generateRandom(6);
    }


    /**
     * 生成水机分组ID
     *
     * @param length 生成ID位数
     */
    public static String generateRandom(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }


}

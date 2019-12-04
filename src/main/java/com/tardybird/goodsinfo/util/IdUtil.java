package com.tardybird.goodsinfo.util;

import java.security.SecureRandom;

public class IdUtil {

    public static String getValue(int length) {
        String pattern = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(pattern.charAt(new SecureRandom().nextInt(pattern.length())));
        }
        return result.toString();
    }
}

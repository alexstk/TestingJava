package com.platzi.javatests.util;

public class StringUtil {

    public static String repeat(String str, int times){
        if (times < 0){
        throw new IllegalArgumentException("negative times is not allowed");
        }

        String result = "";
        for (int i = 0; i < times; i++) {
            result += str;
        }

        return result;
    }

}

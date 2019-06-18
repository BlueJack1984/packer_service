package com.packer.commons.sms.lang;


public class Assert {
    public Assert() {
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.equals("");
    }

    public static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }
}

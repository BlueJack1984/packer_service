package com.packer.commons.sms.util;

public class Assert {
    private Assert() {
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static void notNull(Object obj) throws IllegalArgumentException {
        if (isNull(obj)) {
            throw new IllegalArgumentException("Parameter is null!");
        }
    }

    public static boolean isEmpty(String msg) {
        if (isNull(msg)) {
            return true;
        } else {
            return msg.equals("");
        }
    }

    public static boolean isTrimEmpty(String msg) {
        if (isNull(msg)) {
            return true;
        } else {
            return msg.trim().equals("");
        }
    }
}

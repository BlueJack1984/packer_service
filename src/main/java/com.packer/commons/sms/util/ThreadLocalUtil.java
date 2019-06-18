package com.packer.commons.sms.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<Class<?>, Object>> local = new ThreadLocal();

    public ThreadLocalUtil() {
    }

    public static void save(Object obj) {
        if (obj != null) {
            Map<Class<?>, Object> result = (Map)local.get();
            if (result == null) {
                result = new HashMap();
                local.set(result);
            }

            ((Map)result).put(obj.getClass(), obj);
        }
    }

    public static Object get(Class<?> clazz) {
        if (clazz == null) {
            return null;
        } else {
            Map<Class<?>, Object> result = (Map)local.get();
            return result == null ? null : result.get(clazz);
        }
    }

    public static void remove(Class<?> clazz) {
        if (clazz != null) {
            Map<Class<?>, Object> result = (Map)local.get();
            if (result != null) {
                result.remove(clazz);
            }
        }
    }
}

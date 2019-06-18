package com.packer.commons.sms.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassUtil {
    private static Class<?> DEFAULT_COLLECTION_TYPE = ArrayList.class;
    private static Class<?> DEFAULT_LIST_TYPE = ArrayList.class;
    private static Class<?> DEFAULT_SET_TYPE = HashSet.class;

    public ClassUtil() {
    }

    public static boolean isSimpleType(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return true;
        } else if (clazz == String.class) {
            return true;
        } else if (clazz == Boolean.class) {
            return true;
        } else if (clazz == Character.class) {
            return true;
        } else if (clazz == Byte.class) {
            return true;
        } else if (clazz == Short.class) {
            return true;
        } else if (clazz == Integer.class) {
            return true;
        } else if (clazz == Long.class) {
            return true;
        } else if (clazz == Float.class) {
            return true;
        } else {
            return clazz == Double.class;
        }
    }

    public static boolean isArray(Class<?> clazz) {
        return clazz.isArray();
    }

    public static Object createArray(Class<?> arrayType, int length) {
        return Array.newInstance(arrayType, length);
    }

    public static Class<?> getArrayType(Class<?> arrayClazz) {
        return arrayClazz.getComponentType();
    }

    public static boolean isCollection(Class<?> clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    public static Collection<Object> createCollection(Class<?> clazz) throws Exception {
        if (!canInstance(clazz)) {
            if (List.class.isAssignableFrom(clazz)) {
                clazz = DEFAULT_LIST_TYPE;
            } else if (Set.class.isAssignableFrom(clazz)) {
                clazz = DEFAULT_SET_TYPE;
            } else {
                clazz = DEFAULT_COLLECTION_TYPE;
            }
        }

        return (Collection)clazz.newInstance();
    }

    public static boolean isMultiDimensionsArray(Class<?> clazz) {
        if (!isArray(clazz)) {
            return false;
        } else {
            Class<?> arrayType = getArrayType(clazz);
            return isArray(arrayType);
        }
    }

    public static boolean canInstance(Class<?> clazz) {
        if (clazz.isInterface()) {
            return false;
        } else {
            int modifiers = clazz.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                return false;
            } else {
                return !Modifier.isAbstract(modifiers);
            }
        }
    }

    public static Object convertToSimpleObject(String data, Class<?> targetClass) {
        if (targetClass != Integer.TYPE && targetClass != Integer.class) {
            if (targetClass != Boolean.TYPE && targetClass != Boolean.class) {
                if (targetClass != Long.TYPE && targetClass != Long.class) {
                    if (targetClass != Float.TYPE && targetClass != Float.class) {
                        if (targetClass != Double.TYPE && targetClass != Double.class) {
                            if (targetClass != Byte.TYPE && targetClass != Byte.class) {
                                if (targetClass != Short.TYPE && targetClass != Short.class) {
                                    if (targetClass != Character.TYPE && targetClass != Character.class) {
                                        return data;
                                    } else {
                                        char c = 0;
                                        if (data.length() == 0) {
                                            return c;
                                        } else {
                                            c = data.charAt(0);
                                            return c;
                                        }
                                    }
                                } else {
                                    return Short.valueOf(data);
                                }
                            } else {
                                return Byte.valueOf(data);
                            }
                        } else {
                            return Double.valueOf(data);
                        }
                    } else {
                        return Float.valueOf(data);
                    }
                } else {
                    return Long.valueOf(data);
                }
            } else {
                return Boolean.valueOf(data);
            }
        } else {
            return Integer.valueOf(data);
        }
    }

    public static LinkedHashMap<String, Class<?>> getOrdinalProperties(Class<?> objectClass) {
        LinkedHashMap<String, Class<?>> ordinalResult = new LinkedHashMap();
        List<String> fields = getFields(objectClass);
        LinkedHashMap<String, Class<?>> properties = getProperties(objectClass);

        for(int i = 0; i < fields.size(); ++i) {
            String field = (String)fields.get(i);
            if (properties.containsKey(field)) {
                ordinalResult.put(field, (Class)properties.get(field));
                properties.remove(field);
            }
        }

        ordinalResult.putAll(properties);
        return ordinalResult;
    }

    public static LinkedHashMap<String, Class<?>> getProperties(Class<?> objectClass) {
        Map<String, Method> getMethods = getGetProperties(objectClass);
        Map<String, Method> setMethods = getSetProperties(objectClass);
        LinkedHashMap<String, Class<?>> result = new LinkedHashMap();
        Iterator keyIt = getMethods.keySet().iterator();

        while(keyIt.hasNext()) {
            String property = (String)keyIt.next();
            Method getMethod = (Method)getMethods.get(property);
            Method setMethod = (Method)setMethods.get(property);
            if (setMethod != null && setMethod.getParameterTypes()[0] == getMethod.getReturnType()) {
                result.put(property, getMethod.getReturnType());
            }
        }

        return result;
    }

    public static LinkedHashMap<String, Method> getGetProperties(Class<?> objectClass) {
        Method[] methods = objectClass.getDeclaredMethods();
        LinkedHashMap<String, Method> result = new LinkedHashMap();

        for(int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                String name = method.getName();
                if (name.length() >= 4 && name.startsWith("get") && !Character.isLowerCase(name.charAt(3)) && method.getParameterTypes().length <= 0) {
                    Class<?> returnType = method.getReturnType();
                    if (returnType != Void.class && returnType != Void.TYPE) {
                        char begin = name.charAt(3);
                        String property = "" + Character.toLowerCase(begin);
                        if (name.length() > 4) {
                            property = property + name.substring(4);
                        }

                        result.put(property, method);
                    }
                }
            }
        }

        return result;
    }

    public static LinkedHashMap<String, Method> getSetProperties(Class<?> objectClass) {
        Method[] methods = objectClass.getDeclaredMethods();
        LinkedHashMap<String, Method> result = new LinkedHashMap();

        for(int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                String name = method.getName();
                if (name.length() >= 4 && name.startsWith("set") && !Character.isLowerCase(name.charAt(3)) && method.getParameterTypes().length == 1) {
                    Class<?> returnType = method.getReturnType();
                    if (returnType == Void.TYPE) {
                        char begin = name.charAt(3);
                        String property = "" + Character.toLowerCase(begin);
                        if (name.length() > 4) {
                            property = property + name.substring(4);
                        }

                        result.put(property, method);
                    }
                }
            }
        }

        return result;
    }

    public static List<String> getFields(Class<?> clazz) {
        List<String> result = new ArrayList();
        Field[] fields = clazz.getDeclaredFields();

        for(int i = 0; i < fields.length; ++i) {
            result.add(fields[i].getName());
        }

        return result;
    }

    //public static void main(String[] args) {
    //    System.out.println(getOrdinalProperties(AuRain.class));
    //}
}


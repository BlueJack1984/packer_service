package com.packer.commons.sms.platformapp.impl;


import com.packer.commons.sms.platformapp.IObjectPropertyExchanger;
import com.packer.commons.sms.platformapp.SmsObjectMappingException;
import com.packer.commons.sms.util.ClassUtil;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ObjMapping {
    private Map<Class<?>, IObjectPropertyExchanger<Object>> objectExchangerMapping = new HashMap();
    private static Class<String> DEFAULT_COLLECTION_ELEMENT_TYPE = String.class;
    private static Map<String, Method> setMethodCache = new HashMap();
    private static Map<String, Method> getMethodCache = new HashMap();

    public void setObjectExchangerMapping(List<IObjectPropertyExchanger<Object>> objectExchangers) {
        if (objectExchangers != null) {
            for(int i = 0; i < objectExchangers.size(); ++i) {
                IObjectPropertyExchanger<Object> objectExchanger = (IObjectPropertyExchanger)objectExchangers.get(i);
                this.objectExchangerMapping.put(objectExchanger.getPropertyClass(), objectExchanger);
            }
        }

    }

    public ObjMapping() {
    }

    public Class<?> forName(String classPath) throws SmsObjectMappingException {
        try {
            return Class.forName(classPath);
        } catch (Exception var6) {
            try {
                return Thread.currentThread().getContextClassLoader().loadClass(classPath);
            } catch (Exception var5) {
                String msg = "根据类路径[" + classPath + "]生成Class对象时抛出异常!";
                throw new SmsObjectMappingException(msg, var5);
            }
        }
    }

    public Object newInstance(String classPath) throws SmsObjectMappingException {
        Class objClass = this.forName(classPath);

        try {
            return objClass.newInstance();
        } catch (Exception var4) {
            throw new SmsObjectMappingException("创建业务对象[" + classPath + "]时发生异常!", var4);
        }
    }

    public Object newInstance(Class<?> clazz) throws SmsObjectMappingException {
        try {
            return clazz.newInstance();
        } catch (Exception var3) {
            throw new SmsObjectMappingException("创建业务对象[" + clazz.getName() + "]时发生异常!", var3);
        }
    }

    protected Method getSetMethod(Object obj, String property) throws SmsObjectMappingException {
        String cacheKey = obj.getClass().getName() + "-" + property;
        Method methodCache = (Method)setMethodCache.get(cacheKey);
        if (methodCache == null) {
            char begin = property.charAt(0);
            String setMethodName = "set" + Character.toUpperCase(begin) + property.substring(1);
            Class<?> objClass = obj.getClass();
            Method[] methods = objClass.getMethods();

            for(int i = 0; i < methods.length; ++i) {
                Method method = methods[i];
                if (method.getName().equals(setMethodName)) {
                    Class<?> returnClass = method.getReturnType();
                    if (returnClass == Void.TYPE) {
                        Class[] parameters = method.getParameterTypes();
                        if (parameters.length == 1) {
                            methodCache = method;
                            break;
                        }
                    }
                }
            }

            if (methodCache == null) {
                String msg = "对象[" + obj.getClass().getName() + "]没有提供属性[" + property + "]的set方法!";
                throw new SmsObjectMappingException(msg);
            }

            setMethodCache.put(cacheKey, methodCache);
        }

        return methodCache;
    }

    protected Method getGetMethod(Object obj, String property) throws SmsObjectMappingException {
        String cacheKey = obj.getClass().getName() + "-" + property;
        Method methodCache = (Method)getMethodCache.get(cacheKey);
        if (methodCache == null) {
            char begin = property.charAt(0);
            String getMethodName = "get" + Character.toUpperCase(begin) + property.substring(1);
            Class<?> objClass = obj.getClass();
            Method[] methods = objClass.getMethods();

            for(int i = 0; i < methods.length; ++i) {
                Method method = methods[i];
                if (method.getName().equals(getMethodName)) {
                    Class<?> returnClass = method.getReturnType();
                    if (returnClass != Void.TYPE && returnClass != Void.class) {
                        Class[] parameters = method.getParameterTypes();
                        if (parameters.length == 0) {
                            methodCache = method;
                            break;
                        }
                    }
                }
            }

            if (methodCache == null) {
                String msg = "对象[" + obj.getClass().getName() + "]没有提供属性[" + property + "]的get方法!";
                throw new SmsObjectMappingException(msg);
            }

            getMethodCache.put(cacheKey, methodCache);
        }

        return methodCache;
    }

    public Class<?> getPropertyClass(Object obj, String property) {
        Method method = this.getGetMethod(obj, property);
        return method.getReturnType();
    }

    public Class<?> getSetMethodParameterType(Object obj, String property) throws SmsObjectMappingException {
        return this.getSetMethod(obj, property).getParameterTypes()[0];
    }

    public boolean isSimpleType(Class<?> clazz) {
        if (ClassUtil.isSimpleType(clazz)) {
            return true;
        } else {
            return this.objectExchangerMapping.containsKey(clazz);
        }
    }

    public Collection<Object> createCollection(Class<?> clazz) {
        try {
            return ClassUtil.createCollection(clazz);
        } catch (Exception var4) {
            String msg = "创建集合实例[" + clazz.getName() + "]时发生异常!";
            throw new SmsObjectMappingException(msg, var4);
        }
    }

    public void setSimpleTypeValues(Object obj, String propertyName, List<String> values) {
        Method setMethod = this.getSetMethod(obj, propertyName);
        Class<?> parameter = setMethod.getParameterTypes()[0];
        Object parameterValue = null;
        int i;
        if (ClassUtil.isArray(parameter)) {
            Class<?> arrayType = ClassUtil.getArrayType(parameter);
            Object arrayObjs = ClassUtil.createArray(arrayType, values.size());
            i = Array.getLength(arrayObjs);

            for(i = 0; i < i; ++i) {
                Object arrayObj = this.convertToSimpleObject((String)values.get(i), arrayType);
                Array.set(arrayObjs, i, arrayObj);
            }

            parameterValue = arrayObjs;
        } else if (ClassUtil.isCollection(parameter)) {
            Collection<Object> collection = this.createCollection(parameter);

            for(i = 0; i < values.size(); ++i) {
                Object collectionItem = this.convertToSimpleObject((String)values.get(i), DEFAULT_COLLECTION_ELEMENT_TYPE);
                collection.add(collectionItem);
            }

            parameterValue = collection;
        } else {
            parameterValue = this.convertToSimpleObject((String)values.get(0), parameter);
        }

        this.setObjectValue(obj, propertyName, parameterValue);
    }

    public void setSimpleTypeValue(Object obj, String propertyName, String value) {
        Method setMethod = this.getSetMethod(obj, propertyName);
        Class<?> parameter = setMethod.getParameterTypes()[0];
        Object objValue = this.convertToSimpleObject(value, parameter);
        this.setObjectValue(obj, propertyName, objValue);
    }

    public void setObjectValue(Object obj, String propertyName, Object value) {
        Method setMethod = this.getSetMethod(obj, propertyName);

        try {
            setMethod.invoke(obj, value);
        } catch (Exception var7) {
            String msg = "为对象[" + obj.getClass().getName() + "]设置属性值[" + propertyName + "=" + value + "]时发生异常!";
            throw new SmsObjectMappingException(msg, var7);
        }
    }

    public List<String> getSimpleTypeValues(Object obj, String propertyName) {
        Method getMethod = this.getGetMethod(obj, propertyName);
        Object returnObj = null;

        try {
            returnObj = getMethod.invoke(obj);
        } catch (Exception var11) {
            String msg = "调用对象[" + obj.getClass().getName() + "]属性[" + propertyName + "]的get方法时抛出异常!";
            throw new SmsObjectMappingException(msg);
        }

        if (returnObj == null) {
            return null;
        } else {
            List<String> result = new ArrayList();
            Class<?> returnObjClass = returnObj.getClass();
            if (ClassUtil.isArray(returnObjClass)) {
                Class<?> arrayElementType = ClassUtil.getArrayType(returnObjClass);
                if (!this.isSimpleType(arrayElementType)) {
                    throw new SmsObjectMappingException("类[" + obj.getClass().getName() + "]的属性[" + propertyName + "]不是简单类型数组!");
                }

                int length = Array.getLength(returnObj);

                for(int i = 0; i < length; ++i) {
                    Object arrayObj = Array.get(returnObj, i);
                    result.add(this.convertToString(arrayObj));
                }
            } else if (ClassUtil.isCollection(returnObjClass)) {
                Collection<?> collection = (Collection)returnObj;
                Iterator it = collection.iterator();

                while(it.hasNext()) {
                    Object collectionItem = it.next();
                    if (collectionItem.getClass() != DEFAULT_COLLECTION_ELEMENT_TYPE) {
                        throw new SmsObjectMappingException("类[" + obj.getClass().getName() + "]的属性[" + propertyName + "]不是String类型集合!");
                    }

                    result.add(this.convertToString(collectionItem));
                }
            } else {
                if (!this.isSimpleType(returnObj.getClass())) {
                    throw new SmsObjectMappingException("类[" + returnObj.getClass().getName() + "]的属性[" + propertyName + "]不是简单类型!");
                }

                result.add(this.convertToString(returnObj));
            }

            return result;
        }
    }

    public Object getBusinessObject(Object obj, String propertyName) {
        Method getMethod = this.getGetMethod(obj, propertyName);
        Class<?> returnType = getMethod.getReturnType();
        if (this.isSimpleType(returnType)) {
            throw new SmsObjectMappingException("对象[" + obj.getClass().getName() + "]的属性[" + propertyName + "]被设置为简单类型!");
        } else {
            Object returnObj = null;

            try {
                returnObj = getMethod.invoke(obj);
                return returnObj;
            } catch (Exception var8) {
                String msg = "调用对象[" + obj.getClass().getName() + "]属性[" + propertyName + "]的get方法时抛出异常!";
                throw new SmsObjectMappingException(msg);
            }
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"a", "b"};
        System.out.println(strs[1]);
    }

    public String convertToString(Object obj) throws SmsObjectMappingException {
        if (obj == null) {
            throw new SmsObjectMappingException("对象为空，无法转换成字符串!");
        } else if (!this.isSimpleType(obj.getClass())) {
            throw new SmsObjectMappingException("无法把对象[" + obj + "]转换成字符串");
        } else {
            IObjectPropertyExchanger<Object> objectExchanger = (IObjectPropertyExchanger)this.objectExchangerMapping.get(obj.getClass());
            return objectExchanger != null ? objectExchanger.object2String(obj) : obj.toString();
        }
    }

    public Object convertToSimpleObject(String data, Class<?> targetClass) throws SmsObjectMappingException {
        if (!this.isSimpleType(targetClass)) {
            String msg = "不支持的简单类型[" + targetClass.getName() + "]!";
            throw new SmsObjectMappingException(msg);
        } else {
            try {
                IObjectPropertyExchanger<?> objectExchanger = (IObjectPropertyExchanger)this.objectExchangerMapping.get(targetClass);
                return objectExchanger != null ? objectExchanger.string2Object(data) : ClassUtil.convertToSimpleObject(data, targetClass);
            } catch (Exception var5) {
                String msg = "把数据[" + data + "]转换成简单类型[" + targetClass.getName() + "]时发生异常!";
                throw new SmsObjectMappingException(msg, var5);
            }
        }
    }
}

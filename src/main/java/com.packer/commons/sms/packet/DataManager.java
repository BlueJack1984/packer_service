package com.packer.commons.sms.packet;

import com.packer.commons.sms.util.ThreadLocalUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataManager {
    public static final String METHOD_PARAMETER_PHONE_KEY = "phoneKey";
    private static final Map<String, Object> globalDatasMapping = new LinkedHashMap();

    public DataManager() {
    }

    public static void registerFieldsValue(Map<String, IValueSource> values) {
        ThreadLocalUtil.save(values);
    }

    public static void clearFieldsValue() {
        ThreadLocalUtil.remove(HashMap.class);
    }

    public static IValueSource getFieldValueSource(String name) {
        Map<String, IValueSource> values = (Map)ThreadLocalUtil.get(HashMap.class);
        return values != null ? (IValueSource)values.get(name) : null;
    }

    public static void registerGlobalData(String key, Object value) {
        globalDatasMapping.put(key, value);
    }

    public static Object getGlobalData(String key) {
        return globalDatasMapping.get(key);
    }

    public static Map<String, Object> getGlobalDatas() {
        return globalDatasMapping;
    }

    public static PacketGroupData getPacketGroupData() {
        return (PacketGroupData)ThreadLocalUtil.get(PacketGroupData.class);
    }

    public static PacketData getCurrentPacketData() {
        PacketGroupData groupData = (PacketGroupData)ThreadLocalUtil.get(PacketGroupData.class);
        return groupData == null ? null : groupData.getCurrentPacketData();
    }

    public static Map<String, Object> getParameterDatas() {
        PacketGroupData groupData = (PacketGroupData)ThreadLocalUtil.get(PacketGroupData.class);
        return groupData == null ? null : groupData.getParameterDatas();
    }

    public static String getPhoneParameter() {
        Map<String, Object> parameterDatas = getParameterDatas();
        return parameterDatas == null ? null : (String)parameterDatas.get("phoneKey");
    }

    public static Object getObjectParameter() {
        String phone = getPhoneParameter();
        return phone == null ? null : getParameterData(phone);
    }

    public static Object getParameterData(String key) {
        Map<String, Object> parameterDatas = getParameterDatas();
        return parameterDatas == null ? null : parameterDatas.get(key);
    }

    public static void savePacketGroupData(PacketGroupData packetGroupData) {
        ThreadLocalUtil.save(packetGroupData);
    }

    public static void clearPacketGroupData() {
        ThreadLocalUtil.remove(PacketGroupData.class);
    }
}

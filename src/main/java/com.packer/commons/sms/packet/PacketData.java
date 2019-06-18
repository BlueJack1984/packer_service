package com.packer.commons.sms.packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PacketData {
    private String packetName;
    private String command;
    private String controlFieldsSms;
    private String businessFieldsSms;
    protected Map<String, List<FieldData>> fieldDatasMapping = new LinkedHashMap();

    public PacketData() {
    }

    public PacketData(String packetName) {
        this.packetName = packetName;
    }

    public PacketData(String packetName, String command) {
        this.packetName = packetName;
        this.command = command;
    }

    public void addFieldData(String fieldName, String inputData, String convertedData, String typeData) {
        this.addFieldData(new FieldData(fieldName, inputData, convertedData, typeData));
    }

    public void addFieldData(String fieldName, List<FieldData> fds) {
        List<FieldData> fieldDatas = (List)this.fieldDatasMapping.get(fieldName);
        if (fieldDatas == null) {
            fieldDatas = new ArrayList();
            this.fieldDatasMapping.put(fieldName, fieldDatas);
        }

        Iterator var5 = fds.iterator();

        while(var5.hasNext()) {
            FieldData fd = (FieldData)var5.next();
            this.addFieldData(fd);
        }

    }

    public void addFieldData(FieldData fieldData) {
        String fieldName = fieldData.getFieldName();
        List<FieldData> fieldDatas = (List)this.fieldDatasMapping.get(fieldName);
        if (fieldDatas == null) {
            fieldDatas = new ArrayList();
            this.fieldDatasMapping.put(fieldName, fieldDatas);
        }

        ((List)fieldDatas).add(fieldData);
    }

    public List<FieldData> getFieldDatas(String fieldName) {
        return (List)this.fieldDatasMapping.get(fieldName);
    }

    public FieldData getFieldData(String fieldName) {
        List<FieldData> fieldDatas = this.getFieldDatas(fieldName);
        return fieldDatas != null && fieldDatas.size() != 0 ? (FieldData)fieldDatas.get(0) : null;
    }

    public boolean containsFieldData(String fieldName) {
        return this.fieldDatasMapping.containsKey(fieldName);
    }

    public String getCommand() {
        return this.command;
    }

    public String getPacketName() {
        return this.packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getControlFieldsSms() {
        return this.controlFieldsSms;
    }

    public void setControlFieldsSms(String controlFieldsSms) {
        this.controlFieldsSms = controlFieldsSms;
    }

    public String getBusinessFieldsSms() {
        return this.businessFieldsSms;
    }

    public void setBusinessFieldsSms(String businessFieldsSms) {
        this.businessFieldsSms = businessFieldsSms;
    }
}

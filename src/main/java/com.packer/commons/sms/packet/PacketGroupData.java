package com.packer.commons.sms.packet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketGroupData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Object> parameterDatas = new HashMap();
    private String packetGroupName;
    private String packetGroupCommand;
    private int currentIndex = 0;
    private List<PacketData> packetDatas = new ArrayList();

    public PacketGroupData() {
    }

    public void clear() {
        this.packetDatas.clear();
        this.currentIndex = 0;
    }

    public void addPacketData(PacketData data) {
        this.packetDatas.add(this.currentIndex, data);
        ++this.currentIndex;
    }

    public List<PacketData> getPacketDatas() {
        return this.packetDatas;
    }

    public PacketData getPacketData(int index) {
        return (PacketData)this.packetDatas.get(index - 1);
    }

    public PacketData getCurrentPacketData() {
        return this.getPacketData(this.currentIndex);
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Map<String, Object> getParameterDatas() {
        return this.parameterDatas;
    }

    public void setParameterDatas(Map<String, Object> parameterDatas) {
        this.parameterDatas = parameterDatas;
    }

    public List<FieldData> getFieldDatas(String fieldName) {
        PacketData currentPacketData = this.getCurrentPacketData();
        List fieldDatas;
        if (currentPacketData != null && currentPacketData.containsFieldData(fieldName)) {
            fieldDatas = currentPacketData.getFieldDatas(fieldName);
            return fieldDatas;
        } else {
            for(int i = 0; i < this.packetDatas.size(); ++i) {
                PacketData packetData = (PacketData)this.packetDatas.get(i);
                if (packetData.containsFieldData(fieldName)) {
                    fieldDatas = packetData.getFieldDatas(fieldName);
                    return fieldDatas;
                }
            }

            if (currentPacketData != null) {
                fieldDatas = currentPacketData.getFieldDatas(fieldName);
                return fieldDatas;
            } else {
                return null;
            }
        }
    }

    public String getPacketGroupName() {
        return this.packetGroupName;
    }

    public void setPacketGroupName(String packetGroupName) {
        this.packetGroupName = packetGroupName;
    }

    public String getPacketGroupCommand() {
        return this.packetGroupCommand;
    }

    public void setPacketGroupCommand(String packetGroupCommand) {
        this.packetGroupCommand = packetGroupCommand;
    }
}

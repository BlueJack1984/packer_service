package com.packer.commons.sms;


import com.packer.commons.sms.packet.valuesource.MacValueSourceException;
import com.packer.commons.sms.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SmsException extends RuntimeException {
    private static final long serialVersionUID = 6575796108389376314L;
    private static boolean isShowEnvironment = true;
    private String packetChannelName;
    private String structUnitName;
    private String packetGroupName;
    private String fieldName;
    private List<String> receivedSmses = new ArrayList();
    private Map<String, Object> properties = new LinkedHashMap();

    public static void setShowEnvironment(boolean isShowEnvironment) {
        isShowEnvironment = isShowEnvironment;
    }

    public SmsException() {
    }

    public SmsException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public SmsException(String msg) {
        super(msg);
    }

    public SmsException(Throwable ex) {
        super(ex);
    }

    public String getMessage() {
        if (!isShowEnvironment) {
            return super.getMessage();
        } else {
            StringBuffer msg = new StringBuffer();
            msg.append("\nPosition:PacketChannel[").append(this.packetChannelName).append("]");
            if (!Assert.isEmpty(this.structUnitName)) {
                msg.append(">>StructureUnit[").append(this.structUnitName).append("]");
            }

            if (!Assert.isEmpty(this.packetGroupName)) {
                msg.append(">>PacketGroup[").append(this.packetGroupName).append("]");
            }

            if (!Assert.isEmpty(this.fieldName)) {
                msg.append(">>Field[").append(this.fieldName).append("]");
            }

            msg.append("\n");
            String key;
            if (this.receivedSmses.size() != 0) {
                msg.append("Received Smses:\n");

                for(int i = 0; i < this.receivedSmses.size(); ++i) {
                    key = (String)this.receivedSmses.get(i);
                    msg.append("\t").append(key).append("\n");
                }
            }

            if (this.properties.size() != 0) {
                msg.append("Properties:\n");
                Iterator keyIt = this.properties.keySet().iterator();

                while(keyIt.hasNext()) {
                    key = (String)keyIt.next();
                    Object value = this.properties.get(key);
                    msg.append("\t").append(key).append(" = ").append(value).append("\n");
                }
            }

            String superMessage = super.getMessage();
            if (!Assert.isEmpty(superMessage)) {
                msg.append(super.getMessage());
            }

            return msg.toString();
        }
    }

    public String getPacketChannelName() {
        return this.packetChannelName;
    }

    public void setPacketChannelName(String packetChannelName) {
        this.packetChannelName = packetChannelName;
    }

    public String getStructUnitName() {
        return this.structUnitName;
    }

    public void setStructUnitName(String structUnitName) {
        this.structUnitName = structUnitName;
    }

    public String getPacketGroupName() {
        return this.packetGroupName;
    }

    public void setPacketGroupName(String packetGroupName) {
        this.packetGroupName = packetGroupName;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getReceivedSmses() {
        return this.receivedSmses;
    }

    public void setReceivedSmses(List<String> receivedSmses) {
        this.receivedSmses = receivedSmses;
    }

    public void addProperty(String key, String value) {
        this.properties.put(key, value);
    }

    public void addProperties(Map<String, Object> properties) {
        if (properties != null) {
            this.properties.putAll(properties);
        }

    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        System.out.println();
        MacValueSourceException ex = new MacValueSourceException("MAC计算失败!");
        ex.setPacketChannelName("上海空写");
        ex.setPacketGroupName("号码查询");
        ex.setFieldName("MAC");
        ex.setStructUnitName("MO");
        List<String> smses = new ArrayList();
        smses.add("0A0B........1");
        smses.add("0A0B........2");
        ex.setReceivedSmses(smses);
        ex.addProperty("agentPhone", "13800000000");
        ex.printStackTrace();
    }
}
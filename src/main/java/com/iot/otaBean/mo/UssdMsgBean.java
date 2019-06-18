package com.iot.otaBean.mo;

import java.io.Serializable;

/**
 * Ussd消息bean 用于USSD网关系统与OTA系统传递ussd消息
 *
 * @author Administrator
 *
 */
public class UssdMsgBean implements Serializable {

    private static final long serialVersionUID = -2133842161314633563L;

    private String msisdn; // 手机号

    private String msg;// 整条报文

    private String channel;// 短信对象来源： 0-来源于设备终端；1-来源于服务器

    private String channelType;// 渠道： 0-ussd；1-bip

    private String queueCode;

    private String imsi;

    private String operatorCode;//对接的供应商编码

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getQueueCode() {
        return queueCode;
    }

    public void setQueueCode(String queueCode) {
        this.queueCode = queueCode;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

}

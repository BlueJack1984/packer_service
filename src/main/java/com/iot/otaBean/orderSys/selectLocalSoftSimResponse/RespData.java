package com.iot.otaBean.orderSys.selectLocalSoftSimResponse;

public class RespData {

    private String simIccid;
    private String simImsi;
    private String coverCountry;
    private String orderId;
    public String getSimIccid() {
        return simIccid;
    }
    public void setSimIccid(String simIccid) {
        this.simIccid = simIccid;
    }
    public String getSimImsi() {
        return simImsi;
    }
    public void setSimImsi(String simImsi) {
        this.simImsi = simImsi;
    }
    public String getCoverCountry() {
        return coverCountry;
    }
    public void setCoverCountry(String coverCountry) {
        this.coverCountry = coverCountry;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
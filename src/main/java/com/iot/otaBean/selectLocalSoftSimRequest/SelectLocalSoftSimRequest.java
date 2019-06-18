package com.iot.otaBean.selectLocalSoftSimRequest;

/**
 *为OTA设备的副号套餐订单选择副号
 * @author ll
 *
 */
public class SelectLocalSoftSimRequest {

    private String deviceType;
    private String orderCode;
    private String curPostion;
    private String sysName;
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getOrderCode() {
        return orderCode;
    }
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public String getCurPostion() {
        return curPostion;
    }
    public void setCurPostion(String curPostion) {
        this.curPostion = curPostion;
    }
    public String getSysName() {
        return sysName;
    }
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
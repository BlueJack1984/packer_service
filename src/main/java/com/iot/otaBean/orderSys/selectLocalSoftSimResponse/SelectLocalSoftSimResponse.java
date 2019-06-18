package com.iot.otaBean.orderSys.selectLocalSoftSimResponse;


/**
 *为OTA设备的副号套餐订单选择副号
 * @author ll
 *
 */
public class SelectLocalSoftSimResponse {
    private String error;
    private String errorMsg;
    private RespData respData = new RespData();
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public RespData getRespData() {
        return respData;
    }
    public void setRespData(RespData respData) {
        this.respData = respData;
    }

}
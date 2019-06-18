package com.iot.otaBean.mo;


/**
 * por ussd对象
 *
 * @author Administrator
 *
 */
public class PorMo extends BaseMo {
    /**
     *
     */
    private static final long serialVersionUID = -5052404595198368598L;
    private String otaTradeNo;// ota交易流水号
    private String imei;// 终端设备imei号

    public String getOtaTradeNo() {
        return otaTradeNo;
    }

    public void setOtaTradeNo(String otaTradeNo) {
        this.otaTradeNo = otaTradeNo;
    }

    public String getImei() {
        return imei.substring(0, 15);
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

}

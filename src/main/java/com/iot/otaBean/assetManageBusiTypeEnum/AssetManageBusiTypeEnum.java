package com.iot.otaBean.assetManageBusiTypeEnum;

public enum AssetManageBusiTypeEnum {

    CHANGE_MAIN_CARD_ICCID("01", "更新主号"), CHANGE_MAIN_CARD_PLMN("02", "更新主号PLMN"), LOCK_ASSET("03", "锁定设备")
    , UPDATE_APN_PARAM("06", "更新apn参数"), UPDATE_PNUMBER_PARAM("07", "更新主号的附加参数")
    , UPDATE_PNUMBER_APN_PARAM("08", "更新主号的apn参数"), LOCATION_UPDATE("09", "发送位置上报"), RESTART_DEVICE("10", "设备重启")
    , UPDATE_TIME_PARAM("11", "更新时间参数"),SWITCH_TO_GLOBAL("12", "切主号"),UPDATE_BIP_PARAM("13", "更新bip参数"),UPDATE_PLMN_BIP_PARAM("14", "更新主号bip参数"),STOP_LOCATION_UPDATE("15", "停止设备上报");
    private String busiType;

    private String busiDesc;

    private AssetManageBusiTypeEnum(String busiType, String busiDesc) {
        this.busiDesc = busiDesc;
        this.busiType = busiType;
    }

    public String getBusiDesc() {
        return busiDesc;
    }

    public void setBusiDesc(String busiDesc) {
        this.busiDesc = busiDesc;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }
}
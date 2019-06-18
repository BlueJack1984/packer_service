package com.iot.otaBean.cmdTypeEnum;

public enum CmdTypeEnum {
    // 下载副号por
    LOCALDATA_POR("31"),
    // 更新主号por
    UPDATEICCID_POR("32"),
    // pushCommandpor
    PUSHCOMMAND_POR("33"),
    RECIEVE_DATA_POR("39");

    String cmdType;

    private CmdTypeEnum(String cmdType) {
        this.cmdType = cmdType;
    }
    public String getCmdType() {
        return cmdType;
    }
    public void setCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

}

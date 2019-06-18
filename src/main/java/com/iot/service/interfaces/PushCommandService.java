package com.iot.service.interfaces;

import com.iot.otaBean.deviceInitRec.DeviceInitRec;
import com.iot.otaBean.mo.PositionMo;

public interface PushCommandService {
    public boolean softsimRelease(String imei);
    public String switchGlobalBusi(PositionMo positionMo, String imei);
    public boolean getPushCommand(String imei, String busiType);
}

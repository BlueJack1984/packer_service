package com.iot.service.interfaces;

import com.iot.otaBean.assetOrder.AssetOrder;
import com.iot.otaBean.deviceInitRec.DeviceInitRec;
import com.iot.otaBean.mo.PositionMo;
import com.iot.otaBean.mt.MtData;
import com.iot.otaBean.mt.PlainDataMt;

import java.util.List;

public interface SelectNumberService {
    public MtData  selectNumber(String iccid, PositionMo positionMo, DeviceInitRec deviceInitRec) throws Exception;
    public PlainDataMt selectLocalNumber(AssetOrder assetOrder, PositionMo positionMo,
                                         DeviceInitRec deviceInitRec) throws Exception;
    public boolean checkMccAndDevice(String mcc, List<DeviceInitRec> deviceInitRecs);
}

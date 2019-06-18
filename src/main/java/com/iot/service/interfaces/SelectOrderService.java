package com.iot.service.interfaces;

import com.iot.otaBean.assetOrder.AssetOrder;
import com.iot.otaBean.deviceInitRec.DeviceInitRec;
import com.iot.otaBean.mo.PositionMo;

public interface SelectOrderService {
    public AssetOrder  selectOrder(PositionMo positionMo, DeviceInitRec deviceInitRec) throws Exception;
}

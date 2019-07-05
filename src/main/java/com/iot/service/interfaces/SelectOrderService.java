package com.iot.service.interfaces;

import com.iot.otaBean.assetOrder.AssetOrder;
import com.iot.otaBean.base.IccidMccPair;
import com.iot.otaBean.deviceInitRec.DeviceInitRec;
import com.iot.otaBean.mo.PositionMo;

import java.util.List;

public interface SelectOrderService {
    public AssetOrder  selectOrder(PositionMo positionMo, DeviceInitRec deviceInitRec) throws Exception;
    /**
     * LU下发副号服务的选择订单功能
     */
    AssetOrder getOrder(String iccid, String mcc) throws Exception;

}

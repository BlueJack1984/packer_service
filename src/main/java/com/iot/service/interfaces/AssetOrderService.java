package com.iot.service.interfaces;

import com.iot.otaBean.assetOrder.AssetOrder;

import java.util.List;

/**
 *
 */
public interface AssetOrderService {

    /**
     *
     */
    List<AssetOrder> getListByIccids(List<String> iccidList);
}

package com.iot.service.interfaces;

import com.iot.otaBean.assetOrderSoftsimUsage.AssetOrderSoftsimUsage;

import java.util.List;

public interface AssetOrderSoftsimUsageService {

    /**
     * 获取码号使用信息列表
     */
    List<AssetOrderSoftsimUsage> getListByIccids(List<String> iccidList);
}

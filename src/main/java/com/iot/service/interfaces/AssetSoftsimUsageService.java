package com.iot.service.interfaces;

import com.iot.otaBean.assetSoftsimUsage.AssetSoftsimUsage;

import java.util.List;

/**
 *
 */
public interface AssetSoftsimUsageService {

    /**
     * 获取码号使用信息列表
     */
    List<AssetSoftsimUsage> getListByImsi(String imsi);
}

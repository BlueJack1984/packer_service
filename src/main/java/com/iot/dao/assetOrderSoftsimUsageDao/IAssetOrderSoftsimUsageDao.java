package com.iot.dao.assetOrderSoftsimUsageDao;

import com.iot.otaBean.assetOrderSoftsimUsage.AssetOrderSoftsimUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAssetOrderSoftsimUsageDao {

    List<AssetOrderSoftsimUsage> getList(@Param("imei") String imei, @Param("orderId") String orderId);
}

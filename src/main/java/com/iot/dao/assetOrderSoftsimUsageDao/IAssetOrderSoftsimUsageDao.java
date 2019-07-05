package com.iot.dao.assetOrderSoftsimUsageDao;

import com.iot.otaBean.assetOrderSoftsimUsage.AssetOrderSoftsimUsage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAssetOrderSoftsimUsageDao {

    /**
     *
     * @param imei
     * @param orderId
     * @return
     */
    List<AssetOrderSoftsimUsage> getList(@Param("imei") String imei, @Param("orderId") String orderId);

    /**
     *
     * @param iccidList
     * @return
     */
    List<AssetOrderSoftsimUsage> getListByIccids(@Param("iccidList") List<String> iccidList);
}

package com.iot.dao.assetManageBusiDao;

import com.iot.otaBean.assetManageBusi.AssetManageBusi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IAssetManageBusiDao {
    public AssetManageBusi queryUnfinishBusiByAssetIdAndType(@Param("assetId") String assetId, @Param("busiType")String busiType);
    public Long getOtaTradeNo();
}

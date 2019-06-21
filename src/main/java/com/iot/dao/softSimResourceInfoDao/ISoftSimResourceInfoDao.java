package com.iot.dao.softSimResourceInfoDao;

import com.iot.otaBean.softSimResourceInfo.SoftSimResourceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ISoftSimResourceInfoDao {
    public List<SoftSimResourceInfo> querySoftsimByImsi(@Param("imsi") String imsi);
    public List<SoftSimResourceInfo> querySoftsimByIccid(@Param("iccid") String iccid);
}

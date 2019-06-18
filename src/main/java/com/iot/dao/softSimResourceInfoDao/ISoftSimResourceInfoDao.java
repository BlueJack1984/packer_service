package com.iot.dao.softSimResourceInfoDao;

import com.iot.otaBean.softSimResourceInfo.SoftSimResourceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ISoftSimResourceInfoDao {
    public List<SoftSimResourceInfo> querySoftsimByImsi(String imsi);
    public List<SoftSimResourceInfo> querySoftsimByIccid(String iccid);
}

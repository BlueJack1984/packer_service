package com.iot.dao.softSimResourceImsiDao;

import com.iot.otaBean.softSimResourceImsi.SoftSimResourceImsi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISoftSimResourceImsiDao {
    public List<SoftSimResourceImsi> querySoftsimResourceImsi(@Param("iccid") String iccid, @Param("imsi") String imsi);
}

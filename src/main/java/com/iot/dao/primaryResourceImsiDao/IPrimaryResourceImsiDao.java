package com.iot.dao.primaryResourceImsiDao;

import com.iot.otaBean.primaryResourceImsi.PrimaryResourceImsi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPrimaryResourceImsiDao {
    List<PrimaryResourceImsi> queryPriByIccidAndImsi(@Param("iccid") String iccid, @Param("imsi") String imsi);
}

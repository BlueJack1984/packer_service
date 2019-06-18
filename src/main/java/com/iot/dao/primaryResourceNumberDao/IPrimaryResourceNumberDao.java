package com.iot.dao.primaryResourceNumberDao;

import com.iot.otaBean.primaryResourceNumber.PrimaryResourceNumber;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface IPrimaryResourceNumberDao {
    public List<PrimaryResourceNumber> queryPrimaryByIccid(String iccid);
}

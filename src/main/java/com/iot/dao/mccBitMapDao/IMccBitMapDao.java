package com.iot.dao.mccBitMapDao;

import com.iot.otaBean.mccBitMap.MccBitMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface IMccBitMapDao {
    /**
     * 查询所有mcc bitmap
     *
     * @return
     */
    public List<MccBitMap> getAllMccBitMap();
}

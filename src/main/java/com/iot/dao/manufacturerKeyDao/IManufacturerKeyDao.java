package com.iot.dao.manufacturerKeyDao;

import com.iot.otaBean.manufacturerKey.ManufacturerKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IManufacturerKeyDao {
    /**
     * 根据密钥类型查询密钥信息
     *
     * @param keyType
     * @return
     */
    public List<ManufacturerKey> getKeyByType(String keyType);

}

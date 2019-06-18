package com.iot.dao.supplierDao;

import com.iot.otaBean.supplier.Supplier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISupplierDao {
    Supplier querySupplierByCode(String supplierCode);
}

package com.iot.service.serviceImpl;

import com.iot.dao.assetOrderDao.IAssetOrderDao;
import com.iot.otaBean.assetOrder.AssetOrder;
import com.iot.service.interfaces.AssetOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssetOrderServiceImpl implements AssetOrderService {

    private final IAssetOrderDao assetOrderDao;

    @Override
    public List<AssetOrder> getListByIccids(List<String> iccidList) {
        return null;
    }
}

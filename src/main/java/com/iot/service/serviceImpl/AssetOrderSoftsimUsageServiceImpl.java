package com.iot.service.serviceImpl;

import com.iot.dao.assetOrderSoftsimUsageDao.IAssetOrderSoftsimUsageDao;
import com.iot.otaBean.assetOrderSoftsimUsage.AssetOrderSoftsimUsage;
import com.iot.service.interfaces.AssetOrderSoftsimUsageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssetOrderSoftsimUsageServiceImpl implements AssetOrderSoftsimUsageService {

    private final IAssetOrderSoftsimUsageDao assetOrderSoftsimUsageDao;


    @Override
    public List<AssetOrderSoftsimUsage> getListByIccids(List<String> iccidList) {
        List<AssetOrderSoftsimUsage> list = assetOrderSoftsimUsageDao.getListByIccids(iccidList);
        return list;
    }
}

package com.iot.service.serviceImpl;

import com.iot.dao.assetOrderDao.IAssetOrderDao;
import com.iot.otaBean.assetOrder.AssetOrder;
import com.iot.otaBean.base.IccidMccPair;
import com.iot.otaBean.deviceInitRec.DeviceInitRec;
import com.iot.otaBean.mo.PositionMo;
import com.iot.service.interfaces.SelectOrderService;
import com.iot.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SelectOrderServiceImpl implements SelectOrderService {
    @Autowired
    IAssetOrderDao assetOrderDao;
    public AssetOrder selectOrder(PositionMo positionMo, DeviceInitRec deviceInitRec) throws Exception{
        AssetOrder assetOrder = new AssetOrder();
        String now = DateUtils.format(new Date(), DateUtils.YMD_DASH_WITH_TIME_SECOND);
        List<AssetOrder> startedOrders = assetOrderDao.queryStartedOrders(positionMo.getImei(),now);
        if(0 != startedOrders.size()){
            assetOrder = startedOrders.get(0);
            return assetOrder;
        }
        List<AssetOrder> pauseOrders = assetOrderDao.queryPauseOrders(positionMo.getImei(),positionMo.getMcc(),now);
        if(0 != pauseOrders.size()){
            assetOrder = pauseOrders.get(0);
            return assetOrder;
        }
        List<AssetOrder> notStartedOrders = assetOrderDao.queryNotStartedOrders(positionMo.getImei(), positionMo.getMcc());
        if(0 != notStartedOrders.size()){
            assetOrder = notStartedOrders.get(0);
            return assetOrder;
        }
        return null;
    }

    /**
     * @param iccid 旅游卡的iccid，等同于设备的imei
     * @param mcc 覆盖国家范围
     * @return
     * @throws Exception
     */
    @Override
    public AssetOrder getOrder(String iccid, String mcc) throws Exception{
        PositionMo positionMo = new PositionMo();
        //针对旅游卡
        positionMo.setImei(iccid);
        positionMo.setMcc(mcc);
        AssetOrder assetOrder = selectOrder(positionMo, null);
        return assetOrder;
    }
}

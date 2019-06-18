package com.iot.service.serviceImpl;

import com.iot.dao.assetManageBusiDao.IAssetManageBusiDao;
import com.iot.dao.softSimResourceInfoDao.ISoftSimResourceInfoDao;
import com.iot.otaBean.mo.PositionMo;
import com.iot.otaBean.softSimResourceInfo.SoftSimResourceInfo;
import com.iot.service.interfaces.PushCommandService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushCommandServiceImpl implements PushCommandService {
    private static final Log logger = LogFactory.getLog(PushCommandServiceImpl.class);
    @Autowired
    ISoftSimResourceInfoDao softSimResourceInfoDao;
    @Autowired
    IAssetManageBusiDao assetManageBusiDao;
    public boolean softsimRelease(String imei){
        //当前imsi在码号表中状态
        List<SoftSimResourceInfo> softSimResourceInfos = softSimResourceInfoDao.querySoftsimByImsi(imei);
        if(1 != softSimResourceInfos.size()){
            logger.error("softsim码号重复存储或不存在！");
            return false;
        }
        SoftSimResourceInfo softSimResourceInfo = softSimResourceInfos.get(0);
        if(softSimResourceInfo.getStatus().equals("00")){
            return true;
        }
        return false;
    }
    public boolean getPushCommand(String imei, String busiType){
        if(null != assetManageBusiDao.queryUnfinishBusiByAssetIdAndType(imei,busiType)){
            return true;
        }
        return false;
    }
    public String switchGlobalBusi(PositionMo positionMo, String imei){


        return null;
    }
}

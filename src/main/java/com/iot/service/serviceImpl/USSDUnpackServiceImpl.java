package com.iot.service.serviceImpl;

import com.iot.DEScrypto.DESCrypto;
import com.iot.constant.SysConstants;
import com.iot.controller.USSDController;
import com.iot.otaBean.mo.BaseMo;
import com.iot.otaBean.mo.PorMo;
import com.iot.otaBean.mo.PositionMo;
import com.iot.otaBean.mo.RecieveDataPorMo;
import com.iot.otaBean.mt.*;
import com.iot.service.interfaces.USSDUnpackService;
import com.iot.util.HexStr;
import com.iot.util.JsonUtil;
import com.packer.commons.sms.packet.ValueTypeException;
import com.packer.extension.convertor.ADNConvertor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.trim;

@Service("ussdBusiServiceUnpack")
public class USSDUnpackServiceImpl implements USSDUnpackService {
    private static final Log logger = LogFactory.getLog(USSDController.class);

    public BaseMo ussdBusiServiceUnpack(String msg) {
        logger.info(msg.toString());
        byte[] USSDByteData = msg.getBytes();
        byte[] LocationUploadData = new byte[200];
        String HexIV = "0000000000000000";
        System.arraycopy(USSDByteData, (short)0, LocationUploadData, (short) 0, (short)1);   //保留前1个字节不变
        UnpackUSSD(USSDByteData, (short)1, LocationUploadData, (short)1, (short)(USSDByteData.length-2));
        msg = new String(LocationUploadData);

        BaseMo baseMo = new BaseMo();
        ADNConvertor ascii = new ADNConvertor();
        try {
            if (msg.substring(SysConstants.CmdType_OFF, SysConstants.CipherText_OFF).equals("0")) {//位置上报
                logger.info("位置上报MO解析");
                String[] keys = com.iot.constant.SysConstants.OTA_COMM_KEY_MAP.get(
                        msg.substring(SysConstants.ManufacturerFlag_OFF, SysConstants.AppletVersion_OFF));
                String plainText = "";
                if("#".equals(msg.substring(trim(msg).length() - 1, trim(msg).length())) &&
                        !"#".equals(msg.substring(msg.length() - 2, msg.length() - 1))){
                    plainText= DESCrypto.des_cbc_decrypt(keys[Integer.parseInt(msg.substring(
                            SysConstants.KeyID_OFF, SysConstants.ManufacturerFlag_OFF)) - 1],
                            msg.substring(SysConstants.CipherText_OFF, (trim(msg).length() - 1)), HexIV);
                }else if("##".equals(msg.substring(trim(msg).length() - 2, trim(msg).length())) &&
                        !"#".equals(msg.substring(trim(msg).length() - 3, trim(msg).length() - 2))){
                    plainText = DESCrypto.des_cbc_decrypt(keys[Integer.parseInt(msg.substring(
                            SysConstants.KeyID_OFF, SysConstants.ManufacturerFlag_OFF)) - 1],
                            msg.substring(SysConstants.CipherText_OFF, (trim(msg).length() - 2)), HexIV);
                }else {
                    logger.error("MO数据错误，数据末尾#超过了两个！");
                    return null;
                }
                PositionMo positionMo = new PositionMo();
                positionMo.setCheckNum(plainText.substring(0, 4));
                positionMo.setMcc(plainText.substring(4, 7));
                positionMo.setMnc(plainText.substring(7, 10));
                positionMo.setpIccid(ascii.reconvert(plainText.substring(10, 30)));
                positionMo.setCarryingCapacity(plainText.substring(30, 32));
/*                if (positionMo.getCarryingCapacity().equals("00")) {
                    positionMo.setCarryingCapacity("0x0000FFFF");
                }*/
                //int len = Integer.parseInt(plainText.substring(32, 34)) * 2;
                positionMo.setImei(plainText.substring(32, 47));
                analyzeTLV(plainText.substring(48, plainText.length()), positionMo);
                //positionMo.setLatestTradeNo(msg.substring(SysConstants.Latest_Trade_No_OFF,
                        //SysConstants.Carrying_Capacity_OFF + SysConstants.Latest_Trade_No_LEN));

                baseMo = (BaseMo) positionMo;
                logger.info("MO消息解析：" + baseMo.toString());

            } else if ((msg.substring(SysConstants.CmdType_OFF, SysConstants.CipherText_OFF).equals("1"))
                    || (msg.substring(SysConstants.CmdType_OFF, SysConstants.CipherText_OFF).equals("2"))
                    || (msg.substring(SysConstants.CmdType_OFF, SysConstants.CipherText_OFF).equals("3"))) {//Success POR
                logger.info("成功POR解析");
                PorMo porMo = new PorMo();
                porMo.setImei(msg.substring(SysConstants.IMEI_OFF,
                        SysConstants.IMEI_OFF + SysConstants.IMEI_LEN));
                porMo.setOtaTradeNo(msg.substring(SysConstants.TRADE_ID_OFF, SysConstants.IMEI_OFF));
                baseMo = (BaseMo) porMo;
            } else if (baseMo.getCmdType().equals("9")) {//Retry POR
                logger.info("成功RETRY POR解析");
                RecieveDataPorMo recieveDataPorMo = new RecieveDataPorMo();
                recieveDataPorMo.setIccid(msg.substring(SysConstants.ICCID_OFF,
                        SysConstants.IMEI_OFF));
                recieveDataPorMo.setImei(msg.substring(SysConstants.IMEI_OFF,
                        SysConstants.BATCH_NUM_OFF));
                recieveDataPorMo.setBatchNum(msg.substring(SysConstants.BATCH_NUM_OFF,
                        SysConstants.REQUEST_INDEX_OFF));
                recieveDataPorMo.setRetryIndex(msg.substring(SysConstants.REQUEST_INDEX_OFF,
                        SysConstants.REQUEST_INDEX_OFF + SysConstants.REQUEST_INDEX_LEN));
                baseMo = (BaseMo) recieveDataPorMo;
            } else {
                logger.error("命令类型cmdType非法，退出！");
                return null;
            }
        } catch (Exception e) {
            logger.error("解析ussd通道消息失败！", e);
            return null;
        }
        baseMo.setCmdType(HexStr.ascToHex(msg.substring(SysConstants.CmdType_OFF, SysConstants.CipherText_OFF)));
        baseMo.setBusiType(msg.substring(SysConstants.BusiType_OFF, SysConstants.KeyID_OFF));
        baseMo.setProtocolVersion(msg.substring(6, 7));
        baseMo.setManuFlag(msg.substring(SysConstants.ManufacturerFlag_OFF, SysConstants.AppletVersion_OFF));
        baseMo.setKeyIndex(msg.substring(SysConstants.KeyID_OFF, SysConstants.ManufacturerFlag_OFF));
        return baseMo;
    }
    public void analyzeTLV(String sms, PositionMo positionMo){
        int len = 0;
        String v = null;
        List<String> pImsis = new ArrayList<>();
        List<String> sImsis = new ArrayList<>();
        String cellid = null;
        String tradeNo = null;
        String imsi = null;
        try {
            imsi = sms.substring(2, 18);
            sms = sms.substring(18, trim(sms).length());
            while (sms.length() > 0){
                if("00".equals(sms.substring(0, 2))){
                    break;
                }
                len = Integer.parseInt(sms.substring(2, 4), 16);
                v = sms.substring(4, 4 + len * 2);
                if("01".equals(sms.substring(0, 2))) {
                    pImsis.add(v);
                }else if( "02".equals(sms.substring(0, 2)) || "03".equals(sms.substring(0, 2))){
                    sImsis.add(v);
                }else if("04".equals(sms.substring(0, 2))){
                    cellid = v;
                }else if("05".equals(sms.substring(0,2))){
                    tradeNo = v;
                }
                sms = sms.substring(len * 2 + 4, trim(sms).length());
            }
            positionMo.setImsi(imsi);
            positionMo.setpImsis(pImsis);
            positionMo.setpImsis(sImsis);
            positionMo.setCellId(cellid);
            positionMo.setLatestTradeNo(tradeNo);
        } catch (Exception var6) {
            throw new ValueTypeException("Type parse error!! sms value:[" + sms.toString() + "]");
        }
        return ;
    }
    public String ussdBusiServicePack(String reqStr) throws Exception {
        MtData mtData = new MtData();
        mtData = (MtData) JsonUtil.getDTO(reqStr, MtData.class);

        List<PlainDataMt> plainDataMtList = mtData.getPlainDatas();
        PlainDataMt plainDataMt = new PlainDataMt();
        String cmdType = plainDataMt.getCmdType();
        String checkNum = mtData.getCheckNum();
        String deliverData = null;
        String userData = null;
        String HexIV = "0000000000000000";
        int cipherdataLen = 0;
        String MAC = null;
        String SMS = null;

        for(int i = 0; i < plainDataMtList.size(); i++){
            plainDataMt = plainDataMtList.get(i);
            if(plainDataMt.getCmdType().equals("33")){
                CmdParamData cmdParamData = (CmdParamData) plainDataMt.getCmdParams();
                deliverData = cmdParamData.getOtaTradeNo() +
                        cmdParamData.getCallControl() +
                        cmdParamData.getpIccid();
                CascadePushCommandCMD cascadePushCommandCMD = null;
                for(int j = 0; j < cmdParamData.getCmds().size(); j++){
                    cascadePushCommandCMD = cmdParamData.getCmds().get(i);
                    deliverData += cascadePushCommandCMD.getTag() +
                            cascadePushCommandCMD.getLength() +
                            cascadePushCommandCMD.getValue();
                }
                userData += cmdType + deliverData.length() + deliverData;



            }else if(plainDataMt.getCmdType().equals("31") || plainDataMt.getCmdType().equals("32")){
                CmdParamData cmdParamData = (CmdParamData) plainDataMt.getCmdParams();
                deliverData = cmdParamData.getOtaTradeNo() +
                        cmdParamData.getCallControl() +
                        cmdParamData.getOldIccid() +
                        cmdParamData.getImsi() +
                        cmdParamData.getAlgFlag()+
                        cmdParamData.getDataKeyIndex() +
                        cmdParamData.getKeyData() +
                        organizeData(cmdParamData.getExpTime()) +
                        organizeData(cmdParamData.getCoverMcc()) +
                        organizeData(cmdParamData.getUssdPrefix()) +
                        organizeData(cmdParamData.getNewIccid()) +
                        organizeData(cmdParamData.getApn()) +
                        organizeData(cmdParamData.getSca()) +
                        organizeData(cmdParamData.getTelData()) +
                        organizeData(cmdParamData.getPlmn()) +
                        organizeData(cmdParamData.getBipParam()) +
                        organizeData(cmdParamData.getFplmn());
                userData += cmdType + deliverData.length() +deliverData;
            }
        }
        userData = checkNum + userData;
        String cipherdata = DESCrypto.des_cbc_encrypt(mtData.getKeyIndex(), userData, HexIV);
        cipherdata = "0" + mtData.getBusiType() + "0" + mtData.getKeyIndex() + cipherdata;
        String len = Integer.toHexString(cipherdata.length()/2 + 4);
        if(1 == len.length()){
            len = "000" + len;
        }else if(2 == len.length()){
            len = "00" + len;
        }else if(3 == len.length()){
            len = "0" + len;
        }else if(4 == len.length()){

        }else {
            logger.info("数据长度超过两字节，无法处理！");
            return null;
        }
        cipherdata = len + cipherdata;
        cipherdata = DESCrypto.des_cbc_decrypt(mtData.getKeyIndex(), cipherdata, HexIV);
        cipherdataLen = cipherdata.length();
        MAC = cipherdata.substring((cipherdataLen - 8), cipherdataLen);
        SMS = cipherdata + MAC;
        return SMS;
    }
    private String organizeData(String str){
        if((null == str) || ("".equals(str))){
            return "00";
        }else {
            return str;
        }
    }
    private short UnpackUSSD(byte[] src, short inoff, byte[] dst,short outoff,short len) {

        short off_end = (short)(inoff+len),dst_start = outoff;
        do{
            if(src[inoff]==0x2A){
                inoff++;
                switch (src[(short)(inoff++)])
                {
                    case (byte) 0x30://A
                        dst[outoff++] = 0x41;
                        break;
                    case (byte) 0x31://B
                        dst[outoff++] = 0x42;
                        break;
                    case (byte) 0x32://C
                        dst[outoff++] = 0x43;
                        break;
                    case (byte) 0x33://D
                        dst[outoff++] = 0x44;
                        break;
                    case (byte) 0x34://E
                        dst[outoff++] = 0x45;
                        break;
                    case (byte) 0x35://F
                        dst[outoff++] = 0x46;
                        break;
                    case (byte) 0x36://G
                        dst[outoff++] = 0x47;
                        break;
                    case (byte) 0x37://H
                        dst[outoff++] = 0x48;
                        break;
                    case (byte) 0x38://I
                        dst[outoff++] = 0x49;
                        break;
                    default:
                }

            }else{
                dst[outoff++] = src[(short)(inoff++)];
            }
        }while(inoff<=off_end);
        return len = (short)(outoff - dst_start);
    }
}

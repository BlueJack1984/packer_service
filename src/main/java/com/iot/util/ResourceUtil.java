package com.iot.util;


import com.iot.constant.SysConstants;
import com.iot.controller.BipController;
import com.iot.otaBean.primaryResourceNumber.PrimaryResourceNumber;
import com.iot.otaBean.softSimResourceInfo.SoftSimResourceInfo;
import com.packer.commons.sms.crypto.LF3DesCryptoUtil;
import com.packer.commons.sms.jce.JceBase;
import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 副号数据处理工具
 * 
 * @author Tiger
 * 
 */
public class ResourceUtil {

	private static final Log logger = LogFactory.getLog(ResourceUtil.class);
	/**
	 * 计算数据保护sessionKey
	 *
	 * @param key
	 * @param pIccid
	 * @param otaTradeNo
	 * @return
	 */
	public static String calcSessionKey(String key, String pIccid,
			String otaTradeNo) {
		String adnPiccid = LFStringUtil.adn2string(pIccid);
		String cKey = LF3DesCryptoUtil.ecb_encrypt(
				key,
				StringUtil.paddingTailMutipBy16(adnPiccid.substring(10, 20)
						+ adnPiccid.substring(0, 10) + "80", "00"),
				JceBase.Padding.NoPadding);
		String sessionKey = LF3DesCryptoUtil.ecb_encrypt(cKey,
				StringUtil.paddingTailMutipBy16(otaTradeNo + "80", "00"),
				JceBase.Padding.NoPadding);
		return sessionKey;
	}

	/**
	 * 生成覆盖国bitmap信息
	 *
	 * @param cardTypeMcc
	 * @param
	 * @return
	 */
	public static String genMccBitMap(String cardTypeMcc) {
		List<String> orderMccList = new ArrayList<String>();
		String[] coverCountries = cardTypeMcc.split(",|;");
		//TODO 数组去重
		orderMccList.addAll(Arrays.asList(coverCountries));
		int[] bc = new int[240];
		for (String s : orderMccList) {
			String mccCode = SysConstants.MCC_BIT_MAP.get(s);
			if(null == mccCode) {
				continue;
			}
			bc[Integer.parseInt(mccCode) - 1] = 1;
		}
		String bitMapStr = "";
		for (int i = 0; i < bc.length / 8; i++) {
			String temp = "";
			for (int j = i * 8; j < (i + 1) * 8; j++) {
				temp = temp + bc[j];
			}
			int it = Integer.parseInt(temp, 2);
			temp = StringUtil.paddingHeadZero(Integer.toHexString(it), 2);
			bitMapStr = bitMapStr + temp;
		}
		return bitMapStr;
	}

	/**
	 * 将apn转换为设备需要的格式(LV格式 V为ascii)
	 *
	 * @param apn
	 * @return
	 */
	public static String changeApn(String apn) {
		String apnAsc = StringUtil.asc2hex(apn);
		int apnLen = apnAsc.length() / 2;
		String chApn = StringUtil.paddingHeadZero(
				Integer.toString(apnLen, 16), 2)
				+ apnAsc;
		return chApn;
//    return apnAsc;
	}

	public static String getEfImsi(SoftSimResourceInfo softSimResourceInfo,
							 String imsi) {
		if(imsi.equals(softSimResourceInfo.getImsi())){
			return softSimResourceInfo.getEfimsi();
		}else if(imsi.equals(softSimResourceInfo.getImsi2())){
			return softSimResourceInfo.getEfimsi2();
		}else if(imsi.equals(softSimResourceInfo.getImsi3())){
			return softSimResourceInfo.getEfimsi3();
		}else if(imsi.equals(softSimResourceInfo.getImsi4())){
			return softSimResourceInfo.getEfimsi4();
		}else if(imsi.equals(softSimResourceInfo.getImsi5())){
			return softSimResourceInfo.getEfimsi5();
		}
		logger.info("通过imsi获取efimsi失败");
		return null;
	}
	public static String getEfImsi(PrimaryResourceNumber primaryResourceNumber,
								   String imsi) {
		if(imsi.equals(primaryResourceNumber.getImsi())){
			return primaryResourceNumber.getEfimsi();
		}else if(imsi.equals(primaryResourceNumber.getImsi2())){
			return primaryResourceNumber.getEfimsi2();
		}else if(imsi.equals(primaryResourceNumber.getImsi3())){
			return primaryResourceNumber.getEfimsi3();
		}else if(imsi.equals(primaryResourceNumber.getImsi4())){
			return primaryResourceNumber.getEfimsi4();
		}else if(imsi.equals(primaryResourceNumber.getImsi5())){
			return primaryResourceNumber.getEfimsi5();
		}
		logger.info("通过imsi获取efimsi失败");
		return null;
	}
}

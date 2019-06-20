package com.iot.util;


import com.iot.constant.SysConstants;
import com.packer.commons.sms.crypto.LF3DesCryptoUtil;
import com.packer.commons.sms.jce.JceBase;
import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.util.StringUtil;

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

}

package com.iot.util;


import com.packer.commons.sms.crypto.LF3DesCryptoUtil;
import com.packer.commons.sms.jce.JceBase;
import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.util.StringUtil;

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

}

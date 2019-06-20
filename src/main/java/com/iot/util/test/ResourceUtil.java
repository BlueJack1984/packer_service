package com.iot.util.test;


import com.iot.constant.SysConstants;
import com.iot.otaBean.plmn.PlmnIndex;
import com.iot.otaBean.softSimResourceInfo.SoftSimResourceInfo;
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

	//public String getPLMNIndexList(String MCC,String PLMNList)
	public static String getPLMNIndexList(String[] mccArr,short MCCIndex,String PLMNList)
	{
		int len = PLMNList.length();
		String plmn_list = "";
		String plmn = null;
		short offset = 0;
		String index = null;
		int num = 0;
		String MCC_INDEX = "";
		String NUM = "";
		String MCC = mccArr[MCCIndex];

		//plmn_list = Integer.toHexString(len/6);
		//if (plmn_list.length() == 1) {
		//	plmn_list = '0' + plmn_list;
		//}

		while(offset < len)
		{
			plmn = PLMNList.substring(offset,offset+6);

			index = getPLMNIndex2(MCC,plmn);
			if(index != "")
			{
				num++;
			}
			plmn_list += index;

			offset += 6;
		}

		if(plmn_list.equals(""))
		{
			return "";
		}

		//PLMN长度和数量相同，则表示是一个字节代表的index（即MCC对应的PLMNlist总数大于16），num字段最高bit值1
		if(plmn_list.length() == num*2)
		{
			num |= (int)0x00000080;
		}

		//使用当前传入的覆盖国家的数组的下标作为index
//			MCC_INDEX = Integer.toHexString((int)(MCCIndex&0x0000FFFF));

		//使用mcc_bit_map_t里的bitmap_code的16进制作为index
		String mccCode = SysConstants.MCC_BIT_MAP.get(MCC);
		int i = Integer.parseInt(mccCode) - 1;
		MCC_INDEX = Integer.toHexString(i);


		if (MCC_INDEX.length() == 1) {
			MCC_INDEX = '0' + MCC_INDEX;
		}

		NUM = Integer.toHexString(num);
		if (NUM.length() == 1) {
			NUM = '0' + NUM;
		}

		plmn_list = MCC_INDEX + NUM + plmn_list;

		//非整字节，补F
		if((plmn_list.length()%2) != 0)
		{
			plmn_list += "F";
		}

		return plmn_list;
	}

	public static final byte[] byte_mask = { (byte) 0x80, (byte) 0x40,(byte) 0x20,(byte) 0x10, (byte) 0x08,(byte) 0x04,(byte) 0x02, (byte) 0x01};
	/**
	 * 示例一   覆盖国家:202,206,208   plmn:02F24102F25102F69002F820   plmn_index_list:0a0d820c0d0f8107108101
	 * 示例二   覆盖国家:202,206,208,510   plmn:02F24102F25102F69002F820   plmn_index_list:0a0d820c0d0f8107108101
	 * 1、如果覆盖国家没有对应的plmn，则不会查找对应的plmn。
	 *
	 * @param PLMNList
	 * @param mccs
	 * @return
	 */
	public static String generatePLMNIndexList(String PLMNList,String mccs)
	{
		System.out.println("覆盖国家:" + mccs);
		System.out.println("plmn:" + PLMNList);
		String[] mccArr = mccs.split(",|;");
		List<Integer> MCCBitmap = genMccBitMapforPlmn(mccArr);

		String plmn_index_list = "";
		byte index_map = 0,index_byte = 0,and = 0,mask = 0;
		short mcc_off = 0;
		byte bit_map_len = 30;
		byte byte_bits = 8;

		if(PLMNList == "")
		{
			return "00";
		}

		do{
			index_byte = 0;
			do{
				mask = byte_mask[index_byte];
				and = (byte)(MCCBitmap.get(index_map)&mask);
				if(mask == and)
				{
					//plmn_index_list += getPLMNIndexList(MCCList[mcc_off],PLMNList);
					if(mcc_off >= mccArr.length){
						String LEN = Integer.toHexString(plmn_index_list.length()/2);
						if(LEN.length() == 1)
						{
							LEN = "0" + LEN;
						}
						plmn_index_list = LEN + plmn_index_list;
						System.out.println("plmn_index_list:" + plmn_index_list);
						return plmn_index_list;
					}else{
						plmn_index_list += getPLMNIndexList(mccArr,mcc_off,PLMNList);
					}
					mcc_off ++;
				}

			}while(++index_byte<byte_bits);
		}while(++index_map<bit_map_len);
		String LEN = Integer.toHexString(plmn_index_list.length()/2);
		if(LEN.length() == 1)
		{
			LEN = "0" + LEN;
		}
		plmn_index_list = LEN + plmn_index_list;
		System.out.println("plmn_index_list:" + plmn_index_list);
		return plmn_index_list;
	}
	public static String getPLMNIndex2(String mcc,String PLMN)
	{
		PlmnIndex index = SysConstants.PLMN_INDEX_MAP.get(PLMN);
		if(index == null) return "";
		if(index.getMcc().equals(mcc)) return index.getPlmnIndex();
		return "";
	}
	/**
	 * 生成覆盖国bitmap信息,生产plmn使用
	 *
	 * @param coverCountries
	 * @param
	 * @return
	 */
	public static List<Integer> genMccBitMapforPlmn(String[] coverCountries) {
		List<String> orderMccList = new ArrayList<String>();
		//TODO 数组去重
		orderMccList.addAll(Arrays.asList(coverCountries));
		int[] bc = new int[240];
		for (String s : orderMccList) {
			String mccCode = SysConstants.MCC_BIT_MAP.get(s);
			bc[Integer.parseInt(mccCode) - 1] = 1;
		}
		List<Integer> bitMapList = new ArrayList<>();
		for (int i = 0; i < bc.length / 8; i++) {
			String temp = "";
			for (int j = i * 8; j < (i + 1) * 8; j++) {
				temp = temp + bc[j];
			}
			int it = Integer.parseInt(temp, 2);
//			temp = WDStringUtil.paddingHeadZero(Integer.toHexString(it), 2);
			bitMapList.add(it);
		}
		return bitMapList;

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
//		return apnAsc;
	}
	private String getEfImsi(SoftSimResourceInfo softSimResourceInfo,
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
		//logger.info("通过imsi获取efimsi失败");
		return null;
	}
}

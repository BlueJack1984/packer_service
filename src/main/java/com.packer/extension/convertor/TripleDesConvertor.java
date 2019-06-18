package com.packer.extension.convertor;

//import com.iot.ota.constant.SysConstants;
import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.crypto.LF3DesCryptoUtil;
import com.packer.commons.sms.jce.JceBase.Padding;
import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.packet.ConverterException;
import com.packer.commons.sms.packet.DataManager;
import com.packer.commons.sms.packet.IValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TripleDesConvertor.java
 * 
 * @description：3Des加解密转换器
 * @version: 1.0.0
 * @modify:
 */

public class TripleDesConvertor implements IValueConverter {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3078977962809721258L;

	private final Logger log = LoggerFactory.getLogger(getClass());

	private Property appEnv = new Property("应用场景(asset|script)", "", "asset",
			new String[] { "asset", "script" });

	// 加密模式cbc|ecb,默认为ecb
	private Property mode = new Property("加密模式(cbc|ecb)", "", "cbc",
			new String[] { "cbc", "ecb" });

	// 初始化向量 由配置文件读入 默认为8字节0
	private Property iv = new Property("初始值iv", "", "0000000000000000");

	// 密钥索引
	private Property keyIdx = new Property("密钥索引keyIdx", "", "请输入作为密钥索引的报文字段名称");

	// 密钥组
	private Property keys = new Property("密钥组keys", "", "请输入五组密钥");

	@SuppressWarnings("unchecked")
	@Override
	public String convert(String value) throws ConverterException {

		try {

			String keyNo = DataManager.getCurrentPacketData()
					.getFieldData("keyIndex").getInputData();
			String manuFlag = (String) DataManager.getParameterData("manuFlag");
			if (manuFlag == null || manuFlag.trim().length() == 0) {
				manuFlag = "0";
			}
			String[] keys = SysConstants.OTA_COMM_KEY_MAP.get(manuFlag);
			if (keys == null) {
				throw new ConverterException("加密时异常，无法获取解密密钥，明文:[" + value
						+ "]");
			}
			String key = keys[Integer.parseInt(keyNo) - 1];// getCryptoKey(keyNo);
			value = padding(value);
			String crypText = LF3DesCryptoUtil.cbc_encrypt(key, value,
					Padding.NoPadding, iv.getValue());
			log.debug("明文数据：[" + value + "]");
			log.debug("密文数据：[" + crypText + "]");
			return crypText;
		} catch (Exception e) {
			throw new ConverterException("加密时异常，明文:[" + value + "]", e);
		}
	}

	@Override
	public String reconvert(String value) throws ConverterException {
		try {
			String keyNo = DataManager.getCurrentPacketData()
					.getFieldData("keyIndex").getInputData();

			String manuFlag = DataManager.getCurrentPacketData()
					.getFieldData("manuFlag").getInputData();
			String[] keys = SysConstants.OTA_COMM_KEY_MAP.get(manuFlag);
			if (keys == null) {
				throw new ConverterException("解密时异常，无法获取通信密钥，明文:[" + value
						+ "]");
			}
			String key = keys[Integer.parseInt(keyNo) - 1];
			String encryText = LFStringUtil.hex2asc(value);
			log.debug("密文数据：[" + encryText + "]");
			String crypText = LF3DesCryptoUtil.cbc_decrypt(key, encryText,
					Padding.NoPadding, iv.getValue());
			log.debug("明文数据：[" + crypText + "]");
			return crypText;
		} catch (Exception e) {
			throw new ConverterException("解密时异常，密文:[" + value + "]", e);
		}

	}

	/**
	 * 填充待加密数据,补0直到8字节整数倍
	 * 
	 * @param plaindata
	 *            待加密数据
	 * @return 填充后的待解密数据
	 */
	private String padding(String plaindata) {
		return LFStringUtil.paddingTailMutipBy16(plaindata, "0");
	}

	@Override
	public String getName() {
		return "扩展的加解密转换器";
	}

	@Override
	public String getDescription() {
		return "扩展器应用场景、加密模式、密钥索引、初始向量均可配";
	}

	public Property getMode() {
		return mode;
	}

	public void setMode(Property mode) {
		this.mode = mode;
	}

	public Property getIv() {
		return iv;
	}

	public void setIv(Property iv) {
		this.iv = iv;
	}

	public Property getKeyIdx() {
		return keyIdx;
	}

	public void setKeyIdx(Property keyIdx) {
		this.keyIdx = keyIdx;
	}

	public Property getKeys() {
		return keys;
	}

	public void setKeys(Property keys) {
		this.keys = keys;
	}

	public Property getAppEnv() {
		return appEnv;
	}

	public void setAppEnv(Property appEnv) {
		this.appEnv = appEnv;
	}

}

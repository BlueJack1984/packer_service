package com.iot.otaBean.smsTypeEnum;

/**
 * 下行短信类型枚举
 * 
 * @author Administrator
 * 
 */
public enum SmsTypeEnum {
	// 文本短信
	TEXT_SMS("0"),
	// 格式化数据短信
	FMT_DATA_SMS("1"),
	// 非格式化数据短信
	UNFMT_DATA_SMS("2");

	String smsType;

	private SmsTypeEnum(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

}

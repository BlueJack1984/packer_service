package com.iot.otaBean.mt;

import java.util.ArrayList;
import java.util.List;

public class MtData {

	//UdhHeader;
	private String batchNumber; //请求为空，服务无需组装
	private String totalNumber; //请求为空，服务无需组装
	private String currentMessageIndex; //请求为空，服务无需组装
	
	
	private String userData; //请求为空，组装下行无需组装
	private String userdataLen;//请求为空，需服务计算
	private String busiType;//请求非空
	private String keyIndex;//请求非空
	
	//对应消息协议整体结构的Encrypted Data
//	private String encryptedData;//请求为空,需服务计算
	private String checkNum;//请求非空
	private List<PlainDataMt> plainDatas = new ArrayList<>();//请求非空
	
	private String mac;//请求为空,需服务计算
	
	//其他
	private String manuFlag;//厂商编码

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getCurrentMessageIndex() {
		return currentMessageIndex;
	}

	public void setCurrentMessageIndex(String currentMessageIndex) {
		this.currentMessageIndex = currentMessageIndex;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public String getUserdataLen() {
		return userdataLen;
	}

	public void setUserdataLen(String userdataLen) {
		this.userdataLen = userdataLen;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(String keyIndex) {
		this.keyIndex = keyIndex;
	}

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}

	public List<PlainDataMt> getPlainDatas() {
		return plainDatas;
	}

	public void setPlainDatas(List<PlainDataMt> plainDatas) {
		this.plainDatas = plainDatas;
	}

	@Override
	public String toString() {
		return "MtData [batchNumber=" + batchNumber + ", totalNumber="
				+ totalNumber + ", currentMessageIndex=" + currentMessageIndex
				+ ", userData=" + userData + ", userdataLen=" + userdataLen
				+ ", busiType=" + busiType + ", keyIndex=" + keyIndex
				+ ", checkNum=" + checkNum + ", plainDatas=" + plainDatas
				+ ", mac=" + mac + "]";
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getManuFlag() {
		return manuFlag;
	}

	public void setManuFlag(String manuFlag) {
		this.manuFlag = manuFlag;
	}
}

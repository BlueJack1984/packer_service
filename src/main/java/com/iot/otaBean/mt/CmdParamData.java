package com.iot.otaBean.mt;

import java.util.ArrayList;
import java.util.List;

public class CmdParamData {
	//相同属性
	private String otaTradeNo;
	private String callControl;
	
	//pushCommand属性
	private String pIccid;
	private List<CascadePushCommandCMD> cmds = new ArrayList<>();
	
	//下发码号属性
	private String oldIccid;//旧主号，协议新增
	private String imsi;
	private String algFlag;
	private String dataKeyIndex;
	private String keyData;
	private String expTime;// 过期时间
	private String coverMcc;
	private String ussdPrefix; //下主号使用，协议新增
	private String newIccid;//协议新增
	private String apn;
	private String sca;
	private String telData; //电信数据，协议新增
	private String plmn;
	private String bipParam; //bip参数，协议新增
	private String fplmn; //协议新增
	public String getOtaTradeNo() {
		return otaTradeNo;
	}
	public void setOtaTradeNo(String otaTradeNo) {
		this.otaTradeNo = otaTradeNo;
	}
	public String getCallControl() {
		return callControl;
	}
	public void setCallControl(String callControl) {
		this.callControl = callControl;
	}
	public String getpIccid() {
		return pIccid;
	}
	public void setpIccid(String pIccid) {
		this.pIccid = pIccid;
	}
	public List<CascadePushCommandCMD> getCmds() {
		return cmds;
	}
	public void setCmds(List<CascadePushCommandCMD> cmds) {
		this.cmds = cmds;
	}
	public String getOldIccid() {
		return oldIccid;
	}
	public void setOldIccid(String oldIccid) {
		this.oldIccid = oldIccid;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getAlgFlag() {
		return algFlag;
	}
	public void setAlgFlag(String algFlag) {
		this.algFlag = algFlag;
	}
	@Override
	public String toString() {
		return "CmdParamData [otaTradeNo=" + otaTradeNo + ", callControl="
				+ callControl + ", pIccid=" + pIccid + ", cmds=" + cmds
				+ ", oldIccid=" + oldIccid + ", imsi=" + imsi + ", algFlag="
				+ algFlag + ", dataKeyIndex=" + dataKeyIndex + ", keyData="
				+ keyData + ", expTime=" + expTime + ", coverMcc=" + coverMcc
				+ ", ussdPrefix=" + ussdPrefix + ", newIccid=" + newIccid
				+ ", apn=" + apn + ", sca=" + sca + ", telData=" + telData
				+ ", plmn=" + plmn + ", bipParam=" + bipParam + ", fplmn="
				+ fplmn + "]";
	}
	public String getDataKeyIndex() {
		return dataKeyIndex;
	}
	public void setDataKeyIndex(String dataKeyIndex) {
		this.dataKeyIndex = dataKeyIndex;
	}
	public String getKeyData() {
		return keyData;
	}
	public void setKeyData(String keyData) {
		this.keyData = keyData;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	public String getCoverMcc() {
		return coverMcc;
	}
	public void setCoverMcc(String coverMcc) {
		this.coverMcc = coverMcc;
	}
	public String getUssdPrefix() {
		return ussdPrefix;
	}
	public void setUssdPrefix(String ussdPrefix) {
		this.ussdPrefix = ussdPrefix;
	}
	public String getNewIccid() {
		return newIccid;
	}
	public void setNewIccid(String newIccid) {
		this.newIccid = newIccid;
	}
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	public String getSca() {
		return sca;
	}
	public void setSca(String sca) {
		this.sca = sca;
	}
	public String getTelData() {
		return telData;
	}
	public void setTelData(String telData) {
		this.telData = telData;
	}
	public String getPlmn() {
		return plmn;
	}
	public void setPlmn(String plmn) {
		this.plmn = plmn;
	}
	public String getBipParam() {
		return bipParam;
	}
	public void setBipParam(String bipParam) {
		this.bipParam = bipParam;
	}
	public String getFplmn() {
		return fplmn;
	}
	public void setFplmn(String fplmn) {
		this.fplmn = fplmn;
	}
}

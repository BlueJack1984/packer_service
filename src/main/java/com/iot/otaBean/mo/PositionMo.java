package com.iot.otaBean.mo;

import java.util.ArrayList;
import java.util.List;

/**
 * 位置上报ussd对象
 * 
 * @author Administrator
 * 
 */
public class PositionMo extends BaseMo {

	private static final long serialVersionUID = -4755904391015133876L;
	private String checkNum;// 2字节随机数
	private String positionInfo;// mcc+mnc
	private String pIccid;// 主号iccid
	private String imei;// 终端设备imei
	private String imsi; //bip时存在
	private List<String > pImsis = new ArrayList<>();
	private List<String > sImsis = new ArrayList<>();
	private String cellId;// 基站位置，bip时存在
	private String carryingCapacity;// 设备接收能力
	private String latestTradeNo; //最后一次成功的trade No，bip时存在
	private String mcc;
	private String mnc;

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}

	public String getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(String positionInfo) {
		this.positionInfo = positionInfo;
	}

	public String getpIccid() {
		return pIccid;
	}

	public void setpIccid(String pIccid) {
		this.pIccid = pIccid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Override
	public String getImsi() {
		return imsi;
	}

	@Override
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
/*	public String getImsi() {
		return curImsi;
	}

	public void setCurImsi(String curImsi) {
		this.curImsi = curImsi;
	}*/

	public List<String> getpImsis() {
		return pImsis;
	}

	public void setpImsis(List<String> pImsis) {
		this.pImsis = pImsis;
	}

	public List<String> getsImsis() {
		return sImsis;
	}

	public void setsImsis(List<String> sImsis) {
		this.sImsis = sImsis;
	}
/*	public List<String> getImsis() {
		return imsis;
	}

	public void setImsis(List<String> imsis) {
		this.imsis = imsis;
	}*/
/*	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}*/

	public String getCellId() {
		return cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public String getCarryingCapacity() {
		return carryingCapacity;
	}

	public void setCarryingCapacity(String carryingCapacity) {
		this.carryingCapacity = carryingCapacity;
	}

	public String getLatestTradeNo() {
		return latestTradeNo;
	}

	public void setLatestTradeNo(String latestTradeNo) {
		this.latestTradeNo = latestTradeNo;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	@Override
	public String toString() {
		return "PositionMo [checkNum=" + checkNum + ", positionInfo="
				+ positionInfo + ", pIccid=" + pIccid + ", imei=" + imei
				+ ", cellId=" + cellId + ", carryingCapacity="
				+ carryingCapacity + "]";
	}

}

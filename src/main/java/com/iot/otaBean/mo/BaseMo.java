package com.iot.otaBean.mo;

/**
 * 上行ussd对象基类
 * 
 * @author Administrator
 * 
 */
public class BaseMo extends UssdMsgBean {

	private static final long serialVersionUID = -2948338582172264971L;

	private String ussdPre; // ussd上发信息的前缀信息

	private String protocolVersion;// 协议版本号

	private String busiType;// 业务终端类型

	private String keyIndex;// 密钥索引

	private String manuFlag;// 卡片生产商标识

	private String appletVersion;// 卡片程序版本号

	private String cmdType;// 命令类型

	private String cmdParam;// 命令参数

	private String ussdSuffix;// ussd上发信息的后缀

	private UssdMsgBean ussdMsgBean;

	public String getUssdPre() {
		return ussdPre;
	}

	public void setUssdPre(String ussdPre) {
		this.ussdPre = ussdPre;
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

	public String getManuFlag() {
		return manuFlag;
	}

	public void setManuFlag(String manuFlag) {
		this.manuFlag = manuFlag;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getAppletVersion() {
		return appletVersion;
	}

	public void setAppletVersion(String appletVersion) {
		this.appletVersion = appletVersion;
	}

	public String getCmdType() {
		return cmdType;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	public String getCmdParam() {
		return cmdParam;
	}

	public void setCmdParam(String cmdParam) {
		this.cmdParam = cmdParam;
	}

	public String getUssdSuffix() {
		return ussdSuffix;
	}

	public void setUssdSuffix(String ussdSuffix) {
		this.ussdSuffix = ussdSuffix;
	}

	@Override
	public String toString() {
		return "BaseMo [ussdPre=" + ussdPre + ", busiType=" + busiType
				+ ", keyIndex=" + keyIndex + ", manuFlag=" + manuFlag
				+ ", protocolVersion=" + protocolVersion + ", appletVersion="
				+ appletVersion + ", cmdType=" + cmdType + ", cmdParam="
				+ cmdParam + ", ussdSuffix=" + ussdSuffix + "]";
	}

	public UssdMsgBean getUssdMsgBean() {
		return ussdMsgBean;
	}

	public void setUssdMsgBean(UssdMsgBean ussdMsgBean) {
		this.ussdMsgBean = ussdMsgBean;
	}

}

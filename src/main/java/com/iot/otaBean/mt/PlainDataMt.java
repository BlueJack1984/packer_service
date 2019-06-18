package com.iot.otaBean.mt;


public class PlainDataMt {

	private String cmdType;//请求非空
	private String cmdLength;//请求为空，需服务计算
	
	//对应消息协议整体结构的CMD PARAM DATA
//	private String cmdParamData;//请求为空，需服务计算
	private CmdParamData cmdParams;//请求非空
	
	public String getCmdType() {
		return cmdType;
	}
	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}
	public String getCmdLength() {
		return cmdLength;
	}
	public void setCmdLength(String cmdLength) {
		this.cmdLength = cmdLength;
	}
	public CmdParamData getCmdParams() {
		return cmdParams;
	}
	public void setCmdParams(CmdParamData cmdParams) {
		this.cmdParams = cmdParams;
	}
}

package com.iot.otaBean.mo;


public class RecieveDataPorMo extends BaseMo {
    private static final long serialVersionUID = -8765251478668936683L;
    private String batchNum;
    private String retryIndex;
    private String iccid;
    private String imei;

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getRetryIndex() {
        return retryIndex;
    }

    public void setRetryIndex(String retryIndex) {
        this.retryIndex = retryIndex;
    }

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
}

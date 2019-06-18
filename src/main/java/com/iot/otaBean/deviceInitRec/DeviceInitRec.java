package com.iot.otaBean.deviceInitRec;

public class DeviceInitRec {
    private Long id;
    /* 业务编码*/
    private String businessId;
    /*模块imei  */
    private String imei;
    /*设备类型 2-模块 5-soc*/
    private String deviceType;
    /*正式码号类型 1-主号 2-副号*/
    private String softsimType;
    /*自己编写的分散因子*/
    private String numberIccid;
    /*正式码号iccid*/
    private String iccid;
    /*正式码号imsi*/
    private String imsi;
    /* 供应商编码*/
    private String supplierCode;
    /*  供应商名称*/
    private String supplierName;
    /*运营商编码*/
    private String operatorCode;
    /* 运营商名称*/
    private String operatorName;
    /*种子号iccid*/
    private String seedIccid;
    /* 种子号imsi*/
    private String seedImsi;
    /*种子号供应商编码*/
    private String seedSupplierCode;
    /* 种子号供应商名称*/
    private String seedSupplierName;
    /*种子号运营商编码*/
    private String seedOperatorCode;
    /*种子号运营商名称*/
    private String seedOperatorName;
    /*状态*/
    private String status;
    /*重发上报频率*/
    private Long repeatReportRate;
    /*重试次数*/
    private Long retryCount;
    /*定期上报频率*/
    private Long reportRegularlyRate;

    /*bip服务器ip*/
    private String bipIp;
    /*bip服务器端口*/
    private Long bipPort;
    /*业务创建时间*/
    private String createTime;
    /*业务开始执行的时间*/
    private String excuteStartTime;
    /*业务结束执行的时间*/
    private String excuteEndTime;
    /*业务重复执行的次数*/
    private String repeatCount;
    public Long getRepeatReportRate() {
        return repeatReportRate;
    }
    public void setRepeatReportRate(Long repeatReportRate) {
        this.repeatReportRate = repeatReportRate;
    }
    public Long getRetryCount() {
        return retryCount;
    }
    public void setRetryCount(Long retryCount) {
        this.retryCount = retryCount;
    }
    public Long getReportRegularlyRate() {
        return reportRegularlyRate;
    }
    public void setReportRegularlyRate(Long reportRegularlyRate) {
        this.reportRegularlyRate = reportRegularlyRate;
    }
    public String getBipIp() {
        return bipIp;
    }
    public void setBipIp(String bipIp) {
        this.bipIp = bipIp;
    }
    public Long getBipPort() {
        return bipPort;
    }
    public void setBipPort(Long bipPort) {
        this.bipPort = bipPort;
    }

    public String getRepeatCount() {
        return repeatCount;
    }
    public void setRepeatCount(String repeatCount) {
        this.repeatCount = repeatCount;
    }
    public String getExcuteStartTime() {
        return excuteStartTime;
    }
    public void setExcuteStartTime(String excuteStartTime) {
        this.excuteStartTime = excuteStartTime;
    }
    public String getExcuteEndTime() {
        return excuteEndTime;
    }
    public void setExcuteEndTime(String excuteEndTime) {
        this.excuteEndTime = excuteEndTime;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public String getSoftsimType() {
        return softsimType;
    }
    public void setSoftsimType(String softsimType) {
        this.softsimType = softsimType;
    }
    public String getIccid() {
        return iccid;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public String getImsi() {
        return imsi;
    }
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
    public String getSupplierCode() {
        return supplierCode;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getOperatorCode() {
        return operatorCode;
    }
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public String getSeedIccid() {
        return seedIccid;
    }
    public void setSeedIccid(String seedIccid) {
        this.seedIccid = seedIccid;
    }
    public String getSeedImsi() {
        return seedImsi;
    }
    public void setSeedImsi(String seedImsi) {
        this.seedImsi = seedImsi;
    }
    public String getSeedSupplierCode() {
        return seedSupplierCode;
    }
    public void setSeedSupplierCode(String seedSupplierCode) {
        this.seedSupplierCode = seedSupplierCode;
    }
    public String getSeedSupplierName() {
        return seedSupplierName;
    }
    public void setSeedSupplierName(String seedSupplierName) {
        this.seedSupplierName = seedSupplierName;
    }
    public String getSeedOperatorCode() {
        return seedOperatorCode;
    }
    public void setSeedOperatorCode(String seedOperatorCode) {
        this.seedOperatorCode = seedOperatorCode;
    }
    public String getSeedOperatorName() {
        return seedOperatorName;
    }
    public void setSeedOperatorName(String seedOperatorName) {
        this.seedOperatorName = seedOperatorName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getNumberIccid() {
        return numberIccid;
    }

    public void setNumberIccid(String numberIccid) {
        this.numberIccid = numberIccid;
    }
}
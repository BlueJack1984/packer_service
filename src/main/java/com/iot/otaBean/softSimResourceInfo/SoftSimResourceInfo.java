package com.iot.otaBean.softSimResourceInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * 新副号资源信息实体类
 *
 */

public class SoftSimResourceInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8458304601330483073L;

    private Long id;
    private String msisdn;
    private String iccid;
    private String eficcid;
    private String imsi;
    private String efimsi;
    private String imsi2;
    private String efimsi2;
    private String imsi3;
    private String efimsi3;
    private String imsi4;
    private String efimsi4;
    private String imsi5;
    private String efimsi5;
    private String aKey;
    private String uimidEsn;
    private String hrpdupp;
    private String hrpdSs;
    private String sca;
    private String ki;
    private String opc;
    private String keyIndex;
    private String status;
    private String storageBatchNo;
    private String storageTime;
    private String releaseTime;
    private String operatorCode;
    private String operatorName;
    private String supplierCode;
    private String supplierName;
    //	private String appScope;
    private String abandonedTime;
    private String abandonedCause;
    private String apn;
    private String algFlag;
    private String usageDaysLimits;
    private String netFormat;
    private String actualCoverCountry;
    //	private String actualCoverCountry2;
//	private String actualCoverCountry3;
//	private String actualCoverCountry4;
//	private String actualCoverCountry5;
    private String callFlag;//指副号是否有通话功能，1-有通话功能，0-没有通话功能；
    private String managementModel;
    private String flowPoolCode;
    private String flowPoolName;
    private String telecommunicationsFlag;
    private String moreImsiFlag;
    private String type;
    private String suitablePackageType;
    private String busiData;
    private String validDate;
    private String registerStatus;
    private String chargeStatus;
    private String chargeValidDate;
    private String speedLimit;
    private String remark;
    private String serviceStatus;
    private String initFlag;
    private String firstOpen;
    private String coupona;
    private String couponaExpireTime;
    private String couponb;
    private String couponbExpireTime;
    private Date couponUpdateTime;
    //临时变量
    private String startTime;
    private String endTime;
    public Long getId() {
        return id;
    }
    public String getMsisdn() {
        return msisdn;
    }
    public String getIccid() {
        return iccid;
    }
    public String getEficcid() {
        return eficcid;
    }
    public String getImsi() {
        return imsi;
    }
    public String getEfimsi() {
        return efimsi;
    }
    public String getImsi2() {
        return imsi2;
    }
    public String getEfimsi2() {
        return efimsi2;
    }
    public String getImsi3() {
        return imsi3;
    }
    public String getEfimsi3() {
        return efimsi3;
    }
    public String getImsi4() {
        return imsi4;
    }
    public String getEfimsi4() {
        return efimsi4;
    }
    public String getImsi5() {
        return imsi5;
    }
    public String getEfimsi5() {
        return efimsi5;
    }
    public String getKi() {
        return ki;
    }
    public String getOpc() {
        return opc;
    }
    public String getKeyIndex() {
        return keyIndex;
    }
    public String getStatus() {
        return status;
    }
    public String getStorageTime() {
        return storageTime;
    }
    public String getOperatorCode() {
        return operatorCode;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public String getSupplierCode() {
        return supplierCode;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public String getAbandonedTime() {
        return abandonedTime;
    }
    public String getAbandonedCause() {
        return abandonedCause;
    }
    public String getApn() {
        return apn;
    }
    public String getAlgFlag() {
        return algFlag;
    }
    public String getUsageDaysLimits() {
        return usageDaysLimits;
    }
    public String getNetFormat() {
        return netFormat;
    }
    public String getActualCoverCountry() {
        return actualCoverCountry;
    }
    public String getCallFlag() {
        return callFlag;
    }
    public String getManagementModel() {
        return managementModel;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getFlowPoolName() {
        return flowPoolName;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public void setEficcid(String eficcid) {
        this.eficcid = eficcid;
    }
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
    public void setEfimsi(String efimsi) {
        this.efimsi = efimsi;
    }
    public void setImsi2(String imsi2) {
        this.imsi2 = imsi2;
    }
    public void setEfimsi2(String efimsi2) {
        this.efimsi2 = efimsi2;
    }
    public void setImsi3(String imsi3) {
        this.imsi3 = imsi3;
    }
    public void setEfimsi3(String efimsi3) {
        this.efimsi3 = efimsi3;
    }
    public void setImsi4(String imsi4) {
        this.imsi4 = imsi4;
    }
    public void setEfimsi4(String efimsi4) {
        this.efimsi4 = efimsi4;
    }
    public void setImsi5(String imsi5) {
        this.imsi5 = imsi5;
    }
    public void setEfimsi5(String efimsi5) {
        this.efimsi5 = efimsi5;
    }
    public void setKi(String ki) {
        this.ki = ki;
    }
    public void setOpc(String opc) {
        this.opc = opc;
    }
    public void setKeyIndex(String keyIndex) {
        this.keyIndex = keyIndex;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setStorageTime(String storageTime) {
        this.storageTime = storageTime;
    }
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public void setAbandonedTime(String abandonedTime) {
        this.abandonedTime = abandonedTime;
    }
    public void setAbandonedCause(String abandonedCause) {
        this.abandonedCause = abandonedCause;
    }
    public void setApn(String apn) {
        this.apn = apn;
    }
    public void setAlgFlag(String algFlag) {
        this.algFlag = algFlag;
    }
    public void setUsageDaysLimits(String usageDaysLimits) {
        this.usageDaysLimits = usageDaysLimits;
    }
    public void setNetFormat(String netFormat) {
        this.netFormat = netFormat;
    }
    public void setActualCoverCountry(String actualCoverCountry) {
        this.actualCoverCountry = actualCoverCountry;
    }
    public void setCallFlag(String callFlag) {
        this.callFlag = callFlag;
    }
    public void setManagementModel(String managementModel) {
        this.managementModel = managementModel;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setFlowPoolName(String flowPoolName) {
        this.flowPoolName = flowPoolName;
    }
    public String getaKey() {
        return aKey;
    }
    public String getUimidEsn() {
        return uimidEsn;
    }
    public String getHrpdupp() {
        return hrpdupp;
    }
    public String getHrpdSs() {
        return hrpdSs;
    }
    public String getSca() {
        return sca;
    }
    public void setaKey(String aKey) {
        this.aKey = aKey;
    }
    public void setUimidEsn(String uimidEsn) {
        this.uimidEsn = uimidEsn;
    }
    public void setHrpdupp(String hrpdupp) {
        this.hrpdupp = hrpdupp;
    }
    public void setHrpdSs(String hrpdSs) {
        this.hrpdSs = hrpdSs;
    }
    public void setSca(String sca) {
        this.sca = sca;
    }
    public String getStorageBatchNo() {
        return storageBatchNo;
    }
    public void setStorageBatchNo(String storageBatchNo) {
        this.storageBatchNo = storageBatchNo;
    }
    public String getTelecommunicationsFlag() {
        return telecommunicationsFlag;
    }
    public String getMoreImsiFlag() {
        return moreImsiFlag;
    }
    public String getType() {
        return type;
    }
    public String getSuitablePackageType() {
        return suitablePackageType;
    }
    public String getBusiData() {
        return busiData;
    }
    public void setTelecommunicationsFlag(String telecommunicationsFlag) {
        this.telecommunicationsFlag = telecommunicationsFlag;
    }
    public void setMoreImsiFlag(String moreImsiFlag) {
        this.moreImsiFlag = moreImsiFlag;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setSuitablePackageType(String suitablePackageType) {
        this.suitablePackageType = suitablePackageType;
    }
    public void setBusiData(String busiData) {
        this.busiData = busiData;
    }
    public String getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
    public String getFlowPoolCode() {
        return flowPoolCode;
    }
    public void setFlowPoolCode(String flowPoolCode) {
        this.flowPoolCode = flowPoolCode;
    }
    public String getValidDate() {
        return validDate;
    }
    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
    public String getRegisterStatus() {
        return registerStatus;
    }
    public String getChargeStatus() {
        return chargeStatus;
    }
    public String getChargeValidDate() {
        return chargeValidDate;
    }
    public String getSpeedLimit() {
        return speedLimit;
    }
    public String getRemark() {
        return remark;
    }
    public String getServiceStatus() {
        return serviceStatus;
    }
    public String getInitFlag() {
        return initFlag;
    }
    public String getFirstOpen() {
        return firstOpen;
    }
    public String getCoupona() {
        return coupona;
    }
    public String getCouponaExpireTime() {
        return couponaExpireTime;
    }
    public String getCouponb() {
        return couponb;
    }
    public String getCouponbExpireTime() {
        return couponbExpireTime;
    }
    public Date getCouponUpdateTime() {
        return couponUpdateTime;
    }
    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }
    public void setChargeStatus(String chargeStatus) {
        this.chargeStatus = chargeStatus;
    }
    public void setChargeValidDate(String chargeValidDate) {
        this.chargeValidDate = chargeValidDate;
    }
    public void setSpeedLimit(String speedLimit) {
        this.speedLimit = speedLimit;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    public void setInitFlag(String initFlag) {
        this.initFlag = initFlag;
    }
    public void setFirstOpen(String firstOpen) {
        this.firstOpen = firstOpen;
    }
    public void setCoupona(String coupona) {
        this.coupona = coupona;
    }
    public void setCouponaExpireTime(String couponaExpireTime) {
        this.couponaExpireTime = couponaExpireTime;
    }
    public void setCouponb(String couponb) {
        this.couponb = couponb;
    }
    public void setCouponbExpireTime(String couponbExpireTime) {
        this.couponbExpireTime = couponbExpireTime;
    }
    public void setCouponUpdateTime(Date couponUpdateTime) {
        this.couponUpdateTime = couponUpdateTime;
    }

}

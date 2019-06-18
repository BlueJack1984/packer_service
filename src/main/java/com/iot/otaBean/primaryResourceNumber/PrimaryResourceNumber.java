package com.iot.otaBean.primaryResourceNumber;

import java.io.Serializable;

public class PrimaryResourceNumber implements Serializable {

    private static final long serialVersionUID = 5346115567480749978L;
    private Long id;
    //private String phoneNum;
    private String iccid;
    private String eficcid;
    private String msisdn;

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
    private String hrpdupp;
    private String hrpdSs;
    private String uimidEsn;

    private String ki;
    private String opc;
    private String keyIndex;
    private String acc;
    private String storageBatchNo;
    private String operatorCode;
    private String operatorName;
    private String supplierCode;
    private String supplierName;
    private String validDate;
    private String status;
    private String isMplmn;

    private String adm1;
    private String adm2;
    private String adm3;
    private String adm4;

    private String abandonedTime;
    private String abandonedCause;
    private String kic;
    private String kid;
    private String kik;
    private String apn;
    private String createTime;
    private String algFlag;
    private String occupiedTime;
    //private String productTaskNo;
    private String netFormat;
    //private String busiImsi;
    private String callFlag;

    private String reduplicableNumber;
    private String telecommunicationsFlag;//是否为电信码号: 0-否 1-是
    private String moreImsiFlag;//是否为多imsi主号：0- 否；1-是
    private String maxReuseCount;//最大制卡次数
    private String type;//标识主号是种子主号还是普通主号；
    private String allocateTime;//调配时间 普通主号->种子主号
    private String manufacturerCode;
    private String manufacturerName;
    private String distributionTime;
    private String recoverTime;

    private String partnerCode;
    private String partnerName;
    private String sca;
    private String busiData;
    private String coverCountry;

    //临时变量
    private String startTime;
    private String endTime;
    private int enableAllocateNumber;
    private int allocateNumber;
    private String seedManagementCode;
    private String allocateIccid;
    private String imsis;

    public String getCallFlag() {
        return callFlag;
    }

    public void setCallFlag(String callFlag) {
        this.callFlag = callFlag;
    }

    public String getCoverCountry() {
        return coverCountry;
    }
    public void setCoverCountry(String coverCountry) {
        this.coverCountry = coverCountry;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIccid() {
        return iccid;
    }
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
    public String getEficcid() {
        return eficcid;
    }
    public void setEficcid(String eficcid) {
        this.eficcid = eficcid;
    }
    public String getMsisdn() {
        return msisdn;
    }
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    public String getImsi() {
        return imsi;
    }
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }
    public String getEfimsi() {
        return efimsi;
    }
    public void setEfimsi(String efimsi) {
        this.efimsi = efimsi;
    }
    public String getImsi2() {
        return imsi2;
    }
    public void setImsi2(String imsi2) {
        this.imsi2 = imsi2;
    }
    public String getEfimsi2() {
        return efimsi2;
    }
    public void setEfimsi2(String efimsi2) {
        this.efimsi2 = efimsi2;
    }
    public String getImsi3() {
        return imsi3;
    }
    public void setImsi3(String imsi3) {
        this.imsi3 = imsi3;
    }
    public String getEfimsi3() {
        return efimsi3;
    }
    public void setEfimsi3(String efimsi3) {
        this.efimsi3 = efimsi3;
    }
    public String getImsi4() {
        return imsi4;
    }
    public void setImsi4(String imsi4) {
        this.imsi4 = imsi4;
    }
    public String getEfimsi4() {
        return efimsi4;
    }
    public void setEfimsi4(String efimsi4) {
        this.efimsi4 = efimsi4;
    }
    public String getImsi5() {
        return imsi5;
    }
    public void setImsi5(String imsi5) {
        this.imsi5 = imsi5;
    }
    public String getEfimsi5() {
        return efimsi5;
    }
    public void setEfimsi5(String efimsi5) {
        this.efimsi5 = efimsi5;
    }
    public String getaKey() {
        return aKey;
    }
    public void setaKey(String aKey) {
        this.aKey = aKey;
    }
    public String getHrpdupp() {
        return hrpdupp;
    }
    public void setHrpdupp(String hrpdupp) {
        this.hrpdupp = hrpdupp;
    }
    public String getHrpdSs() {
        return hrpdSs;
    }
    public void setHrpdSs(String hrpdSs) {
        this.hrpdSs = hrpdSs;
    }
    public String getUimidEsn() {
        return uimidEsn;
    }
    public void setUimidEsn(String uimidEsn) {
        this.uimidEsn = uimidEsn;
    }
    public String getKi() {
        return ki;
    }
    public void setKi(String ki) {
        this.ki = ki;
    }
    public String getOpc() {
        return opc;
    }
    public void setOpc(String opc) {
        this.opc = opc;
    }
    public String getKeyIndex() {
        return keyIndex;
    }
    public void setKeyIndex(String keyIndex) {
        this.keyIndex = keyIndex;
    }
    public String getAcc() {
        return acc;
    }
    public void setAcc(String acc) {
        this.acc = acc;
    }
    public String getStorageBatchNo() {
        return storageBatchNo;
    }
    public void setStorageBatchNo(String storageBatchNo) {
        this.storageBatchNo = storageBatchNo;
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

    public String getValidDate() {
        return validDate;
    }
    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getIsMplmn() {
        return isMplmn;
    }
    public void setIsMplmn(String isMplmn) {
        this.isMplmn = isMplmn;
    }
    public String getAdm1() {
        return adm1;
    }
    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }
    public String getAdm2() {
        return adm2;
    }
    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }
    public String getAdm3() {
        return adm3;
    }
    public void setAdm3(String adm3) {
        this.adm3 = adm3;
    }
    public String getAdm4() {
        return adm4;
    }
    public void setAdm4(String adm4) {
        this.adm4 = adm4;
    }
    public String getAbandonedTime() {
        return abandonedTime;
    }
    public void setAbandonedTime(String abandonedTime) {
        this.abandonedTime = abandonedTime;
    }
    public String getAbandonedCause() {
        return abandonedCause;
    }
    public void setAbandonedCause(String abandonedCause) {
        this.abandonedCause = abandonedCause;
    }
    public String getKic() {
        return kic;
    }
    public void setKic(String kic) {
        this.kic = kic;
    }
    public String getKid() {
        return kid;
    }
    public void setKid(String kid) {
        this.kid = kid;
    }
    public String getKik() {
        return kik;
    }
    public void setKik(String kik) {
        this.kik = kik;
    }
    public String getApn() {
        return apn;
    }
    public void setApn(String apn) {
        this.apn = apn;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getAlgFlag() {
        return algFlag;
    }
    public void setAlgFlag(String algFlag) {
        this.algFlag = algFlag;
    }
    public String getOccupiedTime() {
        return occupiedTime;
    }
    public void setOccupiedTime(String occupiedTime) {
        this.occupiedTime = occupiedTime;
    }
    public String getNetFormat() {
        return netFormat;
    }
    public void setNetFormat(String netFormat) {
        this.netFormat = netFormat;
    }
    public String getReduplicableNumber() {
        return reduplicableNumber;
    }
    public void setReduplicableNumber(String reduplicableNumber) {
        this.reduplicableNumber = reduplicableNumber;
    }
    public String getTelecommunicationsFlag() {
        return telecommunicationsFlag;
    }
    public void setTelecommunicationsFlag(String telecommunicationsFlag) {
        this.telecommunicationsFlag = telecommunicationsFlag;
    }
    public String getMoreImsiFlag() {
        return moreImsiFlag;
    }
    public void setMoreImsiFlag(String moreImsiFlag) {
        this.moreImsiFlag = moreImsiFlag;
    }
    public String getMaxReuseCount() {
        return maxReuseCount;
    }
    public void setMaxReuseCount(String maxReuseCount) {
        this.maxReuseCount = maxReuseCount;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getAllocateTime() {
        return allocateTime;
    }
    public void setAllocateTime(String allocateTime) {
        this.allocateTime = allocateTime;
    }
    public String getManufacturerCode() {
        return manufacturerCode;
    }
    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }
    public String getManufacturerName() {
        return manufacturerName;
    }
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
    public String getDistributionTime() {
        return distributionTime;
    }
    public void setDistributionTime(String distributionTime) {
        this.distributionTime = distributionTime;
    }
    public String getRecoverTime() {
        return recoverTime;
    }
    public void setRecoverTime(String recoverTime) {
        this.recoverTime = recoverTime;
    }
    public String getPartnerCode() {
        return partnerCode;
    }
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public String getBusiData() {
        return busiData;
    }
    public void setBusiData(String busiData) {
        this.busiData = busiData;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public int getEnableAllocateNumber() {
        return enableAllocateNumber;
    }
    public void setEnableAllocateNumber(int enableAllocateNumber) {
        this.enableAllocateNumber = enableAllocateNumber;
    }
    public int getAllocateNumber() {
        return allocateNumber;
    }
    public void setAllocateNumber(int allocateNumber) {
        this.allocateNumber = allocateNumber;
    }
    public String getSeedManagementCode() {
        return seedManagementCode;
    }
    public void setSeedManagementCode(String seedManagementCode) {
        this.seedManagementCode = seedManagementCode;
    }
    public String getAllocateIccid() {
        return allocateIccid;
    }
    public void setAllocateIccid(String allocateIccid) {
        this.allocateIccid = allocateIccid;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getImsis() {
        return imsis;
    }
    public void setImsis(String imsis) {
        this.imsis = imsis;
    }
    public String getSca() {
        return sca;
    }
    public void setSca(String sca) {
        this.sca = sca;
    }
}
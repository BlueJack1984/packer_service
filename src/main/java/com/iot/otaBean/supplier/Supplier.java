package com.iot.otaBean.supplier;

public class Supplier {

    private static final long serialVersionUID = -2621621653586631231L;
    private Long supplierId;
    private String supplierCode;
    private String supplierName;
    private String country;
    private String softsimServiceFlag;
    private String remark;
    private String createTime;
    private String status;
    //接口对接情况
    private String ussdPre;
    //临时变量
    private String startTime;
    private String endTime;
    private String operator;
    private String selectOperators;
    private String[]operatorList;
    private String[] busiCodes;
    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getSoftsimServiceFlag() {
        return softsimServiceFlag;
    }
    public void setSoftsimServiceFlag(String softsimServiceFlag) {
        this.softsimServiceFlag = softsimServiceFlag;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String[] getOperatorList() {
        return operatorList;
    }
    public void setOperatorList(String[] operatorList) {
        this.operatorList = operatorList;
    }
    public String getSelectOperators() {
        return selectOperators;
    }
    public void setSelectOperators(String selectOperators) {
        this.selectOperators = selectOperators;
    }
    public String[] getBusiCodes() {
        return busiCodes;
    }
    public void setBusiCodes(String[] busiCodes) {
        this.busiCodes = busiCodes;
    }
    public String getUssdPre() {
        return ussdPre;
    }
    public void setUssdPre(String ussdPre) {
        this.ussdPre = ussdPre;
    }
}

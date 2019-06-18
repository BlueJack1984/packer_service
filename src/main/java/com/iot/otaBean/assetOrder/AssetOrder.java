package com.iot.otaBean.assetOrder;

public class AssetOrder {
    /**
     * 记录ID
     */
    private Long id;

    /**
     * 订购ID，格式为：YYYYMMDD+10位序列号）
     */
    private String orderId;

    private String assetId;

    /**
     * 订购的流量套餐编码
     */
    private String packageCode;

    /**
     * 订购的流量套餐编码名称
     */
    private String packageName;

    /**
     * 流量套餐流量，为MB
     */
    private String packageFlow;

    /**
     * 订单对应企业编号
     */
    private String partnerCode;

    /**
     * 订单对应企业名称
     */
    private String partnerName;

    /**
     * 订购周期
     */
    private String orderPeriod;
    /**
     * 状态，取值如下：1：未启用；2：开始使用；3：已结束；4：已取消
     */
    private String status;
    /**
     * 订购时间
     */
    private String orderDate;

    /**
     * 订购方式：1-API接入下单；2-IOT系统下单；
     */
    private String orderType;

    /**
     * IOT系统下单时，下单人账户名；
     */
    private String operAccount;
    /**
     * 订单覆盖国家码
     */
    private String coverCountry;

    /**
     * 订单实际开始时间
     */
    private String actualStartTime;

    /**
     * 订单预期结束时间
     */
    private String plannedEndTime;

    /**
     * 套餐实际结束时间
     */
    private String actualEndTime;
    /**
     * 是否订购加油包的标识，取值如下：0：未订购加油包；1：订购加油包
     */
    private String refuelingBagFlag;

    /**
     * 当前正在使用套餐类型，取值设定如下：1-基础包；2-加油包
     */
    private String inusePackageType;

    /**
     * 订单激活地区国家码
     */
    private String activateLocation;
    /**
     * 领科卖给企业时的批量价格
     */
    private String salePrice;
    /**
     * 取消订单时间
     */
    private String cancelTime;

    /**
     * 取消订单原因
     */
    private String cancelReason;

    private String payRst;// 支付结果

    private String payType;// 支付方式

    private double payAmount;// 支付金额

    private String buyType;//套餐的购买方式；
    /**
     * 流量套餐类型
     */
    private String packageType;

    private String orderPurpose;// 订购流量套餐的目的；1-全球范围使用主号流量；2-慢悠地使用副号流量

    private String aheadFinishReason;//提前结束订单的原因
    /**
     * 本订购周期已用的基础包流量
     */
    private String usedBaseFlow;
    /**
     * 本订购周期已用的加油包流量
     */
    private String usedRefuelingFlow;

    /**
     * 当前订购周期内已用的总流量
     */
    private String usedFlow;

    private String orderTotalFlow;//整个订单过程中使用的总流量,单位为k字节

    private String isFirstOrder;//是否是该物联网卡的首单

    private String hasTestFlow;

    private String testFlow;//测试流量，单位为K字节

    //订单类型
    private String dataOrderType;

    private String pauseTime;

    private String pauseReason;

    private String exceptionFlag;

    private String exceptionCause;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageFlow() {
        return packageFlow;
    }

    public void setPackageFlow(String packageFlow) {
        this.packageFlow = packageFlow;
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

    public String getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(String orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOperAccount() {
        return operAccount;
    }

    public void setOperAccount(String operAccount) {
        this.operAccount = operAccount;
    }

    public String getCoverCountry() {
        return coverCountry;
    }

    public void setCoverCountry(String coverCountry) {
        this.coverCountry = coverCountry;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(String plannedEndTime) {
        this.plannedEndTime = plannedEndTime;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public String getRefuelingBagFlag() {
        return refuelingBagFlag;
    }

    public void setRefuelingBagFlag(String refuelingBagFlag) {
        this.refuelingBagFlag = refuelingBagFlag;
    }

    public String getInusePackageType() {
        return inusePackageType;
    }

    public void setInusePackageType(String inusePackageType) {
        this.inusePackageType = inusePackageType;
    }

    public String getActivateLocation() {
        return activateLocation;
    }

    public void setActivateLocation(String activateLocation) {
        this.activateLocation = activateLocation;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getPayRst() {
        return payRst;
    }

    public void setPayRst(String payRst) {
        this.payRst = payRst;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getOrderPurpose() {
        return orderPurpose;
    }

    public void setOrderPurpose(String orderPurpose) {
        this.orderPurpose = orderPurpose;
    }

    public String getAheadFinishReason() {
        return aheadFinishReason;
    }

    public void setAheadFinishReason(String aheadFinishReason) {
        this.aheadFinishReason = aheadFinishReason;
    }

    public String getUsedBaseFlow() {
        return usedBaseFlow;
    }

    public void setUsedBaseFlow(String usedBaseFlow) {
        this.usedBaseFlow = usedBaseFlow;
    }

    public String getUsedRefuelingFlow() {
        return usedRefuelingFlow;
    }

    public void setUsedRefuelingFlow(String usedRefuelingFlow) {
        this.usedRefuelingFlow = usedRefuelingFlow;
    }

    public String getUsedFlow() {
        return usedFlow;
    }

    public void setUsedFlow(String usedFlow) {
        this.usedFlow = usedFlow;
    }

    public String getOrderTotalFlow() {
        return orderTotalFlow;
    }

    public void setOrderTotalFlow(String orderTotalFlow) {
        this.orderTotalFlow = orderTotalFlow;
    }

    public String getIsFirstOrder() {
        return isFirstOrder;
    }

    public void setIsFirstOrder(String isFirstOrder) {
        this.isFirstOrder = isFirstOrder;
    }

    public String getHasTestFlow() {
        return hasTestFlow;
    }

    public void setHasTestFlow(String hasTestFlow) {
        this.hasTestFlow = hasTestFlow;
    }

    public String getTestFlow() {
        return testFlow;
    }

    public void setTestFlow(String testFlow) {
        this.testFlow = testFlow;
    }

    public String getDataOrderType() {
        return dataOrderType;
    }

    public void setDataOrderType(String dataOrderType) {
        this.dataOrderType = dataOrderType;
    }

    public String getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(String pauseTime) {
        this.pauseTime = pauseTime;
    }

    public String getPauseReason() {
        return pauseReason;
    }

    public void setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason;
    }

    public String getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(String exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    public String getExceptionCause() {
        return exceptionCause;
    }

    public void setExceptionCause(String exceptionCause) {
        this.exceptionCause = exceptionCause;
    }

}

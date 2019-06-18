package com.iot.otaBean.assetManageBusi;

public class AssetManageBusi {
    /**
     * 业务ID，格式为：YYYYMMDD+8位序列号。例如：2017062200000001
     */
    private String busiCode;

    /**
     * 设备唯一标识
     */
    private String assetId;

    /**
     * 管理业务类型，取值如下：01-更新主号；02-更新主号PLMN03-锁定主号
     */
    private String tradeType;

    /**
     * 业务状态，取值如下：1-未执行；2-已完成；3-进行中
     */
    private String status;

    /**
     * 管理参数，具体定义如下：业务类型为01时，此字段为新主号ICCID；业务类型为02时，此字段为空；业务类型为03时，此字段为空
     */
    private String manageParam;

    /**
     * 业务生成时间
     */
    private String createTime;

    /**
     * 业务开始执行时间
     */
    private String startExcuteTime;

    /**
     * 业务结束执行时间
     */
    private String finishExcuteTime;

    /**
     * 重发处理的次数，默认初始值为0
     */
    private String dealCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 通过OTA方式，对设备进行管理时，下行短信中携带的交易流水号
     */
    private String otaTradeCode;

    /**
     * 管理模式:1废弃旧主号;2回收旧主号
     */
    private String manageMode;

    /**
     * 获取业务ID，格式为：YYYYMMDD+8位序列号。例如：2017062200000001
     *
     * @return busi_code - 业务ID，格式为：YYYYMMDD+8位序列号。例如：2017062200000001
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * 设置业务ID，格式为：YYYYMMDD+8位序列号。例如：2017062200000001
     *
     * @param busiCode 业务ID，格式为：YYYYMMDD+8位序列号。例如：2017062200000001
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode == null ? null : busiCode.trim();
    }

    /**
     * 获取设备唯一标识
     *
     * @return asset_id - 设备唯一标识
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置设备唯一标识
     *
     * @param assetId 设备唯一标识
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 获取管理业务类型，取值如下：01-更新主号；02-更新主号PLMN03-锁定主号
     *
     * @return trade_type - 管理业务类型，取值如下：01-更新主号；02-更新主号PLMN03-锁定主号
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置管理业务类型，取值如下：01-更新主号；02-更新主号PLMN03-锁定主号
     *
     * @param tradeType 管理业务类型，取值如下：01-更新主号；02-更新主号PLMN03-锁定主号
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 获取业务状态，取值如下：1-未执行；2-已完成；3-进行中
     *
     * @return status - 业务状态，取值如下：1-未执行；2-已完成；3-进行中
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置业务状态，取值如下：1-未执行；2-已完成；3-进行中
     *
     * @param status 业务状态，取值如下：1-未执行；2-已完成；3-进行中
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取管理参数，具体定义如下：业务类型为01时，此字段为新主号ICCID；业务类型为02时，此字段为空；业务类型为03时，此字段为空
     *
     * @return manage_param - 管理参数，具体定义如下：业务类型为01时，此字段为新主号ICCID；业务类型为02时，此字段为空；业务类型为03时，此字段为空
     */
    public String getManageParam() {
        return manageParam;
    }

    /**
     * 设置管理参数，具体定义如下：业务类型为01时，此字段为新主号ICCID；业务类型为02时，此字段为空；业务类型为03时，此字段为空
     *
     * @param manageParam 管理参数，具体定义如下：业务类型为01时，此字段为新主号ICCID；业务类型为02时，此字段为空；业务类型为03时，此字段为空
     */
    public void setManageParam(String manageParam) {
        this.manageParam = manageParam == null ? null : manageParam.trim();
    }

    /**
     * 获取业务生成时间
     *
     * @return create_time - 业务生成时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置业务生成时间
     *
     * @param createTime 业务生成时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 获取业务开始执行时间
     *
     * @return start_excute_time - 业务开始执行时间
     */
    public String getStartExcuteTime() {
        return startExcuteTime;
    }

    /**
     * 设置业务开始执行时间
     *
     * @param startExcuteTime 业务开始执行时间
     */
    public void setStartExcuteTime(String startExcuteTime) {
        this.startExcuteTime = startExcuteTime == null ? null : startExcuteTime.trim();
    }

    /**
     * 获取业务结束执行时间
     *
     * @return finish_excute_time - 业务结束执行时间
     */
    public String getFinishExcuteTime() {
        return finishExcuteTime;
    }

    /**
     * 设置业务结束执行时间
     *
     * @param finishExcuteTime 业务结束执行时间
     */
    public void setFinishExcuteTime(String finishExcuteTime) {
        this.finishExcuteTime = finishExcuteTime == null ? null : finishExcuteTime.trim();
    }

    /**
     * 获取重发处理的次数，默认初始值为0
     *
     * @return deal_count - 重发处理的次数，默认初始值为0
     */
    public String getDealCount() {
        return dealCount;
    }

    /**
     * 设置重发处理的次数，默认初始值为0
     *
     * @param dealCount 重发处理的次数，默认初始值为0
     */
    public void setDealCount(String dealCount) {
        this.dealCount = dealCount == null ? null : dealCount.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取通过OTA方式，对设备进行管理时，下行短信中携带的交易流水号
     *
     * @return ota_trade_code - 通过OTA方式，对设备进行管理时，下行短信中携带的交易流水号
     */
    public String getOtaTradeCode() {
        return otaTradeCode;
    }

    /**
     * 设置通过OTA方式，对设备进行管理时，下行短信中携带的交易流水号
     *
     * @param otaTradeCode 通过OTA方式，对设备进行管理时，下行短信中携带的交易流水号
     */
    public void setOtaTradeCode(String otaTradeCode) {
        this.otaTradeCode = otaTradeCode == null ? null : otaTradeCode.trim();
    }

    /**
     * 获取管理模式:1废弃旧主号;2回收旧主号
     *
     * @return manage_mode - 管理模式:1废弃旧主号;2回收旧主号
     */
    public String getManageMode() {
        return manageMode;
    }

    /**
     * 设置管理模式:1废弃旧主号;2回收旧主号
     *
     * @param manageMode 管理模式:1废弃旧主号;2回收旧主号
     */
    public void setManageMode(String manageMode) {
        this.manageMode = manageMode == null ? null : manageMode.trim();
    }
}

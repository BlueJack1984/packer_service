package com.iot.otaBean.manufacturerEnmu;

public class ManufacturerEnmu {

    /**
     * 生产厂商产品外观形态
     *
     * @author lipei
     *
     */
    public enum ManufacturerType {
        CARD("1", "卡"),
        DEVICE("2", "设备");

        private String typeCode;
        private String typeDesc;

        private ManufacturerType(String typeCode, String typeDesc) {
            this.typeCode = typeCode;
            this.typeDesc = typeDesc;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }
    };

    /**
     * 生产厂商状态
     *
     * @author lipei
     *
     */
    public enum ManufacturerStatus {
        NOMAL("1", "正常"), ABANDONED("2", "已停用");

        private String statusCode;
        private String statusDesc;

        private ManufacturerStatus(String statusCode, String statusDesc) {
            this.statusCode = statusCode;
            this.statusDesc = statusDesc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }
    }

    /**
     * 厂商生产数据类型
     *
     * @author lipei
     *
     */
    public enum ManufacturerDataType {
        MODULE_NAME("1", "模块名称"), CHIP_MODEL("2", "芯片型号"), EQUIPMENT_PDN("3",
                "生产设备PDN");

        private String typeCode;
        private String typeDesc;

        private ManufacturerDataType(String typeCode, String typeDesc) {
            this.typeCode = typeCode;
            this.typeDesc = typeDesc;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }
    }

    /**
     * 厂商生产数据状态
     *
     * @author lipei
     *
     */
    public enum ManufacturerDataStatus {
        NOMAL("1", "正常"), ABANDONED("2", "已停用"), USING("3", "使用中");

        private String statusCode;
        private String statusDesc;

        private ManufacturerDataStatus(String statusCode, String statusDesc) {
            this.statusCode = statusCode;
            this.statusDesc = statusDesc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

    }

    /**
     * 厂商模块状态
     *
     * @author lipei
     *
     */
    public enum ManufacturerModuelStatus {
        NOMAL("1", "正常"), ABANDONED("2", "已停用");

        private String statusCode;
        private String statusDesc;

        private ManufacturerModuelStatus(String statusCode, String statusDesc) {
            this.statusCode = statusCode;
            this.statusDesc = statusDesc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

    }

    /**
     * 厂商密钥类型
     *
     * @author lipei
     *
     */
    public enum ManufacturerKeyType {
        PERSONAL_DAY_KEY("1", "个人化私密数据加密主密钥"), LINE_PROTECTED_KEY("2", "线路保护密钥"), OTA_COMM_KEY(
                "3", "OTA通信密钥"), PRODUCTION_DEVICE_AUTHENTICATION("4",
                "生产设备认证密钥");

        private String typeCode;
        private String typeDesc;

        private ManufacturerKeyType(String typeCode, String typeDesc) {
            this.typeCode = typeCode;
            this.typeDesc = typeDesc;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }

    }

    /**
     * 厂商生产方式
     *
     */
    public enum ManufacturerProductType {
        FACTORY_BATCH_PRODUCTION_ADULT("1", "工厂生产批量成卡"),
        FACTORY_BATCH_PRODUCTION_WHITE("2", "工厂生产批量白卡"),
        //		ONEOFF_SMALL_BATCH_PRODUCTION("3","人工一次性生产小批成卡"),
        ONEOFF_SMALL_BATCH_TWICE("4","人工小批二次发卡"),
        PROMOTION("5","升级"),
        //		OTA_PRESET_PACKAGE("6","OTA卡预置套餐"),
        ONLINE("7", "联机"),
        OFFLINE("8", "离线"),
        SEED("9","种子号");

        private String typeCode;
        private String typeDesc;


        private ManufacturerProductType(String typeCode, String typeDesc) {
            this.typeCode = typeCode;
            this.typeDesc = typeDesc;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }

    }

}


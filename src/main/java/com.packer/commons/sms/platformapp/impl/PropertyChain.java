package com.packer.commons.sms.platformapp.impl;


public class PropertyChain {
    private String fieldName;
    private String[] properties;

    public PropertyChain() {
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String[] getProperties() {
        return this.properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}

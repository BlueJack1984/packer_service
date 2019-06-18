package com.packer.commons.sms.packet;

public class FieldData {
    private String fieldName;
    private String inputData;
    private String convertedData;
    private String typedData;

    public FieldData(String fieldName, String inputData, String convertedData, String typedData) {
        this.fieldName = fieldName;
        this.inputData = inputData;
        this.convertedData = convertedData;
        this.typedData = typedData;
    }

    public String getInputData() {
        return this.inputData;
    }

    public String getConvertedData() {
        return this.convertedData;
    }

    public String getTypedData() {
        return this.typedData;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}

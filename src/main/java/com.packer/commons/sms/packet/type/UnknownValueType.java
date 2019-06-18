package com.packer.commons.sms.packet.type;


import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.packet.IValueType;
import com.packer.commons.sms.packet.ValueTypeException;

public class UnknownValueType extends AbstractSelfObject implements IValueType {
    private static final long serialVersionUID = -1298194588398821636L;
    private Property backFieldsLengthProperty = new Property("后面字段长度(单位字节)", "", "0");

    public UnknownValueType() {
        super("Unknown类型", "如果不知道字段数据长度，可以设置为此类型。但是设置为此类型的字段必须是报文的最后一个字段");
    }

    public Property getBackFieldsLengthProperty() {
        return this.backFieldsLengthProperty;
    }

    public String construct(String moFieldContent) throws ValueTypeException {
        return moFieldContent;
    }

    public String[] parse(StringBuffer sms) throws ValueTypeException {
        int backFieldsLength = 0;

        try {
            backFieldsLength = Integer.parseInt(this.backFieldsLengthProperty.getValue());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        if (backFieldsLength < 0) {
            backFieldsLength = 0;
        }

        backFieldsLength *= 2;
        if (backFieldsLength > sms.length()) {
            backFieldsLength = sms.length();
        }

        int unknowLength = sms.length() - backFieldsLength;

        try {
            String unknowSms = sms.substring(0, unknowLength);
            String[] contents = new String[]{unknowSms, unknowSms};
            sms.delete(0, unknowLength);
            return contents;
        } catch (Exception var6) {
            throw new ValueTypeException("Type parse error!! sms value:[" + sms.toString() + "]", var6);
        }
    }

    public void setBackFieldsLengthProperty(Property backFieldsLengthProperty) {
        this.backFieldsLengthProperty = backFieldsLengthProperty;
    }

    public static void main(String[] args) {
        UnknownValueType type = new UnknownValueType();
        type.getBackFieldsLengthProperty().setValue("aaa");
        StringBuffer sms = new StringBuffer("0A0B12345678");
        String[] contents = type.parse(sms);
        System.out.println("0 = " + contents[0]);
        System.out.println("1 = " + contents[1]);
        System.out.println("2 = " + sms);
    }
}

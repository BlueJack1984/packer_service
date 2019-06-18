package com.packer.commons.sms.packet.type;


import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.packet.IValueType;
import com.packer.commons.sms.packet.ValueTypeException;

public class LVValueType implements IValueType {
    private static final long serialVersionUID = -742397368552465774L;
    private Property len = new Property("L的长度(字节)", "", "1");

    public LVValueType() {
    }

    public String construct(String moFieldContent) throws ValueTypeException {
        try {
            int l_len = Integer.parseInt(this.len.getValue());
            String l = LFStringUtil.paddingHeadZero(Integer.toString(moFieldContent.length() / 2, 16), l_len * 2);
            return l + moFieldContent;
        } catch (Exception var4) {
            throw new ValueTypeException("Type construct error!! original value:[" + moFieldContent + "]", var4);
        }
    }

    public String[] parse(StringBuffer sms) throws ValueTypeException {
        try {
            int l_len = Integer.parseInt(this.len.getValue());
            String l = sms.substring(0, l_len * 2);
            int len = Integer.parseInt(l, 16);
            String v = sms.substring(l_len * 2, l_len * 2 + len * 2);
            sms.delete(0, l_len * 2 + len * 2);
            return new String[]{l + v, v};
        } catch (Exception var6) {
            throw new ValueTypeException("Type parse error!! sms value:[" + sms.toString() + "]");
        }
    }

    public String getDescription() {
        return "";
    }

    public String getName() {
        return "LV类型";
    }

    public Property getLen() {
        return this.len;
    }

    public void setLen(Property len) {
        this.len = len;
    }

    public String toString() {
        return this.getName() + "[L=" + this.len.getValue() + "]";
    }
}

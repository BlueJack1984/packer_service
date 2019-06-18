package com.packer.commons.sms.packet.type;

import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.packet.ValueTypeException;

public class AppenderFixedValueType implements IValueType {
    private static final long serialVersionUID = 1492873990305387158L;
    private Property len = new Property("字段内容长度(字节)", "", "1");
    private Property padding = new Property("填充字符", "", "0");
    private Property padtype = new Property("填充方式(head|tail)", "", "head", new String[]{"head", "tail"});

    public AppenderFixedValueType() {
    }

    public static void main(String[] args) {
        AppenderFixedValueType type = new AppenderFixedValueType();
        type.getPadtype().setValue("head");
        type.getLen().setValue("3");
        type.getPadding().setValue("FF");
        System.out.println(type.construct("FFFFFF"));
        System.out.println("1" + type.parse(new StringBuffer("FFFFFF"))[1]);
    }

    public String construct(String moFieldContent) throws ValueTypeException {
        try {
            int length = Integer.parseInt(this.len.getValue());
            String paddings = this.padding.getValue();
            String type = this.padtype.getValue();
            if (moFieldContent.length() > length * 2) {
                throw new ValueTypeException("The Length of data[" + moFieldContent + "] > limit[" + length + " bytes]");
            } else if (paddings != null && paddings.length() != 0) {
                return type.equals("head") ? LFStringUtil.paddingHead(moFieldContent, length * 2, paddings) : LFStringUtil.paddingTail(moFieldContent, length * 2, paddings);
            } else if (moFieldContent.length() != length * 2) {
                throw new ValueTypeException("The Length of data[" + moFieldContent + "] < limit[" + length + " bytes]");
            } else {
                return moFieldContent;
            }
        } catch (ValueTypeException var5) {
            throw var5;
        } catch (Exception var6) {
            throw new ValueTypeException("Type construct error!! original value:[" + moFieldContent + "]", var6);
        }
    }

    public String[] parse(StringBuffer sms) throws ValueTypeException {
        try {
            int length = Integer.parseInt(this.len.getValue());
            String paddingc = this.padding.getValue();
            String type = this.padtype.getValue();
            String content = sms.substring(0, length * 2);
            sms.delete(0, length * 2);
            if (paddingc != null && paddingc.length() != 0) {
                String actual = type.equals("head") ? LFStringUtil.trimHead(content, paddingc, 0) : LFStringUtil.trimTail(content, paddingc, 0);
                return new String[]{content, actual};
            } else {
                return new String[]{content, content};
            }
        } catch (Exception var7) {
            throw new ValueTypeException("Type parse error!! sms value:[" + sms.toString() + "]", var7);
        }
    }

    public String getDescription() {
        return "对于内容长度固定的字段，可以设置为此类型。此类型还提供了字符填充机制，可以在字段的头部/尾部填充字符";
    }

    public String getName() {
        return "固定长度类型";
    }

    public Property getLen() {
        return this.len;
    }

    public void setLen(Property len) {
        this.len = len;
    }

    public Property getPadding() {
        return this.padding;
    }

    public void setPadding(Property padding) {
        this.padding = padding;
    }

    public Property getPadtype() {
        return this.padtype;
    }

    public void setPadtype(Property padtype) {
        this.padtype = padtype;
    }

    public String toString() {
        return this.getName() + "[length=" + this.len.getValue() + ",padding=" + this.padding.getValue() + ",padtype=" + this.padtype.getValue() + "]";
    }
}

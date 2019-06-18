package com.packer.commons.sms.packet.countlimit;


import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.packet.ICountLimit;

public class DefaultFixedCountLimit implements ICountLimit {
    private static final long serialVersionUID = 1L;
    private Property countlimit = new Property("count(0..N | x)", "", "1");

    public DefaultFixedCountLimit() {
    }

    public int getCount() {
        String v = this.countlimit.getValue();
        return v.equals("x") ? 2147483647 : Integer.parseInt(v);
    }

    public boolean isFixed() {
        return this.getCount() != 2147483647;
    }

    public String getDescription() {
        return "此限制有一个\"count\"属性。可以设置为正整数和x。如果设置为x,则表示字段在报文中出现的次数未知";
    }

    public String getName() {
        return "次数限制";
    }

    public Property getCountlimit() {
        return this.countlimit;
    }

    public void setCountlimit(Property countlimit) {
        this.countlimit = countlimit;
    }

    public String toString() {
        return this.getName() + "[count=" + this.countlimit.getValue() + "]";
    }
}

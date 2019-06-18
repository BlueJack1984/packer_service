package com.packer.commons.sms.packet.valuesource;


import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.ValueSourceException;
import java.util.Arrays;
import java.util.List;

public class DefaultFixedValueSource implements IValueSource {
    private static final long serialVersionUID = 1L;
    private Property value = new Property("固定值", "", "");

    public DefaultFixedValueSource() {
    }

    public List<String> getValues() throws ValueSourceException {
        String v = this.value.getValue();
        return Arrays.asList(v);
    }

    public String getDescription() {
        return "通过属性可以设置一个固定值";
    }

    public String getName() {
        return "固定值值源";
    }

    public Property getValue() {
        return this.value;
    }

    public void setValue(Property value) {
        this.value = value;
    }

    public String toString() {
        return this.getName() + "[" + this.value.getValue() + "]";
    }
}

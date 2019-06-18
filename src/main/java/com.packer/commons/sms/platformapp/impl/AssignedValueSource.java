package com.packer.commons.sms.platformapp.impl;


import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.ValueSourceException;
import java.util.ArrayList;
import java.util.List;

class AssignedValueSource extends AbstractSelfObject implements IValueSource {
    private static final long serialVersionUID = -7598871162251663636L;
    private static final List<String> NULL_VALUES = new ArrayList(0);
    private List<String> values = new ArrayList();

    public AssignedValueSource() {
        super("赋值值源", "通过外部设置值源内容");
    }

    public AssignedValueSource(List<String> values) {
        super("赋值值源", "通过外部设置值源内容");
        this.values = values;
    }

    public AssignedValueSource(String value) {
        super("赋值值源", "通过外部设置值源内容");
        this.values = new ArrayList();
        this.values.add(value);
    }

    public List<String> getValues() throws ValueSourceException {
        return this.values == null ? NULL_VALUES : this.values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void addValues(List<String> values) {
        if (values != null) {
            this.values.addAll(values);
        }

    }
}

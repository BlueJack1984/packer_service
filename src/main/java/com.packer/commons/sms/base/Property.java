
package com.packer.commons.sms.base;

public class Property extends AbstractSelfObject implements ISelfDescriptive {
    private static final long serialVersionUID = 948567228161088625L;
    private String value;
    private String[] fixedValues;

    public Property() {
    }

    public Property(String name, String description, String value) {
        super(name, description);
        this.value = value;
    }

    public Property(String name, String description, String value, String[] fixedValues) {
        this(name, description, value);
        this.fixedValues = fixedValues;
    }

    public void assignValue(String strValue) throws PropertyFormatException {
        if (this.fixedValues != null && this.fixedValues.length > 0) {
            boolean contains = false;
            String msg = "";

            for(int i = 0; i < this.fixedValues.length; ++i) {
                String fixedValue = this.fixedValues[i];
                msg = msg + "\n    " + fixedValue;
                if (fixedValue.equals(strValue)) {
                    contains = true;
                }
            }

            if (!contains) {
                throw new PropertyFormatException("属性[" + this.getName() + "]的有效值集为:" + msg);
            }
        }

        this.value = strValue;
    }

    public String expressValue() {
        return this.value;
    }

    public String[] expressFixedValues() {
        return this.fixedValues;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getFixedValues() {
        return this.fixedValues;
    }

    public void setFixedValues(String[] fixedValues) {
        this.fixedValues = fixedValues;
    }
}

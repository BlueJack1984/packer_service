package com.packer.commons.sms.packet;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.ISelfDescriptive;
import java.util.List;

public interface IField extends ISelfDescriptive {
    List<FieldData> parse(StringBuffer var1) throws SmsException;

    List<FieldData> pack() throws SmsException;

    String getName();

    void setName(String var1);

    String getParentFieldName();

    void setParentFieldName(String var1);

    boolean hasParentField();

    String getClassProperty();

    void setClassProperty(String var1);

    IValueValidator getValueValidator();

    void setValueValidator(IValueValidator var1);

    ICountLimit getCountLimit();

    void setCountLimit(ICountLimit var1);

    String print(int var1);
}

package com.packer.commons.sms.packet;

import com.packer.commons.sms.base.IExtendPointer;

public interface IValueType extends IExtendPointer {
    String construct(String var1) throws ValueTypeException;

    String[] parse(StringBuffer var1) throws ValueTypeException;
}

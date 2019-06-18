package com.packer.commons.sms.packet;


import com.packer.commons.sms.base.IExtendPointer;

public interface IValueConverter extends IExtendPointer {
    String convert(String var1) throws ConverterException;

    String reconvert(String var1) throws ConverterException;
}

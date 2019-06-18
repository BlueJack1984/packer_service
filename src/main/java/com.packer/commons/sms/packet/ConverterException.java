package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class ConverterException extends SmsException {
    private static final long serialVersionUID = 1L;

    public ConverterException() {
    }

    public ConverterException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public ConverterException(String msg) {
        super(msg);
    }

    public ConverterException(Throwable ex) {
        super(ex);
    }
}


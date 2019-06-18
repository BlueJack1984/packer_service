package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class ValueTypeException extends SmsException {
    private static final long serialVersionUID = 1L;

    public ValueTypeException() {
    }

    public ValueTypeException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public ValueTypeException(String msg) {
        super(msg);
    }

    public ValueTypeException(Throwable ex) {
        super(ex);
    }
}

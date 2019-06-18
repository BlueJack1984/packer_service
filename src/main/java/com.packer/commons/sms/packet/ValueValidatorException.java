package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class ValueValidatorException extends SmsException {
    private static final long serialVersionUID = 1L;

    public ValueValidatorException() {
    }

    public ValueValidatorException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public ValueValidatorException(String msg) {
        super(msg);
    }

    public ValueValidatorException(Throwable ex) {
        super(ex);
    }
}

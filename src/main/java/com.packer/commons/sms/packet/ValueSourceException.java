package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class ValueSourceException extends SmsException {
    private static final long serialVersionUID = 1L;

    public ValueSourceException() {
    }

    public ValueSourceException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public ValueSourceException(String msg) {
        super(msg);
    }

    public ValueSourceException(Throwable ex) {
        super(ex);
    }
}

package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class SmsFormatInvalidException extends SmsException {
    private static final long serialVersionUID = 641338475523930535L;

    public SmsFormatInvalidException() {
    }

    public SmsFormatInvalidException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public SmsFormatInvalidException(String msg) {
        super(msg);
    }

    public SmsFormatInvalidException(Throwable ex) {
        super(ex);
    }
}

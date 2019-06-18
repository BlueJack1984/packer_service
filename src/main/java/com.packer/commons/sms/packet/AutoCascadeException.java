package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class AutoCascadeException extends SmsException {
    private static final long serialVersionUID = -4307093084086856980L;

    public AutoCascadeException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public AutoCascadeException(String msg) {
        super(msg);
    }

    public AutoCascadeException(Throwable ex) {
        super(ex);
    }
}

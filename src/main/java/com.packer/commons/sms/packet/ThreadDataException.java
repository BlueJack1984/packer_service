package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class ThreadDataException extends SmsException {
    private static final long serialVersionUID = 3652388623219430228L;

    public ThreadDataException() {
    }

    public ThreadDataException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public ThreadDataException(String msg) {
        super(msg);
    }

    public ThreadDataException(Throwable ex) {
        super(ex);
    }
}

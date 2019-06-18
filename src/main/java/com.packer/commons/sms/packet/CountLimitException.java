package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;

public class CountLimitException extends SmsException {
    private static final long serialVersionUID = 7027309971490980831L;

    public CountLimitException() {
    }

    public CountLimitException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public CountLimitException(String msg) {
        super(msg);
    }

    public CountLimitException(Throwable ex) {
        super(ex);
    }
}

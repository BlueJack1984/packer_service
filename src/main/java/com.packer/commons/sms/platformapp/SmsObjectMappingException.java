package com.packer.commons.sms.platformapp;

import com.packer.commons.sms.SmsException;

public class SmsObjectMappingException extends SmsException {
    private static final long serialVersionUID = -3724769887734144168L;

    public SmsObjectMappingException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public SmsObjectMappingException(String msg) {
        super(msg);
    }

    public SmsObjectMappingException(Throwable ex) {
        super(ex);
    }
}

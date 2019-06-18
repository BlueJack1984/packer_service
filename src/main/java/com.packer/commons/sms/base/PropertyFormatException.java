package com.packer.commons.sms.base;

import com.packer.commons.sms.SmsException;

public class PropertyFormatException extends SmsException {
    private static final long serialVersionUID = 5057944660085828761L;

    public PropertyFormatException(String msg) {
        super(msg);
    }

    public PropertyFormatException(Exception ex) {
        super(ex);
    }

    public PropertyFormatException(String msg, Exception ex) {
        super(msg, ex);
    }
}
package com.packer.commons.sms.platformapp;

import com.packer.commons.sms.packet.ParseResult;

public class PlatformParseResult extends ParseResult {
    private Object businessObj;

    public PlatformParseResult() {
    }

    public Object getBusinessObj() {
        return this.businessObj;
    }

    public void setBusinessObj(Object businessObj) {
        this.businessObj = businessObj;
    }
}

package com.packer.commons.sms.packet;

import com.packer.commons.sms.SmsException;
import java.util.Map;

public interface IPacketParser {
    ParseResult parse(String var1, Map<String, Object> var2) throws SmsException;
}


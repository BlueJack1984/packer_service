package com.packer.commons.sms.platformapp;

import com.packer.commons.sms.packet.IPacketParser;
import java.util.Map;

public interface IPlatformPacketParser extends IPacketParser {
    PlatformParseResult parse(String var1, Map<String, Object> var2);
}

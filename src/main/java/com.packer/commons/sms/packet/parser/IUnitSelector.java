package com.packer.commons.sms.packet.parser;

import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.IExtendPointer;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.StructureUnit;

public interface IUnitSelector extends IExtendPointer {
    StructureUnit select(PacketChannel var1, String var2) throws SmsException;
}

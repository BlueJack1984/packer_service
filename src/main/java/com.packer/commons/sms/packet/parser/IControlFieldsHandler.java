package com.packer.commons.sms.packet.parser;

import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.IExtendPointer;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.StructureUnit;

public interface IControlFieldsHandler extends IExtendPointer {
    HandleResult handle(PacketChannel var1, StructureUnit var2, PacketData var3, String var4) throws SmsException;
}

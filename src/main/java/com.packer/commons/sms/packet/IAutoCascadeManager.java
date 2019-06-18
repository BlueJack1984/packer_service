package com.packer.commons.sms.packet;

import com.packer.commons.sms.base.IExtendPointer;
import java.util.List;

public interface IAutoCascadeManager extends IExtendPointer {
    List<StringBuffer> split(Packet var1, PacketData var2) throws AutoCascadeException;

    StringBuffer assemble(Packet var1, PacketGroupData var2, String[] var3) throws AutoCascadeException;
}

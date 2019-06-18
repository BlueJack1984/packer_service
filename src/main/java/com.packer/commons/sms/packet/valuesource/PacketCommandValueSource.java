package com.packer.commons.sms.packet.valuesource;

import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.packet.DataManager;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.PacketGroupData;
import com.packer.commons.sms.packet.ValueSourceException;
import java.util.ArrayList;
import java.util.List;

public class PacketCommandValueSource extends AbstractSelfObject implements IValueSource {
    private static final long serialVersionUID = -2519741626916375773L;

    public PacketCommandValueSource() {
        super("报文命令值源", "通过此值源，可以获得报文的命令");
    }

    public List<String> getValues() throws ValueSourceException {
        List<String> result = new ArrayList(1);
        PacketGroupData groupData = DataManager.getPacketGroupData();
        result.add(groupData.getPacketGroupCommand());
        return result;
    }
}
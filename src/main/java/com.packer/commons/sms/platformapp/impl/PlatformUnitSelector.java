package com.packer.commons.sms.platformapp.impl;

import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketConfigException;
import com.packer.commons.sms.packet.StructureUnit;
import com.packer.commons.sms.packet.parser.IUnitSelector;
import java.util.List;

public class PlatformUnitSelector extends AbstractSelfObject implements IUnitSelector {
    private static final long serialVersionUID = -4072535656608372421L;

    public PlatformUnitSelector() {
    }

    public StructureUnit select(PacketChannel packetChannel, String sms) throws SmsException {
        List<StructureUnit> moUnits = packetChannel.getMoUnits();
        if (moUnits != null && moUnits.size() != 0) {
            if (moUnits.size() > 1) {
                throw new PacketConfigException("The packetChannel[name=" + packetChannel.getName() + "] is config many MO units!");
            } else {
                return (StructureUnit)moUnits.get(0);
            }
        } else {
            throw new PacketConfigException("The packetChannel[name=" + packetChannel.getName() + "] is not config MO unit!");
        }
    }
}
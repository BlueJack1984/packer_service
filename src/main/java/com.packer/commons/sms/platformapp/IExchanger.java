package com.packer.commons.sms.platformapp;

import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.PacketGroupData;
import java.util.List;
import java.util.Map;

public interface IExchanger {
    Object toBusinessObject(PacketGroup var1, PacketGroupData var2) throws SmsObjectMappingException;

    Map<String, IValueSource> toFieldValues(PacketGroup var1, Object var2) throws SmsObjectMappingException;

    List<IObjectPropertyExchanger<Object>> getObjectPropertyExchangers();

    void setObjectPropertyExchangers(List<IObjectPropertyExchanger<Object>> var1);
}
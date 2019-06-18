package com.packer.commons.sms.packet.valuesource;
import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.packet.DataManager;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.PacketGroupData;
import com.packer.commons.sms.packet.ValueSourceException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDatasValueSource extends AbstractSelfObject implements IValueSource {
    private static final long serialVersionUID = -8548265802693491175L;

    public BusinessDatasValueSource() {
        super("业务数据值源", "此值源可以取得当前处理报文的业务数据");
    }

    public List<String> getValues() throws ValueSourceException {
        List<String> result = new ArrayList(1);
        PacketGroupData groupData = DataManager.getPacketGroupData();
        PacketData packetData = groupData.getCurrentPacketData();
        result.add(packetData.getBusinessFieldsSms());
        return result;
    }
}

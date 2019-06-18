package com.packer.commons.sms.packet.parser;

import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.StructureUnit;
import java.util.ArrayList;
import java.util.List;

public class SimpleControlFieldsHandler extends AbstractControlFieldsHandler implements IControlFieldsHandler {
    private static final long serialVersionUID = -3568661563333705963L;

    public SimpleControlFieldsHandler() {
        super("字段结构处理器(报文组命定唯一，不支持及联)", "");
    }

    protected void cascadeHandle(PacketChannel channel, StructureUnit unit, PacketData packetData, String sms, HandleResult result) {
        result.setTotal(1);
        result.setCurrentIndex(1);
        List<String> receivedSmses = new ArrayList();
        receivedSmses.add(sms);
        result.setReceivedSmses(receivedSmses);
    }
}

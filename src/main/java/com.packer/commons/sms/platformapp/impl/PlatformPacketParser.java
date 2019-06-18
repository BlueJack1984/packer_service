package com.packer.commons.sms.platformapp.impl;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.PacketGroupData;
import com.packer.commons.sms.packet.ParseResult;
import com.packer.commons.sms.packet.parser.IControlFieldsHandler;
import com.packer.commons.sms.packet.parser.IUnitSelector;
import com.packer.commons.sms.packet.parser.PacketParser;
import com.packer.commons.sms.packet.parser.SimpleControlFieldsHandler;
import com.packer.commons.sms.platformapp.IExchanger;
import com.packer.commons.sms.platformapp.IPlatformPacketParser;
import com.packer.commons.sms.platformapp.PlatformParseResult;
import java.util.Map;

public class PlatformPacketParser extends PacketParser implements IPlatformPacketParser {
    private IExchanger exchanger;

    public PlatformPacketParser(PacketChannel channel) {
        this(channel, new PlatformUnitSelector(), new SimpleControlFieldsHandler(), new FieldStructureExchanger());
    }

    public PlatformPacketParser(PacketChannel channel, IControlFieldsHandler controlFieldsHandler) {
        this(channel, new PlatformUnitSelector(), controlFieldsHandler, new FieldStructureExchanger());
    }

    public PlatformPacketParser(PacketChannel channel, IUnitSelector unitSelector, IControlFieldsHandler controlFieldsHandler) {
        this(channel, unitSelector, controlFieldsHandler, new FieldStructureExchanger());
    }

    public PlatformPacketParser(PacketChannel channel, IUnitSelector unitSelector, IControlFieldsHandler controlFieldsHandler, IExchanger exchanger) {
        super(channel, unitSelector, controlFieldsHandler);
        this.exchanger = exchanger;
    }

    public PlatformParseResult parse(String sms, Map<String, Object> parameterDatas) throws SmsException {
        ParseResult parseResult = super.parse(sms, parameterDatas);
        PlatformParseResult platformParseResult = new PlatformParseResult();
        platformParseResult.setParseOver(parseResult.isParseOver());
        platformParseResult.setGroupData(parseResult.getGroupData());
        platformParseResult.setReceivedSmses(parseResult.getReceivedSmses());
        platformParseResult.setPacketGroup(parseResult.getPacketGroup());
        if (platformParseResult.isParseOver()) {
            platformParseResult.setBusinessObj(this.constructBusinessObj(parseResult, parameterDatas));
        }

        return platformParseResult;
    }

    private Object constructBusinessObj(ParseResult parseResult, Map<String, Object> parameterDatas) {
        PacketGroupData groupData = parseResult.getGroupData();
        PacketGroup packetGroup = parseResult.getPacketGroup();

        try {
            return this.exchanger.toBusinessObject(packetGroup, groupData);
        } catch (SmsException var7) {
            this.fillSmsException(var7, groupData, parameterDatas);
            throw var7;
        } catch (Exception var8) {
            SmsException smsEx = new SmsException(var8);
            this.fillSmsException(smsEx, groupData, parameterDatas);
            throw smsEx;
        }
    }
}

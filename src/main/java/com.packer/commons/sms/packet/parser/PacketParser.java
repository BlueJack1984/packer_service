package com.packer.commons.sms.packet.parser;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.packet.DataManager;
import com.packer.commons.sms.packet.FieldData;
import com.packer.commons.sms.packet.FieldGroup;
import com.packer.commons.sms.packet.IAutoCascadeManager;
import com.packer.commons.sms.packet.IField;
import com.packer.commons.sms.packet.IPacketParser;
import com.packer.commons.sms.packet.IValueValidator;
import com.packer.commons.sms.packet.Packet;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketConfigException;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.PacketGroupData;
import com.packer.commons.sms.packet.ParseResult;
import com.packer.commons.sms.packet.StructureUnit;
import com.packer.commons.sms.packet.ValueValidatorException;
import com.packer.commons.sms.util.ThreadLocalUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PacketParser implements IPacketParser {
    private PacketChannel channel;
    private IUnitSelector unitSelector;
    private IControlFieldsHandler controlFieldsHandler;

    public PacketParser(PacketChannel channel, IUnitSelector unitSelector, IControlFieldsHandler controlFieldsHandler) {
        this.channel = channel;
        this.unitSelector = unitSelector;
        this.controlFieldsHandler = controlFieldsHandler;
    }

    protected void fillSmsException(SmsException ex, PacketGroupData groupData, Map<String, Object> parameterDatas) {
        if (this.channel != null) {
            ex.setPacketChannelName(this.channel.getName());
        }

        ex.addProperties(DataManager.getGlobalDatas());
        ex.addProperties(parameterDatas);
        List<String> allSms = new ArrayList();
        if (groupData == null) {
            ex.setReceivedSmses(allSms);
        } else {
            for(int i = 0; i < groupData.getPacketDatas().size(); ++i) {
                PacketData packetData = (PacketData)groupData.getPacketDatas().get(i);
                allSms.add(packetData.getControlFieldsSms());
            }

            ex.setReceivedSmses(allSms);
            ex.setPacketGroupName(groupData.getPacketGroupName());
        }
    }

    public ParseResult parse(String sms, Map<String, Object> parameterDatas) throws SmsException {
        PacketGroupData groupData = new PacketGroupData();
        groupData.setParameterDatas(parameterDatas);
        ThreadLocalUtil.save(groupData);

        ParseResult var7;
        try {
            var7 = this.parse(sms, groupData);
        } catch (SmsException var11) {
            this.fillSmsException(var11, groupData, parameterDatas);
            throw var11;
        } catch (Throwable var12) {
            SmsException smsException = new SmsException(var12);
            this.fillSmsException(smsException, groupData, parameterDatas);
            throw smsException;
        } finally {
            ThreadLocalUtil.remove(PacketGroupData.class);
        }

        return var7;
    }

    protected ParseResult parse(String sms, PacketGroupData groupData) {
        StructureUnit currentUnit = this.unitSelector.select(this.channel, sms);
        if (currentUnit == null) {
            throw new PacketConfigException("Can't select unit through the sms[" + sms + "] in the packetChannel[" + this.channel.getName() + "]!");
        } else {
            StringBuffer smsBuffer = new StringBuffer(sms);
            PacketData packetData = new PacketData();
            groupData.addPacketData(packetData);
            this.parseControlFields(packetData, currentUnit.getControlFields(), smsBuffer);
            HandleResult result = this.controlFieldsHandler.handle(this.channel, currentUnit, packetData, sms);
            ParseResult parseResult = new ParseResult();
            PacketGroup packetGroup = result.getPacketGroup();
            groupData.setPacketGroupName(packetGroup.getName());
            groupData.setPacketGroupCommand(packetGroup.getCommand());
            parseResult.setReceivedSmses(result.getReceivedSmses());
            parseResult.setGroupData(groupData);
            parseResult.setPacketGroup(result.getPacketGroup());
            if (packetGroup.isSimple()) {
                if (result.getTotal() != 1) {
                    throw new PacketConfigException("There have received " + result.getTotal() + " smses, but the PacketGroup[command=" + packetGroup.getCommand() + "] is a simple type packetGroup!");
                }

                this.dealSimple(result, packetData, currentUnit);
                parseResult.setParseOver(true);
            } else if (result.isReceivedOver()) {
                this.dealCascade(result, groupData, currentUnit);
                parseResult.setParseOver(true);
            } else {
                parseResult.setParseOver(false);
            }

            return parseResult;
        }
    }

    protected void dealSimple(HandleResult handleResult, PacketData packetData, StructureUnit currentUnit) {
        PacketGroup packetGroup = handleResult.getPacketGroup();
        packetData.setCommand(packetGroup.getCommand());
        packetData.setPacketName(packetGroup.getName());
        Packet packet = (Packet)packetGroup.getPackets().get(0);
        StringBuffer smsBuffer = this.getBusinessSms(packetData, currentUnit.getBussfield());
        packetData.setBusinessFieldsSms(smsBuffer.toString());
        this.parseBusinessFields(packetData, packet, smsBuffer, true);
        this.validateFields(packetData, currentUnit.getControlFields());
        this.validateFields(packetData, packet.getBusinessFields());
    }

    protected void dealCascade(HandleResult handleResult, PacketGroupData groupData, StructureUnit currentUnit) {
        PacketGroup packetGroup = handleResult.getPacketGroup();
        groupData.clear();
        if (packetGroup.isCascadeManual()) {
            this.dealCascadeManual(handleResult, groupData, currentUnit);
        } else {
            this.dealCascadeAuto(handleResult, groupData, currentUnit);
        }

    }

    protected void dealCascadeManual(HandleResult handleResult, PacketGroupData groupData, StructureUnit currentUnit) {
        List<String> smses = handleResult.getReceivedSmses();
        PacketGroup packetGroup = handleResult.getPacketGroup();
        if (smses.size() != packetGroup.getPacketCount()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("The manual packetGroup[command=").append(groupData.getPacketGroupCommand()).append("] is configed ").append(packetGroup.getPacketCount()).append(" packets, but received ").append(smses.size()).append(" smses in fact!");
            throw new PacketConfigException(buffer.toString());
        } else {
            for(int i = 0; i < smses.size(); ++i) {
                Packet packet = (Packet)packetGroup.getPackets().get(i);
                StringBuffer smsBuffer = new StringBuffer((String)smses.get(i));
                PacketData packetData = new PacketData(packet.getName(), packet.getCommand());
                groupData.addPacketData(packetData);
                this.parseControlFields(packetData, currentUnit.getControlFields(), smsBuffer);
                StringBuffer businessSms = this.getBusinessSms(packetData, currentUnit.getBussfield());
                packetData.setBusinessFieldsSms(businessSms.toString());
                this.parseBusinessFields(packetData, packet, businessSms, true);
            }

            List<Packet> packets = packetGroup.getPackets();

            for(int i = 0; i < packets.size(); ++i) {
                Packet packet = (Packet)packets.get(i);
                groupData.setCurrentIndex(i + 1);
                this.validateFields(groupData.getCurrentPacketData(), currentUnit.getControlFields());
                this.validateFields(groupData.getCurrentPacketData(), packet.getBusinessFields());
            }

        }
    }

    protected void dealCascadeAuto(HandleResult handleResult, PacketGroupData groupData, StructureUnit currentUnit) {
        Packet packet = (Packet)handleResult.getPacketGroup().getPackets().get(0);
        List<String> smses = handleResult.getReceivedSmses();
        String[] businessSmses = new String[smses.size()];

        StringBuffer allBusinessSms;
        PacketData firstPacketData;
        for(int i = 0; i < smses.size(); ++i) {
            allBusinessSms = new StringBuffer((String)smses.get(i));
            firstPacketData = new PacketData(packet.getName(), packet.getCommand());
            groupData.addPacketData(firstPacketData);
            this.parseControlFields(firstPacketData, currentUnit.getControlFields(), allBusinessSms);
            StringBuffer businessSms = this.getBusinessSms(firstPacketData, currentUnit.getBussfield());
            firstPacketData.setBusinessFieldsSms(businessSms.toString());
            businessSmses[i] = businessSms.toString();
        }

        IAutoCascadeManager autoCascadeManager = handleResult.getPacketGroup().getAutoCascadeManager();
        allBusinessSms = autoCascadeManager.assemble(packet, groupData, businessSmses);
        groupData.setCurrentIndex(1);
        firstPacketData = groupData.getCurrentPacketData();
        this.parseBusinessFields(firstPacketData, packet, allBusinessSms, false);
        this.validateFields(groupData.getCurrentPacketData(), packet.getBusinessFields());

        for(int i = 0; i < groupData.getPacketDatas().size(); ++i) {
            groupData.setCurrentIndex(i + 1);
            this.validateFields(groupData.getCurrentPacketData(), currentUnit.getControlFields());
        }

    }

    private void parseControlFields(PacketData packetData, List<IField> controlFields, StringBuffer smsBuffer) {
        packetData.setControlFieldsSms(smsBuffer.toString());
        this.parseFields(packetData, controlFields, smsBuffer);
    }

    private void parseBusinessFields(PacketData packetData, Packet packet, StringBuffer sms, boolean isSetBusinessData) {
        if (isSetBusinessData) {
            packetData.setBusinessFieldsSms(sms.toString());
        }

        this.parseFields(packetData, packet.getBusinessFields(), sms);
        if (sms.length() > 0) {
            throw new PacketConfigException("The packetGroup[" + packetData.getPacketName() + "] is invalid：The sms is remain [" + sms.toString() + "] after parsed");
        }
    }

    protected void parseFields(PacketData packetData, List<IField> fields, StringBuffer sms) {
        for(int i = 0; i < fields.size(); ++i) {
            IField field = (IField)fields.get(i);
            field.parse(sms);
        }

    }

    protected void validateFields(PacketData packetData, List<IField> fields) {
        for(int i = 0; i < fields.size(); ++i) {
            IField field = (IField)fields.get(i);
            if (field instanceof FieldGroup) {
                FieldGroup fieldGroup = (FieldGroup)field;
                this.validateFields(packetData, fieldGroup.getFields());
            }

            IValueValidator validator = field.getValueValidator();
            if (validator != null) {
                String fieldName = field.getName();
                List<FieldData> fieldDatas = packetData.getFieldDatas(fieldName);
                if (fieldDatas != null) {
                    for(int j = 0; j < fieldDatas.size(); ++j) {
                        FieldData fieldData = (FieldData)fieldDatas.get(j);

                        try {
                            validator.validate(fieldData.getInputData());
                        } catch (ValueValidatorException var11) {
                            var11.setFieldName(field.getName());
                            throw var11;
                        }
                    }
                }
            }
        }

    }

    private StringBuffer getBusinessSms(PacketData packetData, String userDataFieldName) {
        FieldData fieldData = packetData.getFieldData(userDataFieldName);
        return new StringBuffer(fieldData.getInputData());
    }

    public PacketChannel getPacketChannel() {
        return this.channel;
    }
}

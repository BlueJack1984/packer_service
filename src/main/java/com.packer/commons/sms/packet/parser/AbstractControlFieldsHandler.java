package com.packer.commons.sms.packet.parser;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.AbstractSelfObject;
import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.packet.FieldData;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketConfigException;
import com.packer.commons.sms.packet.PacketData;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.StructureUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractControlFieldsHandler extends AbstractSelfObject implements IControlFieldsHandler {
    private static final long serialVersionUID = -4562864958376468823L;
    private Map<String, PacketGroup> packetGroupCache = new HashMap();
    private Property isIgnoreCommand = new Property("忽略命令大小写", "", "false", new String[]{"true", "false"});

    public AbstractControlFieldsHandler(String name, String description) {
        super(name, description);
    }

    public HandleResult handle(PacketChannel channel, StructureUnit unit, PacketData packetData, String sms) throws SmsException {
        HandleResult result = new HandleResult();
        this.packetGroupHandle(channel, unit, packetData, sms, result);
        this.cascadeHandle(channel, unit, packetData, sms, result);
        return result;
    }

    protected abstract void cascadeHandle(PacketChannel var1, StructureUnit var2, PacketData var3, String var4, HandleResult var5) throws SmsException;

    protected void packetGroupHandle(PacketChannel channel, StructureUnit unit, PacketData packetData, String sms, HandleResult result) throws SmsException {
        String commandFieldName = unit.getCmdField();
        FieldData commandFieldData = packetData.getFieldData(commandFieldName);
        if (commandFieldData == null) {
            throw new PacketConfigException("Can't find the data of command field [" + commandFieldName + "] in the packetData!");
        } else {
            String command = commandFieldData.getInputData();
            String key = channel.getName() + command;
            PacketGroup packetGroup = null;
            if (this.packetGroupCache.containsKey(key)) {
                packetGroup = (PacketGroup)this.packetGroupCache.get(key);
            } else {
                packetGroup = this.findPacketGroup(channel, unit, command);
                this.packetGroupCache.put(key, packetGroup);
            }

            result.setPacketGroup(packetGroup);
        }
    }

    protected PacketGroup findPacketGroup(PacketChannel channel, StructureUnit unit, String command) {
        List<PacketGroup> result = new ArrayList();
        List<PacketGroup> packetGroups = unit.getPacketGroups();

        for(int i = 0; i < packetGroups.size(); ++i) {
            PacketGroup group = (PacketGroup)packetGroups.get(i);
            if (this.commandEquals(group, command)) {
                result.add(group);
            }
        }

        if (result.size() == 0) {
            throw new PacketConfigException("Can't find the packetGroup through the command[" + command + "] ! packetChannel[" + channel.getName() + "], unit[" + unit.getName() + "]");
        } else if (result.size() > 1) {
            throw new PacketConfigException("Find " + result.size() + " packetGroups through the command[" + command + "] ! packetChannel[" + channel.getName() + "], unit[" + unit.getName() + "]");
        } else {
            return (PacketGroup)result.get(0);
        }
    }

    protected boolean commandEquals(PacketGroup packetGroup, String exceptCommand) {
        return this.isIgnoreCommand.getValue().equals("true") ? packetGroup.getCommand().equalsIgnoreCase(exceptCommand) : packetGroup.getCommand().equals(exceptCommand);
    }

    public Property getIsIgnoreCommand() {
        return this.isIgnoreCommand;
    }

    public void setIsIgnoreCommand(Property isIgnoreCommand) {
        this.isIgnoreCommand = isIgnoreCommand;
    }
}

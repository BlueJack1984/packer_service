package com.packer.commons.sms.mapping.impl;
import com.packer.commons.sms.mapping.XMLMapping;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.serialize.XMLSerializer;
import java.util.List;

public class XMLFileMapping implements XMLMapping {
    private XMLFileParser parser;
    private XMLSerializer serializer;
    private LoadCondition condition;

    public XMLFileMapping(String path) {
        this.parser = new XMLFileParser(path);
        this.serializer = new XMLSerializer(path);
    }

    public List<PacketChannel> loadChannels() {
        this.parser.setCondition(this.getCondition());
        return this.parser.loadChannels();
    }

    public void serializeChannels(List<PacketChannel> pc) {
        this.serializer.write(pc, this.parser.getFiles());
    }

    public LoadCondition getCondition() {
        return this.condition;
    }

    public void setCondition(LoadCondition condition) {
        this.condition = condition;
    }
}

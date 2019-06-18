package com.packer.commons.sms.mapping.impl;

import com.packer.commons.sms.mapping.XMLMapping;
import com.packer.commons.sms.packet.PacketChannel;
import java.util.List;
import org.apache.log4j.Logger;

public class XMLClassPathMapping implements XMLMapping {
    Logger logger = Logger.getLogger(XMLClassPathMapping.class);
    private XMLClassPathParser parser;
    private LoadCondition condition;

    public XMLClassPathMapping(String path) {
        this.parser = new XMLClassPathParser(path);
    }

    public List<PacketChannel> loadChannels() {
        this.parser.setCondition(this.getCondition());
        return this.parser.loadChannels();
    }

    public void serializeChannels(List<PacketChannel> pc) {
        this.logger.warn("ClassPath Mapping doesn't support serialize");
    }

    public LoadCondition getCondition() {
        return this.condition;
    }

    public void setCondition(LoadCondition condition) {
        this.condition = condition;
    }
}

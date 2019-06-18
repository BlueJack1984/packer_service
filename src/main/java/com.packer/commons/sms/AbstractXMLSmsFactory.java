package com.packer.commons.sms;

import com.packer.commons.sms.mapping.XMLMapping;
import com.packer.commons.sms.packet.DefaultChannelManager;
import com.packer.commons.sms.packet.IChannelManager;

public abstract class AbstractXMLSmsFactory extends AbstractSmsFactory {
    public String config;

    public AbstractXMLSmsFactory() {
    }

    protected IChannelManager initManager() {
        DefaultChannelManager cm = new DefaultChannelManager();
        cm.setImpl(this.getMapping(this.getConfig()));
        return cm;
    }

    protected abstract XMLMapping getMapping(String var1);

    public String getConfig() {
        return this.config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}

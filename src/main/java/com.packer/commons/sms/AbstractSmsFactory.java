package com.packer.commons.sms;

import com.packer.commons.sms.mapping.impl.LoadCondition;
import com.packer.commons.sms.packet.IChannelManager;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.parser.IControlFieldsHandler;
import com.packer.commons.sms.packet.parser.IUnitSelector;
import com.packer.commons.sms.packet.parser.SimpleControlFieldsHandler;
import com.packer.commons.sms.platformapp.IExchanger;
import com.packer.commons.sms.platformapp.IObjectPropertyExchanger;
import com.packer.commons.sms.platformapp.IPacketGroupSelector;
import com.packer.commons.sms.platformapp.IPlatformPacketConstructor;
import com.packer.commons.sms.platformapp.IPlatformPacketParser;
import com.packer.commons.sms.platformapp.impl.DefaultPacketGroupSelector;
import com.packer.commons.sms.platformapp.impl.DefaultPlatFormPacketConstructor;
import com.packer.commons.sms.platformapp.impl.FieldStructureExchanger;
import com.packer.commons.sms.platformapp.impl.PlatformPacketParser;
import com.packer.commons.sms.platformapp.impl.PlatformUnitSelector;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSmsFactory {
    private IChannelManager manager;
    private IPacketGroupSelector selector = new DefaultPacketGroupSelector();
    private IControlFieldsHandler handler = new SimpleControlFieldsHandler();
    private IUnitSelector unitSelector = new PlatformUnitSelector();
    private LoadCondition condition = new LoadCondition();
    private IExchanger exchanger = new FieldStructureExchanger();
    private List<IObjectPropertyExchanger<Object>> objectPropertyExchangers = new ArrayList();

    protected abstract IChannelManager initManager();

    public AbstractSmsFactory() {
    }

    public AbstractSmsFactory(IPacketGroupSelector selector, IControlFieldsHandler handler, IUnitSelector unitSelector, IExchanger exchanger) {
        this.selector = selector;
        this.handler = handler;
        this.unitSelector = unitSelector;
        this.exchanger = exchanger;
        this.condition = new LoadCondition();
    }

    public IPlatformPacketConstructor getDefaultConstructor(String channelName) {
        PacketChannel pc = this.getManager().loadPacketChannel(channelName);
        IPlatformPacketConstructor cons = new DefaultPlatFormPacketConstructor(pc, this.selector);
        return cons;
    }

    public IPlatformPacketParser getDefaultParser(String channelName) {
        PacketChannel pc = this.getManager().loadPacketChannel(channelName);
        this.exchanger.setObjectPropertyExchangers(this.objectPropertyExchangers);
        PlatformPacketParser parser = new PlatformPacketParser(pc, this.unitSelector, this.handler, this.exchanger);
        return parser;
    }

    public IPacketGroupSelector getSelector() {
        return this.selector;
    }

    public void setSelector(IPacketGroupSelector selector) {
        this.selector = selector;
    }

    public IControlFieldsHandler getHandler() {
        return this.handler;
    }

    public void setHandler(IControlFieldsHandler handler) {
        this.handler = handler;
    }

    public IUnitSelector getUnitSelector() {
        return this.unitSelector;
    }

    public void setUnitSelector(IUnitSelector unitSelector) {
        this.unitSelector = unitSelector;
    }

    public IExchanger getExchanger() {
        return this.exchanger;
    }

    public void setExchanger(IExchanger exchanger) {
        this.exchanger = exchanger;
    }

    public IChannelManager getManager() {
        if (this.manager == null) {
            this.manager = this.initManager();
        }

        return this.manager;
    }

    public LoadCondition getCondition() {
        return this.condition;
    }

    public void setCondition(LoadCondition condition) {
        this.condition = condition;
    }

    public List<IObjectPropertyExchanger<Object>> getObjectPropertyExchangers() {
        return this.objectPropertyExchangers;
    }

    public void setObjectPropertyExchangers(List<IObjectPropertyExchanger<Object>> objectPropertyExchangers) {
        this.objectPropertyExchangers = objectPropertyExchangers;
    }
}

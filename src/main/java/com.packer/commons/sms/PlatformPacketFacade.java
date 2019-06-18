package com.packer.commons.sms;

import com.packer.commons.sms.mapping.impl.LoadCondition;
import com.packer.commons.sms.packet.ConstructResult;
import com.packer.commons.sms.packet.parser.IControlFieldsHandler;
import com.packer.commons.sms.packet.parser.IUnitSelector;
import com.packer.commons.sms.platformapp.IExchanger;
import com.packer.commons.sms.platformapp.IObjectPropertyExchanger;
import com.packer.commons.sms.platformapp.IPacketGroupSelector;
import com.packer.commons.sms.platformapp.IPlatformPacketConstructor;
import com.packer.commons.sms.platformapp.IPlatformPacketParser;
import com.packer.commons.sms.platformapp.PlatformParseResult;
import com.packer.commons.sms.util.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlatformPacketFacade {
    private static final String FILE_TYPE = "file:";
    private static final String CLASSPATH_TYPE = "classpath:";
    private IPlatformPacketConstructor platformPacketConstructor;
    private IPlatformPacketParser platformPacketParser;
    private String path;
    private String channelName;
    private Map<String, List<String>> unloadCondition;
    private IPacketGroupSelector packetGroupSelector;
    private IControlFieldsHandler controlFieldsHandler;
    private IUnitSelector unitSelector;
    private IExchanger exchanger;
    private List<IObjectPropertyExchanger<Object>> objectPropertyExchangers;

    public PlatformPacketFacade() {
    }

    private AbstractXMLSmsFactory creatFilePathFactory(String config) {
        FileXMLSmsFactory factory = new FileXMLSmsFactory();
        factory.setConfig(config);
        return factory;
    }

    private AbstractXMLSmsFactory creatClassPathFactory(String config) {
        ClassPathXMLSmsFactory factory = new ClassPathXMLSmsFactory();
        factory.setConfig(config);
        return factory;
    }

    public void init() {
        if (!Assert.isEmpty(this.path) && !Assert.isEmpty(this.channelName)) {
            try {
                AbstractXMLSmsFactory factory = null;
                if (this.path.startsWith("file:")) {
                    factory = this.creatFilePathFactory(this.path.substring("file:".length()));
                } else if (this.path.startsWith("classpath:")) {
                    factory = this.creatClassPathFactory(this.path.substring("classpath:".length()));
                } else {
                    factory = this.creatClassPathFactory(this.path);
                }

                if (this.packetGroupSelector != null) {
                    factory.setSelector(this.packetGroupSelector);
                }

                if (this.unitSelector != null) {
                    factory.setUnitSelector(this.unitSelector);
                }

                if (this.controlFieldsHandler != null) {
                    factory.setHandler(this.controlFieldsHandler);
                }

                if (this.exchanger != null) {
                    factory.setExchanger(this.exchanger);
                } else if (this.objectPropertyExchangers != null) {
                    factory.setObjectPropertyExchangers(this.objectPropertyExchangers);
                }

                LoadCondition condition = new LoadCondition();
                if (this.unloadCondition != null) {
                    condition.setUnload(this.unloadCondition);
                }

                factory.setCondition(condition);
                this.platformPacketConstructor = factory.getDefaultConstructor(this.channelName);
                this.platformPacketParser = factory.getDefaultParser(this.channelName);
            } catch (Exception var3) {
                throw new SmsException("Init SmsFactory Error!", var3);
            }
        } else {
            throw new SmsException("Init SmsFactory Error: The config or channelName is empty!");
        }
    }

    public ConstructResult construct(Object businessObject, Map<String, Object> parameters) throws SmsException {
        return this.platformPacketConstructor.construct(businessObject, parameters);
    }

    public ConstructResult construct(Object businessObject) throws SmsException {
        Map<String, Object> parameters = new HashMap();
        return this.construct(businessObject, (Map)parameters);
    }

    public ConstructResult construct(Object businessObject, String phone) throws SmsException {
        Map<String, Object> parameters = new HashMap();
        parameters.put("phoneKey", phone);
        return this.construct(businessObject, (Map)parameters);
    }

    public ConstructResult construct(Object businessObject, String phone, Object obj) throws SmsException {
        Map<String, Object> parameters = new HashMap();
        parameters.put("phoneKey", phone);
        parameters.put(phone, obj);
        return this.construct(businessObject, (Map)parameters);
    }

    public PlatformParseResult parse(String sms, Map<String, Object> datas) throws SmsException {
        return this.platformPacketParser.parse(sms, datas);
    }

    public PlatformParseResult parse(String sms) throws SmsException {
        Map<String, Object> parameters = new HashMap();
        return this.parse(sms, (Map)parameters);
    }

    public PlatformParseResult parse(String sms, String phone) throws SmsException {
        Map<String, Object> parameters = new HashMap();
        parameters.put("phoneKey", phone);
        return this.parse(sms, (Map)parameters);
    }

    public PlatformParseResult parse(String sms, String phone, Object obj) throws SmsException {
        Map<String, Object> parameters = new HashMap();
        parameters.put("phoneKey", phone);
        parameters.put(phone, obj);
        return this.parse(sms, (Map)parameters);
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Map<String, List<String>> getUnloadCondition() {
        return this.unloadCondition;
    }

    public void setUnloadCondition(Map<String, List<String>> unloadCondition) {
        this.unloadCondition = unloadCondition;
    }

    public IPacketGroupSelector getPacketGroupSelector() {
        return this.packetGroupSelector;
    }

    public void setPacketGroupSelector(IPacketGroupSelector packetGroupSelector) {
        this.packetGroupSelector = packetGroupSelector;
    }

    public IControlFieldsHandler getControlFieldsHandler() {
        return this.controlFieldsHandler;
    }

    public void setControlFieldsHandler(IControlFieldsHandler controlFieldsHandler) {
        this.controlFieldsHandler = controlFieldsHandler;
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

    public List<IObjectPropertyExchanger<Object>> getObjectPropertyExchangers() {
        return this.objectPropertyExchangers;
    }

    public void setObjectPropertyExchangers(List<IObjectPropertyExchanger<Object>> objectPropertyExchangers) {
        this.objectPropertyExchangers = objectPropertyExchangers;
    }
}

package com.packer.commons.sms.mapping;


import java.util.HashMap;
import java.util.Map;
import nu.xom.Element;
import nu.xom.Nodes;

public class Configuration {
    public static final String CONSTANTS = "/g:cfg/g:constant";
    public static final String CONSTANT_TYPE_IMPL_FIX = "type.fix.impl";
    public static final String CONSTANT_VALUE_IMPL_FIX = "value.fix.impl";
    public static final String CONSTANT_VALIDATOR_IMPL_FIX = "validator.fix.impl";
    public static final String CONSTANT_COUNTLIMIT_IMPL_FIX = "countlimit.fix.impl";
    public static final String CONSTANT_DEFAULT_COUNTLIMIT = "global.countlimit.value";
    public static final String CONSTANT_PADDING = "global.padding.value";
    public static final String CONSTANT_PADDING_POSITION = "global.padding.position";
    public static final String DEFAULT_BUSSFILED_TYPE = "TYPE_BUSS";
    public static final String DEFAULT_BUSSFIELD_VALUE = "VALUE_BUSS";
    public static final String DEFAULT_CMD_VALUE = "VALUE_CMD";
    private static XMLConfig config = new XMLConfig(XMLConfig.class.getResource("/wdpacker-cfg.xml"));
    private static Map<String, String> constant = new HashMap();

    static {
        Nodes nodes = config.getMutilNodes("/g:cfg/g:constant");

        for(int i = 0; i < nodes.size(); ++i) {
            Element e = (Element)nodes.get(i);
            addCons(e.getAttributeValue("key"), e.getAttributeValue("value"));
        }

    }

    public Configuration() {
    }

    public static String getCons(String key) {
        return (String)constant.get(key);
    }

    public static void addCons(String key, String value) {
        constant.put(key, value);
    }
}

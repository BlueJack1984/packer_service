package com.packer.commons.sms;

import com.packer.commons.sms.mapping.XMLMapping;
import com.packer.commons.sms.mapping.impl.XMLClassPathMapping;

public class ClassPathXMLSmsFactory extends AbstractXMLSmsFactory {
    public ClassPathXMLSmsFactory() {
        this.setConfig("/packet-config.xml");
    }

    protected XMLMapping getMapping(String config) {
        XMLClassPathMapping mapping = new XMLClassPathMapping(config);
        mapping.setCondition(this.getCondition());
        return mapping;
    }
}
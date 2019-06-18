package com.packer.commons.sms;
import com.packer.commons.sms.mapping.XMLMapping;
import com.packer.commons.sms.mapping.impl.XMLFileMapping;

public class FileXMLSmsFactory extends AbstractXMLSmsFactory {
    public FileXMLSmsFactory() {
    }

    protected XMLMapping getMapping(String config) {
        XMLFileMapping mapping = new XMLFileMapping(config);
        mapping.setCondition(this.getCondition());
        return mapping;
    }
}

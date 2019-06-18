package com.packer.commons.sms.mapping.impl;

import com.packer.commons.sms.mapping.XMLConfig;
import java.net.URL;

public class XMLClassPathParser extends XMLParser {
    String rootDir;

    public XMLClassPathParser(String rootConfigAbsolutePath) {
        super(rootConfigAbsolutePath);
        this.rootDir = rootConfigAbsolutePath.substring(0, rootConfigAbsolutePath.lastIndexOf("/"));
        this.rootConfigCanonicalPath = rootConfigAbsolutePath.substring(rootConfigAbsolutePath.lastIndexOf("/"), rootConfigAbsolutePath.length());
    }

    public XMLConfig getXMLConfig(String canonicalPath) {
        String configPath = this.rootDir + canonicalPath;
        URL url = XMLClassPathParser.class.getResource(configPath);
        if (url == null) {
            String tempPath;
            for(tempPath = configPath; tempPath.startsWith("/"); tempPath = tempPath.substring(1)) {
            }

            url = Thread.currentThread().getContextClassLoader().getResource(tempPath);
            if (url == null) {
                throw new MappingException("The config file in classpath [" + configPath + "] really exist?");
            }
        }

        XMLConfig c = new XMLConfig(url);
        return c;
    }
}

package com.packer.commons.sms.mapping.impl;

import com.packer.commons.sms.mapping.XMLConfig;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

public class XMLFileParser extends XMLParser {
    private String path;
    private Set<String> files;

    public XMLFileParser(String path) {
        super(path);
        File f = new File(path);
        this.path = f.getParentFile().getAbsolutePath();
        this.rootConfigCanonicalPath = File.separator + f.getName();
        this.files = new HashSet();
    }

    public Set<String> getFiles() {
        return this.files;
    }

    public XMLConfig getXMLConfig(String path) {
        File f = null;
        if (this.path == null) {
            f = new File(path);
        } else {
            f = new File(this.path + path);
        }

        if (f.exists()) {
            try {
                this.files.add(f.getAbsolutePath());
                XMLConfig c = new XMLConfig(f.toURL());
                return c;
            } catch (MalformedURLException var4) {
                throw new MappingException("Can't load [" + f.getAbsolutePath() + "] from the file", var4);
            }
        } else {
            throw new MappingException("The config file [" + f.getAbsolutePath() + "] really exist?");
        }
    }
}

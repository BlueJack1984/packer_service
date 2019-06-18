package com.packer.commons.sms.mapping;


import com.packer.commons.sms.mapping.impl.MappingException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;
import nu.xom.Nodes;
import nu.xom.XPathContext;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class XMLConfig {
    public static final String NS_WDPACKER = "http://www.watchdata.com/schema/wdpacker";
    public static final String NS_PACKET = "http://www.watchdata.com/schema/packet";
    public Document cfg = null;
    public Hashtable<String, Object> cache = new Hashtable();
    public XPathContext context;
    Logger log = Logger.getLogger(XMLConfig.class);

    public XMLConfig(URL file) {
        if (file == null) {
            throw new MappingException("the resource may be not exist");
        } else {
            this.context = new XPathContext();
            this.context.addNamespace("g", "http://www.watchdata.com/schema/wdpacker");
            this.context.addNamespace("p", "http://www.watchdata.com/schema/packet");
            this.initCfg(file);
        }
    }

    private SchemaFactory getSchemaFactory() {
        SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        return sf;
    }

    private void validateXMLBySchema(String namespace, InputStream file) throws SAXException, IOException {
        HashMap<String, String> schema = new HashMap();
        schema.put("http://www.watchdata.com/schema/wdpacker", "/wdpacker-cfg.xsd");
        schema.put("http://www.watchdata.com/schema/packet", "/wdpacker-packet.xsd");
        SchemaFactory sf = this.getSchemaFactory();
        Schema s = sf.newSchema(XMLConfig.class.getResource((String)schema.get(namespace)));
        Validator v = s.newValidator();
        v.validate(new StreamSource(file));
        file.close();
    }

    private void initCfg(URL file) {
        try {
            Builder b = new Builder();
            InputStream is = file.openStream();
            this.cfg = b.build(is);
            String namespace = this.cfg.getRootElement().getNamespaceURI();
            this.validateXMLBySchema(namespace, file.openStream());
            is.close();
        } catch (SAXException var5) {
            throw new MappingException("the XML config has an valid format ! please check the XML file" + file, var5);
        } catch (Exception var6) {
            throw new MappingException("init config failed ! " + file, var6);
        }
    }

    public String getSingle(String path) {
        String value = null;
        if (this.cache.containsKey(path)) {
            value = (String)this.cache.get(path);
        } else {
            Nodes ns = this.cfg.query(path, this.context);
            if (ns.size() > 0) {
                value = this.cfg.query(path, this.context).get(0).getValue();
            }

            if (value != null) {
                this.cache.put(path, value);
            }
        }

        return value;
    }

    public String[] getMutil(String path) {
        String[] values = (String[])null;
        if (this.cache.containsKey(path)) {
            values = (String[])this.cache.get(path);
        } else {
            Nodes ns = this.cfg.query(path, this.context);
            if (ns.size() > 0) {
                values = new String[ns.size()];

                for(int i = 0; i < values.length; ++i) {
                    values[i] = ns.get(i).getValue();
                }

                this.cache.put(path, values);
            }
        }

        return values;
    }

    public Nodes getMutilNodes(String path) {
        Nodes values = null;
        if (this.cache.containsKey(path)) {
            values = (Nodes)this.cache.get(path);
        } else {
            values = this.cfg.query(path, this.context);
            if (values != null && values.size() != 0) {
                this.cache.put(path, values);
            }
        }

        return values;
    }

    public Node getSingleNode(String path) {
        Nodes ns = this.getMutilNodes(path);
        return ns != null & ns.size() != 0 ? ns.get(0) : null;
    }

    public void reloadCfg() {
        this.cache.clear();
    }

    public String toXml() {
        return this.cfg.toXML();
    }
}

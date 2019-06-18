package com.packer.commons.sms.mapping.impl;


import com.packer.commons.sms.mapping.XMLConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Nodes;

public class MappingConvertor {
    public MappingConvertor() {
    }

    public static XMLConfig convert(XMLConfig c) {
        Nodes ns = c.getMutilNodes("//p:field");
        List<Element> ls = new ArrayList();
        int s = 0;

        for(int i = 0; i < ns.size(); ++i) {
            Element e = (Element)ns.get(i);
            String l = e.getAttributeValue("len");
            if (l != null && l.startsWith("#")) {
                int len = Integer.parseInt(l.substring(1, l.length()));
                e.getAttribute("len").setValue(String.valueOf(len));
                s += len;
                ls.add(e);
                if (s != 8) {
                    if (s > 8) {
                        throw new RuntimeException("Adjacent bit field has '#length' property must in one Byte(8bits)");
                    }
                } else {
                    Element group = new Element("group", "http://www.watchdata.com/schema/packet");
                    e.getParent().replaceChild(e, group);
                    StringBuilder sb = new StringBuilder();
                    StringBuilder namesb = new StringBuilder();
                    Iterator var12 = ls.iterator();

                    while(var12.hasNext()) {
                        Element ele = (Element)var12.next();
                        int bit = Integer.parseInt(ele.getAttributeValue("len"));
                        sb.append(bit);
                        if (ele.getParent() != null) {
                            ele.getParent().removeChild(ele);
                        }

                        ele.removeAttribute(ele.getAttribute("len"));
                        Attribute bitfieldtype = new Attribute("type", "{TYPE_BIT_FIELD}");
                        ele.addAttribute(bitfieldtype);
                        group.appendChild(ele);
                        namesb.append(ele.getAttributeValue("name"));
                    }

                    Attribute name = new Attribute("name", namesb.toString());
                    group.addAttribute(name);
                    group.addAttribute(new Attribute("type", "{TYPE_BIT}(bits=" + sb + ")"));
                    ls.clear();
                    s = 0;
                }
            }
        }

        c.reloadCfg();
        return c;
    }
}

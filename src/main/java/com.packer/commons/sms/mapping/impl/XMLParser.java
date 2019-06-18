package com.packer.commons.sms.mapping.impl;


import com.packer.commons.sms.SmsException;
import com.packer.commons.sms.base.Property;
import com.packer.commons.sms.mapping.Configuration;
import com.packer.commons.sms.mapping.XMLConfig;
import com.packer.commons.sms.packet.AtomicField;
import com.packer.commons.sms.packet.FieldGroup;
import com.packer.commons.sms.packet.IAutoCascadeManager;
import com.packer.commons.sms.packet.ICountLimit;
import com.packer.commons.sms.packet.IField;
import com.packer.commons.sms.packet.IValueConverter;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.IValueType;
import com.packer.commons.sms.packet.IValueValidator;
import com.packer.commons.sms.packet.Packet;
import com.packer.commons.sms.packet.PacketChannel;
import com.packer.commons.sms.packet.PacketGroup;
import com.packer.commons.sms.packet.StructureUnit;
import com.packer.commons.sms.util.StringUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;
import org.apache.log4j.Logger;

public abstract class XMLParser {
    Logger log;
    XMLConfig global;
    String rootConfigCanonicalPath;
    LoadCondition condition;

    public XMLParser(String path) {
        this(path, new LoadCondition());
    }

    public XMLParser(String path, LoadCondition condition) {
        this.log = Logger.getLogger(XMLParser.class);
        this.rootConfigCanonicalPath = path;
        this.condition = condition;
    }

    public void init() {
        this.global = this.getXMLConfig(this.rootConfigCanonicalPath);
        this.initConstant();
    }

    private void initConstant() {
        if (this.global != null) {
            Nodes nodes = this.global.getMutilNodes("/g:cfg/g:constant");

            for(int i = 0; i < nodes.size(); ++i) {
                Element e = (Element)nodes.get(i);
                Configuration.addCons(e.getAttributeValue("key"), e.getAttributeValue("value"));
            }
        }

    }

    public List<PacketChannel> loadChannels() {
        if (this.global == null) {
            this.init();
        }

        List<PacketChannel> list = new ArrayList();
        Nodes channels = this.global.getMutilNodes("/g:cfg/g:channel");

        for(int i = 0; i < channels.size(); ++i) {
            PacketChannel pc = this.initChannel(channels.get(i));
            pc.validate();
            pc.print(0);
            list.add(pc);
        }

        return list;
    }

    private PacketChannel initChannel(Node n) {
        String name = ((Element)n).getAttributeValue("name");

        try {
            PacketChannel channel = new PacketChannel(name, "");

            for(int i = 0; i < n.getChildCount(); ++i) {
                this.initStructUnit(channel, n.getChild(i));
            }

            return channel;
        } catch (SmsException var5) {
            var5.setPacketChannelName(name);
            throw var5;
        }
    }

    public abstract XMLConfig getXMLConfig(String var1);

    private void addAllPacketGroup(StructureUnit su, Node n) {
        Element e = (Element)n;
        String cmd = e.getAttributeValue("cmd");
        String mapping = e.getAttributeValue("mapping");
        XMLConfig packet = MappingConvertor.convert(this.getXMLConfig(mapping));
        PacketGroup pg = new PacketGroup("", "", cmd);
        Nodes cas = packet.getMutilNodes("//p:cascade");
        Node cascadeManager = packet.getSingleNode("/p:packet/p:cascadeManager");
        if (cas != null & cas.size() != 0 & cascadeManager != null) {
            throw new MappingException("the 'packet' node can't contains  'cascadeManager' node and 'cascade' node at same time ");
        } else {
            pg.setName(packet.getSingle("/p:packet/@name"));
            pg.setBusinessObjClass(packet.getSingle("/p:packet/@class"));

            try {
                StructType st = su.isMoUnit() ? StructType.MO : StructType.MT;
                if (cas.size() == 0) {
                    this.addSinglePacket(st, cmd, pg, su, packet);
                } else {
                    this.addCouplePacket(st, cmd, pg, su, packet);
                }

                su.addPacketGroup(pg);
            } catch (SmsException var11) {
                var11.setPacketGroupName(pg.getName());
                throw var11;
            }
        }
    }

    private void addSinglePacket(StructType st, String cmd, PacketGroup pg, StructureUnit su, XMLConfig packet) {
        Packet p = new Packet(pg.getName(), "", cmd);
        p.setControlFieldsTree(su.getControlFieldsTree());
        pg.addPacket(p);
        this.initPacket(st, p, packet.getMutilNodes("/p:packet/p:*"), (List)null);
        Node cascadeManager = packet.getSingleNode("/p:packet/p:cascadeManager");
        if (cascadeManager != null) {
            String[] para = StringUtil.parseByRegex(((Element)cascadeManager).getAttributeValue("impl"));
            pg.setAutoCascadeSplitor((IAutoCascadeManager)this.invokeProperty(para[0], StringUtil.String2Param(para[1])));
        }

    }

    private void addCouplePacket(StructType st, String cmd, PacketGroup pg, StructureUnit su, XMLConfig packet) {
        Nodes cascades = packet.getMutilNodes("//p:cascade");

        for(int i = 0; i < cascades.size(); ++i) {
            Nodes ns = cascades.get(i).query("*");
            Packet p = new Packet(packet.getSingle("/p:packet/@name"), "", cmd);
            p.setControlFieldsTree(su.getControlFieldsTree());
            List<IField> caslist = this.createRootField(su.isMoUnit() ? StructType.MO : StructType.MT, (FieldGroup)null, ns, (List)null);
            this.initPacket(st, p, packet.getMutilNodes("/p:packet/p:*"), caslist);
            pg.addPacket(p);
        }

    }

    private void initStructUnit(PacketChannel channel, Node n) {
        if (n instanceof Element) {
            Element e = (Element)n;
            String mapping = e.getAttributeValue("mapping");
            StructureUnit su = e.getLocalName().equalsIgnoreCase("MO") ? StructureUnit.createMoUnit(mapping, "") : StructureUnit.createMtUnit(mapping, "");
            XMLConfig controls = MappingConvertor.convert(this.getXMLConfig(mapping));
            this.initStructUnit(su, controls);
            Nodes nodes = n.query("*");

            for(int i = 0; i < nodes.size(); ++i) {
                this.addAllPacketGroup(su, nodes.get(i));
            }

            if (su.isMoUnit()) {
                channel.addMoUnit(su);
            } else {
                channel.addMtUnit(su);
            }
        }

    }

    private Object invokeProperty(String cls, Map<String, String> param) {
        Object o = null;

        try {
            cls = Configuration.getCons(cls) == null ? cls : Configuration.getCons(cls);

            try {
                o = Class.forName(cls).newInstance();
            } catch (ClassNotFoundException var8) {
                o = Thread.currentThread().getContextClassLoader().loadClass(cls).newInstance();
            }

            Iterator var5 = param.keySet().iterator();

            while(var5.hasNext()) {
                String key = (String)var5.next();
                Method m = o.getClass().getMethod(StringUtil.getMethod("get", key));
                Property p = (Property)m.invoke(o);
                p.assignValue((String)param.get(key));
            }
        } catch (Throwable var9) {
            o = new ErrorDelegateExtendPointer();
            ((ErrorDelegateExtendPointer)o).setError(var9);
            ((ErrorDelegateExtendPointer)o).setOriginalExtendPointer(cls, param);
            this.log.error("无法加载[" + cls + "]", var9);
        }

        return o;
    }

    private void dealField(StructType st, AtomicField f, Element e) {
        try {
            if (this.getCondition().canLoad(st, ExtentionType.ValueType)) {
                this.addType(f, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.ValueSource)) {
                this.addValueSource(f, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.Convertor)) {
                this.addConvertor(f, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.Validator)) {
                this.addValidators(f, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.CountLimit)) {
                this.addCountLimit(f, e);
            }

            this.addClassProperty(f, e);
        } catch (SmsException var5) {
            var5.setFieldName(f.getName());
            throw var5;
        }
    }

    private void dealFieldGroup(StructType st, FieldGroup fg, Element e) {
        try {
            fg.setBusinessObjClass(e.getAttributeValue("class"));
            if (this.getCondition().canLoad(st, ExtentionType.ValueType)) {
                this.addType(fg, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.Convertor)) {
                this.addConvertor(fg, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.Validator)) {
                this.addValidators(fg, e);
            }

            if (this.getCondition().canLoad(st, ExtentionType.CountLimit)) {
                this.addCountLimit(fg, e);
            }

            this.addClassProperty(fg, e);
        } catch (SmsException var5) {
            var5.setFieldName(fg.getName());
            throw var5;
        }
    }

    private void addClassProperty(IField f, Element e) {
        String classProperty = e.getAttributeValue("classproperty");
        if (classProperty != null) {
            f.setClassProperty(classProperty);
        }

    }

    private void addCountLimit(IField f, Element e) {
        String countlimit = e.getAttributeValue("count") == null ? Configuration.getCons("global.countlimit.value") : e.getAttributeValue("count");
        ICountLimit c = null;
        String[] s = StringUtil.parseByRegex(countlimit);
        if (s != null && s.length == 2) {
            c = (ICountLimit)this.invokeProperty(s[0], StringUtil.String2Param(s[1]));
        } else {
            Map<String, String> m = new HashMap();
            m.put("countlimit", countlimit);
            c = (ICountLimit)this.invokeProperty("countlimit.fix.impl", m);
        }

        if (f instanceof AtomicField) {
            ((AtomicField)f).setCountLimit(c);
        } else {
            ((FieldGroup)f).setCountLimit(c);
        }

    }

    private void addType(IField f, Element e) {
        String l = e.getAttributeValue("len");
        IValueType type = null;
        if (l != null) {
            Map<String, String> m = new HashMap();
            m.put("len", e.getAttributeValue("len"));
            m.put("padding", e.getAttributeValue("padding") == null ? Configuration.getCons("global.padding.value") : e.getAttributeValue("padding"));
            m.put("padtype", e.getAttributeValue("padtype") == null ? Configuration.getCons("global.padding.position") : e.getAttributeValue("padtype"));
            type = (IValueType)this.invokeProperty("type.fix.impl", m);
        } else if (e.getAttributeValue("type") != null) {
            String[] s = StringUtil.parseByRegex(e.getAttributeValue("type"));
            if (s == null || s.length != 2) {
                throw new MappingException("the type is invalid,shoule be \"{class}\" or \"{class}(param1=v1,param2=v2)\"");
            }

            type = (IValueType)this.invokeProperty(s[0], StringUtil.String2Param(s[1]));
        }

        if (f instanceof AtomicField) {
            ((AtomicField)f).setValueType(type);
        } else {
            ((FieldGroup)f).setValueType(type);
        }

    }

    private void addValueSource(AtomicField f, Element e) {
        if (e.getAttributeValue("val") != null) {
            String[] s = StringUtil.parseByRegex(e.getAttributeValue("val"));
            if (s == null) {
                Map<String, String> m = new HashMap();
                m.put("value", e.getAttributeValue("val"));
                f.setValueSource((IValueSource)this.invokeProperty("value.fix.impl", m));
                f.setValueValidator((IValueValidator)this.invokeProperty("validator.fix.impl", m));
            } else if (s.length == 2) {
                f.setValueSource((IValueSource)this.invokeProperty(s[0], StringUtil.String2Param(s[1])));
            }
        }

    }

    private void addConvertor(IField f, Element e) {
        if (e.getAttributeValue("convertor") != null) {
            String[] s = StringUtil.parseByRegex(e.getAttributeValue("convertor"));
            if (s.length != 2) {
                throw new MappingException("the convertor is invalid,shoule be \"{class}\" or \"{class}(param1=v1,param2=v2)\"");
            }

            IValueConverter con = (IValueConverter)this.invokeProperty(s[0], StringUtil.String2Param(s[1]));
            List<IValueConverter> l = new ArrayList();
            l.add(con);
            if (f instanceof AtomicField) {
                ((AtomicField)f).setValueConverters(l);
            } else {
                ((FieldGroup)f).setValueConverters(l);
            }
        }

    }

    private void addValidators(IField f, Element e) {
        if (e.getAttributeValue("validator") != null) {
            String[] s = StringUtil.parseByRegex(e.getAttributeValue("validator"));
            if (s.length != 2) {
                throw new MappingException("the validator is invalid,shoule be \"{class}\" or \"{class}(param1=v1,param2=v2)\"");
            }

            if (f instanceof AtomicField) {
                ((AtomicField)f).setValueValidator((IValueValidator)this.invokeProperty(s[0], StringUtil.String2Param(s[1])));
            } else {
                ((FieldGroup)f).setValueValidator((IValueValidator)this.invokeProperty(s[0], StringUtil.String2Param(s[1])));
            }
        }

    }

    private void initPacket(StructType st, Packet p, Nodes childs, List<IField> cascade) {
        List<IField> bussness = this.createRootField(st, (FieldGroup)null, childs, cascade);
        Iterator var7 = bussness.iterator();

        while(var7.hasNext()) {
            IField f = (IField)var7.next();
            p.addBusinessField(f);
        }

    }

    private void initStructUnit(StructureUnit su, XMLConfig config) {
        try {
            Element e = (Element)config.getSingleNode("/p:struct");
            String bussfield = e.getAttributeValue("bussfield");
            String cmdfield = e.getAttributeValue("cmdfield");
            String name = e.getAttributeValue("name");
            su.setName(name);
            if (bussfield == null) {
                throw new MappingException("The struct must set the bussfield  attribute");
            } else {
                su.setBussfield(bussfield);
                su.setCmdField(cmdfield);
                List<IField> controls = this.createRootField(su.isMoUnit() ? StructType.MO : StructType.MT, (FieldGroup)null, config.getMutilNodes("/p:struct/p:*"), (List)null);
                Iterator var9 = controls.iterator();

                while(var9.hasNext()) {
                    IField f = (IField)var9.next();
                    su.addControlField(f);
                }

                this.validate(su);
            }
        } catch (SmsException var10) {
            var10.setStructUnitName(su.getName());
            throw var10;
        }
    }

    public void validate(StructureUnit su) {
        try {
            AtomicField ud = (AtomicField)su.getControlField(su.getBussfield());
            if (ud == null) {
                throw new MappingException("the control fields doesn't have a BUSS FIELD" + su.getBussfield() + " field");
            } else {
                Class<?> valuec = Class.forName(Configuration.getCons("VALUE_BUSS"));
                if (ud.getValueSource() != null) {
                    if (ud.getValueSource().getClass() != valuec) {
                        throw new MappingException("The Business Field :" + su.getBussfield() + "'s value should be " + "VALUE_BUSS");
                    }
                } else {
                    ud.setValueSource((IValueSource)this.invokeProperty("VALUE_BUSS", new HashMap()));
                }

                if (ud.getValueType() == null) {
                    ud.setValueType(ud.getValueType() == null ? (IValueType)this.invokeProperty("TYPE_BUSS", new HashMap()) : ud.getValueType());
                }

                if (su.getCmdField() != null && !su.getCmdField().equals("")) {
                    AtomicField cmd = (AtomicField)su.getControlField(su.getCmdField());
                    if (cmd == null) {
                        throw new MappingException("the control fields doesn't have a CMD FIELD " + su.getCmdField() + " field");
                    }

                    cmd.setValueSource((IValueSource)this.invokeProperty("VALUE_CMD", new HashMap()));
                }

            }
        } catch (Exception var5) {
            ((SmsException)var5).setStructUnitName(su.getName());
            throw (SmsException)var5;
        }
    }

    private List<IField> createRootField(StructType st, FieldGroup fg, Nodes childs, List<IField> cascade) {
        List<IField> root = new ArrayList();

        for(int i = 0; i < childs.size(); ++i) {
            Element e = (Element)childs.get(i);
            String name = e.getAttributeValue("name");
            if (e.getLocalName().equals("field")) {
                AtomicField f = new AtomicField(fg == null ? null : fg.getName(), name, "");
                this.dealField(st, f, e);
                if (fg == null) {
                    root.add(f);
                } else {
                    fg.addField(f);
                }
            } else if (e.getLocalName().equals("group")) {
                FieldGroup f = new FieldGroup(fg == null ? null : fg.getName(), name, "");
                this.createRootField(st, f, e.query("*"), (List)null);
                this.dealFieldGroup(st, f, e);
                if (fg == null) {
                    root.add(f);
                } else {
                    fg.addField(f);
                }
            } else if (e.getLocalName().equals("cascade") && cascade != null) {
                Iterator var10 = cascade.iterator();

                while(var10.hasNext()) {
                    IField f = (IField)var10.next();
                    if (fg == null) {
                        root.add(f);
                    } else {
                        fg.addField(f);
                    }
                }

                cascade.clear();
            }
        }

        return root;
    }

    public LoadCondition getCondition() {
        return this.condition;
    }

    public void setCondition(LoadCondition condition) {
        this.condition = condition;
    }
}

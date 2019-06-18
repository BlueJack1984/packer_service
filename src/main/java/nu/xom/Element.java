package nu.xom;


import java.util.*;

public class Element extends ParentNode {
    private String localName;
    private String prefix;
    private String URI;
    private Attribute[] attributes;
    private int numAttributes;
    Namespaces namespaces;

    public Element(String var1) {
        this(var1, "");
    }

    public Element(String var1, String var2) {
        this.attributes = null;
        this.numAttributes = 0;
        this.namespaces = null;
        String var3 = "";
        String var4 = var1;
        int var5 = var1.indexOf(58);
        if (var5 > 0) {
            var3 = var1.substring(0, var5);
            var4 = var1.substring(var5 + 1);
        }

        this._setNamespacePrefix(var3);
        this._setNamespaceURI(var2);

        try {
            this._setLocalName(var4);
        } catch (IllegalNameException var7) {
            var7.setData(var1);
            throw var7;
        }
    }

    private Element() {
        this.attributes = null;
        this.numAttributes = 0;
        this.namespaces = null;
    }

    static nu.xom.Element build(String var0, String var1, String var2) {
        nu.xom.Element var3 = new nu.xom.Element();
        String var4 = "";
        int var5 = var0.indexOf(58);
        if (var5 >= 0) {
            var4 = var0.substring(0, var5);
        }

        var3.prefix = var4;
        var3.localName = var2;
        if (!"".equals(var1)) {
            Verifier.checkAbsoluteURIReference(var1);
        }

        var3.URI = var1;
        return var3;
    }

    public Element(nu.xom.Element var1) {
        this.attributes = null;
        this.numAttributes = 0;
        this.namespaces = null;
        this.prefix = var1.prefix;
        this.localName = var1.localName;
        this.URI = var1.URI;
        if (var1.namespaces != null) {
            this.namespaces = var1.namespaces.copy();
        }

        if (var1.attributes != null) {
            this.attributes = var1.copyAttributes(this);
            this.numAttributes = var1.numAttributes;
        }

        this.actualBaseURI = var1.findActualBaseURI();
        copyChildren(var1, this);
    }

    private Attribute[] copyAttributes(nu.xom.Element var1) {
        if (this.numAttributes == 0) {
            return null;
        } else {
            Attribute[] var2 = new Attribute[this.numAttributes];

            for(int var3 = 0; var3 < this.numAttributes; ++var3) {
                var2[var3] = (Attribute)this.attributes[var3].copy();
                var2[var3].setParent(var1);
            }

            return var2;
        }
    }

    private static nu.xom.Element copyTag(nu.xom.Element var0) {
        nu.xom.Element var1 = var0.shallowCopy();
        if (var0.namespaces != null) {
            var1.namespaces = var0.namespaces.copy();
        }

        if (var0.attributes != null) {
            var1.attributes = var0.copyAttributes(var1);
            var1.numAttributes = var0.numAttributes;
        }

        var1.actualBaseURI = var0.findActualBaseURI();
        return var1;
    }

    private static void copyChildren(nu.xom.Element var0, nu.xom.Element var1) {
        if (var0.getChildCount() != 0) {
            nu.xom.Element var2 = var1;
            Object var3 = var0;
            int var4 = 0;
            int[] var5 = new int[10];
            int var6 = 0;
            var5[0] = 0;
            boolean var7 = false;

            while(true) {
                while(true) {
                    if (!var7 && ((Node)var3).getChildCount() > 0) {
                        var3 = ((Node)var3).getChild(0);
                        var4 = 0;
                        ++var6;
                        var5 = grow(var5, var6);
                        var5[var6] = 0;
                    } else {
                        var7 = false;
                        ParentNode var8 = ((Node)var3).getParent();
                        if (var8.getChildCount() - 1 == var4) {
                            var3 = var8;
                            --var6;
                            if (var8 == var0) {
                                return;
                            }

                            var2 = (nu.xom.Element)var2.getParent();
                            var4 = var5[var6];
                            var7 = true;
                            continue;
                        }

                        ++var4;
                        var5[var6] = var4;
                        var3 = var8.getChild(var4);
                    }

                    if (((Node)var3).isElement()) {
                        nu.xom.Element var9 = copyTag((nu.xom.Element)var3);
                        var2.appendChild(var9);
                        if (((Node)var3).getChildCount() > 0) {
                            var2 = var9;
                        }
                    } else {
                        Node var10 = ((Node)var3).copy();
                        var2.appendChild(var10);
                    }
                }
            }
        }
    }

    private static int[] grow(int[] var0, int var1) {
        if (var1 < var0.length) {
            return var0;
        } else {
            int[] var2 = new int[var0.length * 2];
            System.arraycopy(var0, 0, var2, 0, var0.length);
            return var2;
        }
    }

    public final Elements getChildElements(String var1) {
        return this.getChildElements(var1, "");
    }

    public final Elements getChildElements(String var1, String var2) {
        if (var2 == null) {
            var2 = "";
        }

        if (var1 == null) {
            var1 = "";
        }

        Elements var3 = new Elements();

        for(int var4 = 0; var4 < this.getChildCount(); ++var4) {
            Node var5 = this.getChild(var4);
            if (var5.isElement()) {
                nu.xom.Element var6 = (nu.xom.Element)var5;
                if ((var1.equals(var6.getLocalName()) || var1.length() == 0) && var2.equals(var6.getNamespaceURI())) {
                    var3.add(var6);
                }
            }
        }

        return var3;
    }

    public final Elements getChildElements() {
        Elements var1 = new Elements();

        for(int var2 = 0; var2 < this.getChildCount(); ++var2) {
            Node var3 = this.getChild(var2);
            if (var3.isElement()) {
                nu.xom.Element var4 = (nu.xom.Element)var3;
                var1.add(var4);
            }
        }

        return var1;
    }

    public final nu.xom.Element getFirstChildElement(String var1) {
        return this.getFirstChildElement(var1, "");
    }

    public final nu.xom.Element getFirstChildElement(String var1, String var2) {
        for(int var3 = 0; var3 < this.getChildCount(); ++var3) {
            Node var4 = this.getChild(var3);
            if (var4.isElement()) {
                nu.xom.Element var5 = (nu.xom.Element)var4;
                if (var1.equals(var5.getLocalName()) && var2.equals(var5.getNamespaceURI())) {
                    return var5;
                }
            }
        }

        return null;
    }

    public void addAttribute(Attribute var1) {
        if (var1.getParent() != null) {
            throw new MultipleParentException("Attribute already has a parent");
        } else {
            String var2 = var1.getNamespacePrefix();
            if (var2.length() != 0 && !"xml".equals(var2)) {
                if (this.prefix.equals(var1.getNamespacePrefix()) && !this.getNamespaceURI().equals(var1.getNamespaceURI())) {
                    throw new NamespaceConflictException("Prefix of " + var1.getQualifiedName() + " conflicts with element prefix " + this.prefix);
                }

                if (this.namespaces != null) {
                    String var3 = this.namespaces.getURI(var1.getNamespacePrefix());
                    if (var3 != null && !var3.equals(var1.getNamespaceURI())) {
                        throw new NamespaceConflictException("Attribute prefix  " + var2 + " conflicts with namespace declaration.");
                    }
                }
            }

            if (this.attributes == null) {
                this.attributes = new Attribute[1];
            }

            this.checkPrefixConflict(var1);
            Attribute var4 = this.getAttribute(var1.getLocalName(), var1.getNamespaceURI());
            if (var4 != null) {
                this.remove(var4);
            }

            this.add(var1);
            var1.setParent(this);
        }
    }

    private void add(Attribute var1) {
        if (this.numAttributes == this.attributes.length) {
            Attribute[] var2 = new Attribute[this.attributes.length * 2];
            System.arraycopy(this.attributes, 0, var2, 0, this.numAttributes);
            this.attributes = var2;
        }

        this.attributes[this.numAttributes] = var1;
        ++this.numAttributes;
    }

    private boolean remove(Attribute var1) {
        int var2 = -1;

        int var3;
        for(var3 = 0; var3 < this.attributes.length; ++var3) {
            if (this.attributes[var3] == var1) {
                var2 = var3;
                break;
            }
        }

        if (var2 == -1) {
            return false;
        } else {
            var3 = this.numAttributes - var2 - 1;
            if (var3 > 0) {
                System.arraycopy(this.attributes, var2 + 1, this.attributes, var2, var3);
            }

            --this.numAttributes;
            this.attributes[this.numAttributes] = null;
            return true;
        }
    }

    void fastAddAttribute(Attribute var1) {
        if (this.attributes == null) {
            this.attributes = new Attribute[1];
        }

        this.add(var1);
        var1.setParent(this);
    }

    public Attribute removeAttribute(Attribute var1) {
        if (this.attributes == null) {
            throw new NoSuchAttributeException("Tried to remove attribute " + var1.getQualifiedName() + " from non-parent element");
        } else if (var1 == null) {
            throw new NullPointerException("Tried to remove null attribute");
        } else if (this.remove(var1)) {
            var1.setParent((ParentNode)null);
            return var1;
        } else {
            throw new NoSuchAttributeException("Tried to remove attribute " + var1.getQualifiedName() + " from non-parent element");
        }
    }

    public final Attribute getAttribute(String var1) {
        return this.getAttribute(var1, "");
    }

    public final Attribute getAttribute(String var1, String var2) {
        if (this.attributes == null) {
            return null;
        } else {
            for(int var3 = 0; var3 < this.numAttributes; ++var3) {
                Attribute var4 = this.attributes[var3];
                if (var4.getLocalName().equals(var1) && var4.getNamespaceURI().equals(var2)) {
                    return var4;
                }
            }

            return null;
        }
    }

    public final String getAttributeValue(String var1) {
        return this.getAttributeValue(var1, "");
    }

    public final int getAttributeCount() {
        return this.numAttributes;
    }

    public final Attribute getAttribute(int var1) {
        if (this.attributes == null) {
            throw new IndexOutOfBoundsException("Element does not have any attributes");
        } else {
            return this.attributes[var1];
        }
    }

    public final String getAttributeValue(String var1, String var2) {
        Attribute var3 = this.getAttribute(var1, var2);
        return var3 == null ? null : var3.getValue();
    }

    public final String getLocalName() {
        return this.localName;
    }

    public final String getQualifiedName() {
        return this.prefix.length() == 0 ? this.localName : this.prefix + ":" + this.localName;
    }

    public final String getNamespacePrefix() {
        return this.prefix;
    }

    public final String getNamespaceURI() {
        return this.URI;
    }

    public final String getNamespaceURI(String var1) {
        nu.xom.Element var2 = this;

        String var3;
        for(var3 = this.getLocalNamespaceURI(var1); var3 == null; var3 = var2.getLocalNamespaceURI(var1)) {
            ParentNode var4 = var2.getParent();
            if (var4 == null || var4.isDocument()) {
                break;
            }

            var2 = (nu.xom.Element)var4;
        }

        if (var3 == null && "".equals(var1)) {
            var3 = "";
        }

        return var3;
    }

    final String getLocalNamespaceURI(String var1) {
        if (var1.equals(this.prefix)) {
            return this.URI;
        } else if ("xml".equals(var1)) {
            return "http://www.w3.org/XML/1998/namespace";
        } else if ("xmlns".equals(var1)) {
            return "";
        } else {
            if (this.namespaces != null) {
                String var2 = this.namespaces.getURI(var1);
                if (var2 != null) {
                    return var2;
                }
            }

            if (var1.length() != 0 && this.attributes != null) {
                for(int var4 = 0; var4 < this.numAttributes; ++var4) {
                    Attribute var3 = this.attributes[var4];
                    if (var3.getNamespacePrefix().equals(var1)) {
                        return var3.getNamespaceURI();
                    }
                }
            }

            return null;
        }
    }

    public void setLocalName(String var1) {
        this._setLocalName(var1);
    }

    private void _setLocalName(String var1) {
        Verifier.checkNCName(var1);
        this.localName = var1;
    }

    public void setNamespaceURI(String var1) {
        this._setNamespaceURI(var1);
    }

    private void _setNamespaceURI(String var1) {
        if (var1 == null) {
            var1 = "";
        }

        if (!var1.equals(this.URI)) {
            if (var1.length() == 0) {
                if (this.prefix.length() != 0) {
                    throw new NamespaceConflictException("Prefixed elements must have namespace URIs.");
                }
            } else {
                Verifier.checkAbsoluteURIReference(var1);
            }

            if (this.namespaces != null) {
                String var2 = this.namespaces.getURI(this.prefix);
                if (var2 != null) {
                    throw new NamespaceConflictException("new URI conflicts with existing prefix");
                }
            }

            if (var1.length() > 0 && this.attributes != null) {
                for(int var5 = 0; var5 < this.numAttributes; ++var5) {
                    Attribute var3 = this.attributes[var5];
                    String var4 = var3.getNamespacePrefix();
                    if (var4.length() != 0 && var3.getNamespacePrefix().equals(this.prefix)) {
                        throw new NamespaceConflictException("new element URI " + var1 + " conflicts with attribute " + var3.getQualifiedName());
                    }
                }
            }

            if ("http://www.w3.org/XML/1998/namespace".equals(var1) && !"xml".equals(this.prefix)) {
                throw new NamespaceConflictException("Wrong prefix " + this.prefix + " for the http://www.w3.org/XML/1998/namespace namespace URI");
            } else if ("xml".equals(this.prefix) && !"http://www.w3.org/XML/1998/namespace".equals(var1)) {
                throw new NamespaceConflictException("Wrong namespace URI " + var1 + " for the xml prefix");
            } else {
                this.URI = var1;
            }
        }
    }

    public void setNamespacePrefix(String var1) {
        this._setNamespacePrefix(var1);
    }

    private void _setNamespacePrefix(String var1) {
        if (var1 == null) {
            var1 = "";
        }

        if (var1.length() != 0) {
            Verifier.checkNCName(var1);
        }

        String var2 = this.getLocalNamespaceURI(var1);
        if (var2 != null) {
            if (!var2.equals(this.URI) && !"xml".equals(var1)) {
                throw new NamespaceConflictException(var1 + " conflicts with existing prefix");
            }
        } else if ("".equals(this.URI) && !"".equals(var1)) {
            throw new NamespaceConflictException("Cannot assign prefix to element in no namespace");
        }

        this.prefix = var1;
    }

    void insertionAllowed(Node var1, int var2) {
        if (var1 == null) {
            throw new NullPointerException("Tried to insert a null child in the tree");
        } else if (var1.getParent() != null) {
            throw new MultipleParentException(var1.toString() + " child already has a parent.");
        } else if (var1.isElement()) {
            checkCycle(var1, this);
        } else if (!var1.isText() && !var1.isProcessingInstruction() && !var1.isComment()) {
            throw new IllegalAddException("Cannot add a " + var1.getClass().getName() + " to an Element.");
        }
    }

    private static void checkCycle(Node var0, ParentNode var1) {
        if (var0 == var1) {
            throw new CycleException("Cannot add a node to itself");
        } else if (var0.getChildCount() != 0) {
            do {
                if ((var1 = var1.getParent()) == null) {
                    return;
                }
            } while(var1 != var0);

            throw new CycleException("Cannot add an ancestor as a child");
        }
    }

    public void insertChild(String var1, int var2) {
        if (var1 == null) {
            throw new NullPointerException("Inserted null string");
        } else {
            super.fastInsertChild(new Text(var1), var2);
        }
    }

    public void appendChild(String var1) {
        this.insertChild(new Text(var1), this.getChildCount());
    }

    public Nodes removeChildren() {
        int var1 = this.getChildCount();
        Nodes var2 = new Nodes();

        for(int var3 = 0; var3 < var1; ++var3) {
            Node var4 = this.getChild(var3);
            if (var4.isElement()) {
                this.fillInBaseURI((nu.xom.Element)var4);
            }

            var4.setParent((ParentNode)null);
            var2.append(var4);
        }

        this.children = null;
        this.childCount = 0;
        return var2;
    }

    public void addNamespaceDeclaration(String var1, String var2) {
        if (var1 == null) {
            var1 = "";
        }

        if (var2 == null) {
            var2 = "";
        }

        if (var1.equals("xmlns")) {
            if (!var2.equals("")) {
                throw new NamespaceConflictException("The xmlns prefix cannot bound to any URI");
            }
        } else if (var1.equals("xml")) {
            if (!var2.equals("http://www.w3.org/XML/1998/namespace")) {
                throw new NamespaceConflictException("Wrong namespace URI for xml prefix: " + var2);
            }
        } else if (var2.equals("http://www.w3.org/XML/1998/namespace")) {
            throw new NamespaceConflictException("Wrong prefix for http://www.w3.org/XML/1998/namespace namespace: " + var1);
        } else {
            if (var1.length() != 0) {
                Verifier.checkNCName(var1);
                Verifier.checkAbsoluteURIReference(var2);
            } else if (var2.length() != 0) {
                Verifier.checkAbsoluteURIReference(var2);
            }

            String var3 = this.getLocalNamespaceURI(var1);
            if (var3 != null && !var3.equals(var2)) {
                String var4;
                if (var1.equals("")) {
                    var4 = "Additional namespace " + var2 + " conflicts with existing default namespace " + var3;
                } else {
                    var4 = "Additional namespace " + var2 + " for the prefix " + var1 + " conflicts with existing namespace binding " + var3;
                }

                throw new NamespaceConflictException(var4);
            } else {
                if (this.namespaces == null) {
                    this.namespaces = new Namespaces();
                }

                this.namespaces.put(var1, var2);
            }
        }
    }

    public void removeNamespaceDeclaration(String var1) {
        if (this.namespaces != null) {
            this.namespaces.remove(var1);
        }

    }

    public final int getNamespaceDeclarationCount() {
        HashSet var1 = null;
        if (this.namespaces != null) {
            var1 = new HashSet(this.namespaces.getPrefixes());
            var1.add(this.prefix);
        }

        if ("xml".equals(this.prefix)) {
            var1 = new HashSet();
        }

        int var2 = this.getAttributeCount();

        for(int var3 = 0; var3 < var2; ++var3) {
            Attribute var4 = this.attributes[var3];
            String var5 = var4.getNamespacePrefix();
            if (var5.length() != 0 && !"xml".equals(var5)) {
                if (var1 == null) {
                    var1 = new HashSet();
                    var1.add(this.prefix);
                }

                var1.add(var5);
            }
        }

        if (var1 == null) {
            return 1;
        } else {
            return var1.size();
        }
    }

    Map getNamespacePrefixesInScope() {
        HashMap var1 = new HashMap();
        nu.xom.Element var2 = this;

        while(true) {
            if (!"xml".equals(var2.prefix)) {
                this.addPrefixIfNotAlreadyPresent(var1, var2, var2.prefix);
            }

            int var3;
            int var4;
            if (var2.attributes != null) {
                var3 = var2.numAttributes;

                for(var4 = 0; var4 < var3; ++var4) {
                    Attribute var5 = var2.attributes[var4];
                    String var6 = var5.getNamespacePrefix();
                    if (var6.length() != 0 && !"xml".equals(var6)) {
                        this.addPrefixIfNotAlreadyPresent(var1, var2, var6);
                    }
                }
            }

            if (var2.namespaces != null) {
                var3 = var2.namespaces.size();

                for(var4 = 0; var4 < var3; ++var4) {
                    String var8 = var2.namespaces.getPrefix(var4);
                    this.addPrefixIfNotAlreadyPresent(var1, var2, var8);
                }
            }

            ParentNode var7 = var2.getParent();
            if (var7 == null || var7.isDocument() || var7.isDocumentFragment()) {
                return var1;
            }

            var2 = (nu.xom.Element)var7;
        }
    }

    private void addPrefixIfNotAlreadyPresent(HashMap var1, nu.xom.Element var2, String var3) {
        if (!var1.containsKey(var3)) {
            var1.put(var3, var2.getLocalNamespaceURI(var3));
        }

    }

    public final String getNamespacePrefix(int var1) {
        if (var1 < 0) {
            throw new IndexOutOfBoundsException("Negative prefix number " + var1);
        } else if (var1 == 0 && !"xml".equals(this.prefix)) {
            return this.prefix;
        } else {
            Set var2 = this.getNamespacePrefixes();
            Iterator var3 = var2.iterator();

            try {
                for(int var4 = 0; var4 < var1; ++var4) {
                    var3.next();
                }

                return (String)var3.next();
            } catch (NoSuchElementException var5) {
                throw new IndexOutOfBoundsException("No " + var1 + "th namespace");
            }
        }
    }

    private Set getNamespacePrefixes() {
        LinkedHashSet var1 = new LinkedHashSet();
        if (!"xml".equals(this.prefix)) {
            var1.add(this.prefix);
        }

        if (this.namespaces != null) {
            var1.addAll(this.namespaces.getPrefixes());
        }

        if (this.attributes != null) {
            int var2 = this.getAttributeCount();

            for(int var3 = 0; var3 < var2; ++var3) {
                Attribute var4 = this.attributes[var3];
                String var5 = var4.getNamespacePrefix();
                if (var5.length() != 0 && !"xml".equals(var5)) {
                    var1.add(var5);
                }
            }
        }

        return var1;
    }

    public void setBaseURI(String var1) {
        this.setActualBaseURI(var1);
    }

    public String getBaseURI() {
        String var1 = "";
        String var2 = this.getActualBaseURI();
        Object var3 = this;

        while(true) {
            String var4 = ((ParentNode)var3).getActualBaseURI();
            if (var2.length() != 0 && !var2.equals(var4)) {
                var1 = nu.xom.URIUtil.absolutize(var2, var1);
                break;
            }

            if (((ParentNode)var3).isDocument()) {
                var1 = nu.xom.URIUtil.absolutize(var4, var1);
                break;
            }

            Attribute var5 = ((nu.xom.Element)var3).getAttribute("base", "http://www.w3.org/XML/1998/namespace");
            if (var5 != null) {
                String var6 = var5.getValue();
                String var7 = nu.xom.URIUtil.toURI(var6);
                if ("".equals(var7)) {
                    var1 = this.getEntityURI();
                    break;
                }

                if (this.legalURI(var7)) {
                    if ("".equals(var1)) {
                        var1 = var7;
                    } else {
                        if (nu.xom.URIUtil.isOpaque(var7)) {
                            break;
                        }

                        var1 = nu.xom.URIUtil.absolutize(var7, var1);
                    }

                    if (nu.xom.URIUtil.isAbsolute(var7)) {
                        break;
                    }
                }
            }

            var3 = ((ParentNode)var3).getParent();
            if (var3 == null) {
                var1 = nu.xom.URIUtil.absolutize(var4, var1);
                break;
            }
        }

        return nu.xom.URIUtil.isAbsolute(var1) ? var1 : "";
    }

    private String getEntityURI() {
        for(Object var1 = this; var1 != null; var1 = ((ParentNode)var1).getParent()) {
            if (((ParentNode)var1).actualBaseURI != null && ((ParentNode)var1).actualBaseURI.length() != 0) {
                return ((ParentNode)var1).actualBaseURI;
            }
        }

        return "";
    }

    private boolean legalURI(String var1) {
        try {
            Verifier.checkURIReference(var1);
            return true;
        } catch (MalformedURIException var3) {
            return false;
        }
    }

    public final String toXML() {
        StringBuffer var1 = new StringBuffer(1024);
        Object var2 = this;
        boolean var3 = false;
        int var4 = -1;
        int[] var5 = new int[10];
        int var6 = 0;
        var5[0] = -1;

        while(true) {
            while(!var3 && ((Node)var2).getChildCount() > 0) {
                writeStartTag((nu.xom.Element)var2, var1);
                var2 = ((Node)var2).getChild(0);
                var4 = 0;
                ++var6;
                var5 = grow(var5, var6);
                var5[var6] = 0;
            }

            if (var3) {
                writeEndTag((nu.xom.Element)var2, var1);
                if (var2 == this) {
                    break;
                }
            } else if (((Node)var2).isElement()) {
                writeStartTag((nu.xom.Element)var2, var1);
                if (var2 == this) {
                    break;
                }
            } else {
                var1.append(((Node)var2).toXML());
            }

            var3 = false;
            ParentNode var7 = ((Node)var2).getParent();
            if (var7.getChildCount() - 1 == var4) {
                var2 = var7;
                --var6;
                if (var7 != this) {
                    var4 = var5[var6];
                }

                var3 = true;
            } else {
                ++var4;
                var5[var6] = var4;
                var2 = var7.getChild(var4);
            }
        }

        return var1.toString();
    }

    private static void writeStartTag(nu.xom.Element var0, StringBuffer var1) {
        var1.append('<');
        var1.append(var0.getQualifiedName());
        ParentNode var2 = var0.getParent();

        int var3;
        for(var3 = 0; var3 < var0.getNamespaceDeclarationCount(); ++var3) {
            String var4 = var0.getNamespacePrefix(var3);
            String var5 = var0.getNamespaceURI(var4);
            if (var2 != null && var2.isElement()) {
                nu.xom.Element var6 = (nu.xom.Element)var2;
                if (var5.equals(var6.getNamespaceURI(var4))) {
                    continue;
                }
            } else if (var5.length() == 0) {
                continue;
            }

            var1.append(" xmlns");
            if (var4.length() > 0) {
                var1.append(':');
                var1.append(var4);
            }

            var1.append("=\"");
            var1.append(escape(var5));
            var1.append('"');
        }

        if (var0.attributes != null) {
            for(var3 = 0; var3 < var0.numAttributes; ++var3) {
                Attribute var7 = var0.attributes[var3];
                var1.append(' ');
                var1.append(var7.toXML());
            }
        }

        if (var0.getChildCount() > 0) {
            var1.append('>');
        } else {
            var1.append(" />");
        }

    }

    private static String escape(String var0) {
        int var1 = var0.length();
        StringBuffer var2 = new StringBuffer(var1 + 12);

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            if (var4 == '&') {
                var2.append("&amp;");
            } else {
                var2.append(var4);
            }
        }

        return var2.toString();
    }

    private static void writeEndTag(nu.xom.Element var0, StringBuffer var1) {
        var1.append("</");
        var1.append(var0.getQualifiedName());
        var1.append('>');
    }

    public final String getValue() {
        int var1 = this.getChildCount();
        if (var1 == 0) {
            return "";
        } else {
            Object var2 = this.getChild(0);
            if (var1 == 1 && ((Node)var2).isText()) {
                return ((Node)var2).getValue();
            } else {
                StringBuffer var3 = new StringBuffer();
                int var4 = 0;
                int[] var5 = new int[10];
                int var6 = 0;
                var5[0] = 0;
                boolean var7 = false;

                while(true) {
                    while(var7 || ((Node)var2).getChildCount() <= 0) {
                        var7 = false;
                        if (((Node)var2).isText()) {
                            var3.append(((Node)var2).getValue());
                        }

                        ParentNode var8 = ((Node)var2).getParent();
                        if (var8.getChildCount() - 1 == var4) {
                            var2 = var8;
                            --var6;
                            if (var8 == this) {
                                return var3.toString();
                            }

                            var4 = var5[var6];
                            var7 = true;
                        } else {
                            ++var4;
                            var5[var6] = var4;
                            var2 = var8.getChild(var4);
                        }
                    }

                    var2 = ((Node)var2).getChild(0);
                    var4 = 0;
                    ++var6;
                    var5 = grow(var5, var6);
                    var5[var6] = 0;
                }
            }
        }
    }

    public Node copy() {
        nu.xom.Element var1 = copyTag(this);
        copyChildren(this, var1);
        return var1;
    }

    protected nu.xom.Element shallowCopy() {
        return new nu.xom.Element(this.getQualifiedName(), this.getNamespaceURI());
    }

    public String toString() {
        return "[" + this.getClass().getName() + ": " + this.getQualifiedName() + "]";
    }

    boolean isElement() {
        return true;
    }

    private void checkPrefixConflict(Attribute var1) {
        String var2 = var1.getNamespacePrefix();
        String var3 = var1.getNamespaceURI();

        for(int var4 = 0; var4 < this.numAttributes; ++var4) {
            Attribute var5 = this.attributes[var4];
            if (var5.getNamespacePrefix().equals(var2)) {
                if (var5.getNamespaceURI().equals(var3)) {
                    return;
                }

                throw new NamespaceConflictException("Prefix of " + var1.getQualifiedName() + " conflicts with " + var5.getQualifiedName());
            }
        }

    }

    Iterator attributeIterator() {
        return new Iterator() {
            private int next = 0;

            public boolean hasNext() {
                return this.next < nu.xom.Element.this.numAttributes;
            }

            public Object next() throws NoSuchElementException {
                if (this.hasNext()) {
                    Attribute var1 = nu.xom.Element.this.attributes[this.next];
                    ++this.next;
                    return var1;
                } else {
                    throw new NoSuchElementException("No such attribute");
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

package nu.xom;

public class Attribute extends Node {
    private String localName;
    private String prefix;
    private String URI;
    private String value;
    private nu.xom.Attribute.Type type;

    public Attribute(String var1, String var2) {
        this(var1, "", var2, nu.xom.Attribute.Type.UNDECLARED);
    }

    public Attribute(String var1, String var2, nu.xom.Attribute.Type var3) {
        this(var1, "", var2, var3);
    }

    public Attribute(String var1, String var2, String var3) {
        this(var1, var2, var3, nu.xom.Attribute.Type.UNDECLARED);
    }

    public Attribute(String var1, String var2, String var3, nu.xom.Attribute.Type var4) {
        this.value = "";
        this.prefix = "";
        String var5 = var1;
        int var6 = var1.indexOf(58);
        if (var6 > 0) {
            this.prefix = var1.substring(0, var6);
            var5 = var1.substring(var6 + 1);
        }

        try {
            this._setLocalName(var5);
        } catch (IllegalNameException var8) {
            var8.setData(var1);
            throw var8;
        }

        this._setNamespace(this.prefix, var2);
        this._setValue(var3);
        if (this.isXMLID()) {
            this._setType(nu.xom.Attribute.Type.ID);
        } else {
            this._setType(var4);
        }

    }

    public Attribute(nu.xom.Attribute var1) {
        this.value = "";
        this.localName = var1.localName;
        this.prefix = var1.prefix;
        this.URI = var1.URI;
        this.value = var1.value;
        this.type = var1.type;
    }

    private Attribute() {
        this.value = "";
    }

    static nu.xom.Attribute build(String var0, String var1, String var2, nu.xom.Attribute.Type var3, String var4) {
        nu.xom.Attribute var5 = new nu.xom.Attribute();
        String var6 = "";
        int var7 = var0.indexOf(58);
        if (var7 >= 0) {
            var6 = var0.substring(0, var7);
            if ("xml:id".equals(var0)) {
                var3 = nu.xom.Attribute.Type.ID;
                var2 = normalize(var2);
            }
        }

        var5.localName = var4;
        var5.prefix = var6;
        var5.type = var3;
        var5.URI = var1;
        var5.value = var2;
        return var5;
    }

    private static String normalize(String var0) {
        int var1 = var0.length();

        int var2;
        for(var2 = 0; var2 < var1 && var0.charAt(var2) == ' '; ++var2) {
        }

        var0 = var0.substring(var2);

        int var3;
        for(var3 = var0.length() - 1; var3 > 0 && var0.charAt(var3) == ' '; --var3) {
        }

        var0 = var0.substring(0, var3 + 1);
        var1 = var0.length();
        StringBuffer var4 = new StringBuffer(var1);
        boolean var5 = false;

        for(int var6 = 0; var6 < var1; ++var6) {
            char var7 = var0.charAt(var6);
            if (var7 == ' ') {
                if (!var5) {
                    var4.append(' ');
                    var5 = true;
                }
            } else {
                var4.append(var7);
                var5 = false;
            }
        }

        return var4.toString();
    }

    public final nu.xom.Attribute.Type getType() {
        return this.type;
    }

    public void setType(nu.xom.Attribute.Type var1) {
        if (var1 == null) {
            throw new NullPointerException("Null attribute type");
        } else if (this.isXMLID() && !nu.xom.Attribute.Type.ID.equals(var1)) {
            throw new IllegalDataException("Can't change type of xml:id attribute to " + var1);
        } else {
            this._setType(var1);
        }
    }

    private boolean isXMLID() {
        return "xml".equals(this.prefix) && "id".equals(this.localName);
    }

    private void _setType(nu.xom.Attribute.Type var1) {
        this.type = var1;
    }

    public final String getValue() {
        return this.value;
    }

    public void setValue(String var1) {
        this._setValue(var1);
    }

    private void _setValue(String var1) {
        Verifier.checkPCDATA(var1);
        if (this.isXMLID()) {
            var1 = normalize(var1);
        }

        this.value = var1;
    }

    public final String getLocalName() {
        return this.localName;
    }

    public void setLocalName(String var1) {
        if ("id".equals(var1) && "http://www.w3.org/XML/1998/namespace".equals(this.URI)) {
            Verifier.checkNCName(this.value);
        }

        this._setLocalName(var1);
        if (this.isXMLID()) {
            this.setType(nu.xom.Attribute.Type.ID);
        }

    }

    private void _setLocalName(String var1) {
        Verifier.checkNCName(var1);
        if (var1.equals("xmlns")) {
            throw new IllegalNameException("The Attribute class is not used for namespace declaration attributes.");
        } else {
            this.localName = var1;
        }
    }

    public final String getQualifiedName() {
        return this.prefix.length() == 0 ? this.localName : this.prefix + ":" + this.localName;
    }

    public final String getNamespaceURI() {
        return this.URI;
    }

    public final String getNamespacePrefix() {
        return this.prefix;
    }

    public void setNamespace(String var1, String var2) {
        this._setNamespace(var1, var2);
        if (this.isXMLID()) {
            this.setType(nu.xom.Attribute.Type.ID);
        }

    }

    private void _setNamespace(String var1, String var2) {
        if (var2 == null) {
            var2 = "";
        }

        if (var1 == null) {
            var1 = "";
        }

        if (var1.equals("xmlns")) {
            throw new IllegalNameException("Attribute objects are not used to represent  namespace declarations");
        } else if (var1.equals("xml") && !var2.equals("http://www.w3.org/XML/1998/namespace")) {
            throw new NamespaceConflictException("Wrong namespace URI for xml prefix: " + var2);
        } else if (var2.equals("http://www.w3.org/XML/1998/namespace") && !var1.equals("xml")) {
            throw new NamespaceConflictException("Wrong prefix for the XML namespace: " + var1);
        } else if (var1.length() == 0) {
            if (var2.length() == 0) {
                this.prefix = "";
                this.URI = "";
            } else {
                throw new NamespaceConflictException("Unprefixed attribute " + this.localName + " cannot be in default namespace " + var2);
            }
        } else if (var2.length() == 0) {
            throw new NamespaceConflictException("Attribute prefixes must be declared.");
        } else {
            ParentNode var3 = this.getParent();
            if (var3 != null) {
                Element var4 = (Element)var3;
                String var5 = var4.getLocalNamespaceURI(var1);
                if (var5 != null && !var5.equals(var2)) {
                    throw new NamespaceConflictException("New prefix " + var1 + "conflicts with existing namespace declaration");
                }
            }

            Verifier.checkAbsoluteURIReference(var2);
            Verifier.checkNCName(var1);
            this.URI = var2;
            this.prefix = var1;
        }
    }

    public final Node getChild(int var1) {
        throw new IndexOutOfBoundsException("Attributes do not have children");
    }

    public final int getChildCount() {
        return 0;
    }

    public Node copy() {
        return new nu.xom.Attribute(this);
    }

    public final String toXML() {
        return this.getQualifiedName() + "=\"" + escapeText(this.value) + "\"";
    }

    public final String toString() {
        return "[" + this.getClass().getName() + ": " + this.getQualifiedName() + "=\"" + Text.escapeLineBreaksAndTruncate(this.getValue()) + "\"]";
    }

    private static String escapeText(String var0) {
        int var1 = var0.length();
        StringBuffer var2 = new StringBuffer(var1 + 12);

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case '\t':
                    var2.append("&#x09;");
                    break;
                case '\n':
                    var2.append("&#x0A;");
                case '\u000b':
                case '\f':
                case '\u000e':
                case '\u000f':
                case '\u0010':
                case '\u0011':
                case '\u0012':
                case '\u0013':
                case '\u0014':
                case '\u0015':
                case '\u0016':
                case '\u0017':
                case '\u0018':
                case '\u0019':
                case '\u001a':
                case '\u001b':
                case '\u001c':
                case '\u001d':
                case '\u001e':
                case '\u001f':
                    break;
                case '\r':
                    var2.append("&#x0D;");
                    break;
                case ' ':
                    var2.append(' ');
                    break;
                case '!':
                    var2.append('!');
                    break;
                case '"':
                    var2.append("&quot;");
                    break;
                case '#':
                    var2.append('#');
                    break;
                case '$':
                    var2.append('$');
                    break;
                case '%':
                    var2.append('%');
                    break;
                case '&':
                    var2.append("&amp;");
                    break;
                case '\'':
                    var2.append('\'');
                    break;
                case '(':
                    var2.append('(');
                    break;
                case ')':
                    var2.append(')');
                    break;
                case '*':
                    var2.append('*');
                    break;
                case '+':
                    var2.append('+');
                    break;
                case ',':
                    var2.append(',');
                    break;
                case '-':
                    var2.append('-');
                    break;
                case '.':
                    var2.append('.');
                    break;
                case '/':
                    var2.append('/');
                    break;
                case '0':
                    var2.append('0');
                    break;
                case '1':
                    var2.append('1');
                    break;
                case '2':
                    var2.append('2');
                    break;
                case '3':
                    var2.append('3');
                    break;
                case '4':
                    var2.append('4');
                    break;
                case '5':
                    var2.append('5');
                    break;
                case '6':
                    var2.append('6');
                    break;
                case '7':
                    var2.append('7');
                    break;
                case '8':
                    var2.append('8');
                    break;
                case '9':
                    var2.append('9');
                    break;
                case ':':
                    var2.append(':');
                    break;
                case ';':
                    var2.append(';');
                    break;
                case '<':
                    var2.append("&lt;");
                    break;
                case '=':
                    var2.append('=');
                    break;
                case '>':
                    var2.append("&gt;");
                    break;
                default:
                    var2.append(var4);
            }
        }

        return var2.toString();
    }

    boolean isAttribute() {
        return true;
    }

    public static final class Type {
        public static final nu.xom.Attribute.Type CDATA = new nu.xom.Attribute.Type(1);
        public static final nu.xom.Attribute.Type ID = new nu.xom.Attribute.Type(2);
        public static final nu.xom.Attribute.Type IDREF = new nu.xom.Attribute.Type(3);
        public static final nu.xom.Attribute.Type IDREFS = new nu.xom.Attribute.Type(4);
        public static final nu.xom.Attribute.Type NMTOKEN = new nu.xom.Attribute.Type(5);
        public static final nu.xom.Attribute.Type NMTOKENS = new nu.xom.Attribute.Type(6);
        public static final nu.xom.Attribute.Type NOTATION = new nu.xom.Attribute.Type(7);
        public static final nu.xom.Attribute.Type ENTITY = new nu.xom.Attribute.Type(8);
        public static final nu.xom.Attribute.Type ENTITIES = new nu.xom.Attribute.Type(9);
        public static final nu.xom.Attribute.Type ENUMERATION = new nu.xom.Attribute.Type(10);
        public static final nu.xom.Attribute.Type UNDECLARED = new nu.xom.Attribute.Type(0);
        private final int type;

        public String getName() {
            switch(this.type) {
                case 0:
                    return "UNDECLARED";
                case 1:
                    return "CDATA";
                case 2:
                    return "ID";
                case 3:
                    return "IDREF";
                case 4:
                    return "IDREFS";
                case 5:
                    return "NMTOKEN";
                case 6:
                    return "NMTOKENS";
                case 7:
                    return "NOTATION";
                case 8:
                    return "ENTITY";
                case 9:
                    return "ENTITIES";
                case 10:
                    return "ENUMERATION";
                default:
                    throw new RuntimeException("Bug in XOM: unexpected attribute type: " + this.type);
            }
        }

        private Type(int var1) {
            this.type = var1;
        }

        public int hashCode() {
            return this.type;
        }

        public boolean equals(Object var1) {
            if (var1 == this) {
                return true;
            } else if (var1 == null) {
                return false;
            } else if (this.hashCode() != var1.hashCode()) {
                return false;
            } else {
                return var1.getClass().getName().equals("nu.Attribute.Type");
            }
        }

        public String toString() {
            StringBuffer var1 = new StringBuffer("[Attribute.Type: ");
            var1.append(this.getName());
            var1.append(']');
            return var1.toString();
        }
    }
}

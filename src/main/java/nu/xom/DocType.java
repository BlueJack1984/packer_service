package nu.xom;


public class DocType extends Node {
    private String rootName;
    private String systemID;
    private String publicID;
    private String internalDTDSubset;

    public DocType(String var1, String var2, String var3) {
        this.internalDTDSubset = "";
        this._setRootElementName(var1);
        this._setSystemID(var3);
        this._setPublicID(var2);
    }

    public DocType(String var1, String var2) {
        this(var1, (String)null, var2);
    }

    public DocType(String var1) {
        this(var1, (String)null, (String)null);
    }

    public DocType(nu.xom.DocType var1) {
        this.internalDTDSubset = "";
        this.internalDTDSubset = var1.internalDTDSubset;
        this.publicID = var1.publicID;
        this.systemID = var1.systemID;
        this.rootName = var1.rootName;
    }

    private DocType() {
        this.internalDTDSubset = "";
    }

    static nu.xom.DocType build(String var0, String var1, String var2) {
        nu.xom.DocType var3 = new nu.xom.DocType();
        var3.publicID = var1;
        var3.systemID = var2;
        var3.rootName = var0;
        return var3;
    }

    public final String getRootElementName() {
        return this.rootName;
    }

    public void setRootElementName(String var1) {
        this._setRootElementName(var1);
    }

    private void _setRootElementName(String var1) {
        Verifier.checkXMLName(var1);
        this.rootName = var1;
    }

    public final String getInternalDTDSubset() {
        return this.internalDTDSubset;
    }

    public final void setInternalDTDSubset(String var1) {
        if (var1 != null && var1.length() > 0) {
            Verifier.checkInternalDTDSubset(var1);
            this.fastSetInternalDTDSubset(var1);
        } else {
            this.internalDTDSubset = "";
        }

    }

    final void fastSetInternalDTDSubset(String var1) {
        this.internalDTDSubset = var1;
    }

    public final String getPublicID() {
        return this.publicID;
    }

    public void setPublicID(String var1) {
        this._setPublicID(var1);
    }

    private void _setPublicID(String var1) {
        if (this.systemID == null && var1 != null) {
            throw new WellformednessException("Cannot have a public ID without a system ID");
        } else {
            if (var1 != null) {
                int var2 = var1.length();
                if (var2 != 0) {
                    if (Verifier.isXMLSpaceCharacter(var1.charAt(0))) {
                        throw new IllegalDataException("Initial white space in public IDs is not round trippable.");
                    }

                    if (Verifier.isXMLSpaceCharacter(var1.charAt(var2 - 1))) {
                        throw new IllegalDataException("Trailing white space in public IDs is not round trippable.");
                    }

                    for(int var3 = 0; var3 < var2; ++var3) {
                        char var4 = var1.charAt(var3);
                        if (!isXMLPublicIDCharacter(var4)) {
                            throw new IllegalDataException("The character 0x" + Integer.toHexString(var4) + " is not allowed in public IDs");
                        }

                        if (var4 == ' ' && var1.charAt(var3 - 1) == ' ') {
                            throw new IllegalDataException("Multiple consecutive spaces in public IDs are not round trippable.");
                        }
                    }
                }
            }

            this.publicID = var1;
        }
    }

    public final String getSystemID() {
        return this.systemID;
    }

    public void setSystemID(String var1) {
        this._setSystemID(var1);
    }

    private void _setSystemID(String var1) {
        if (var1 == null && this.publicID != null) {
            throw new WellformednessException("Cannot remove system ID without removing public ID first");
        } else {
            if (var1 != null) {
                Verifier.checkURIReference(var1);
                if (var1.indexOf(35) != -1) {
                    MalformedURIException var2 = new MalformedURIException("System literals cannot contain fragment identifiers");
                    var2.setData(var1);
                    throw var2;
                }
            }

            this.systemID = var1;
        }
    }

    public final String getValue() {
        return "";
    }

    public final Node getChild(int var1) {
        throw new IndexOutOfBoundsException("Document type declarations do not have children");
    }

    public final int getChildCount() {
        return 0;
    }

    public final String toString() {
        return "[" + this.getClass().getName() + ": " + this.rootName + "]";
    }

    public Node copy() {
        return new nu.xom.DocType(this);
    }

    public final String toXML() {
        StringBuffer var1 = new StringBuffer();
        var1.append("<!DOCTYPE ");
        var1.append(this.rootName);
        if (this.publicID != null) {
            var1.append(" PUBLIC \"");
            var1.append(this.publicID);
            var1.append("\" \"");
            var1.append(this.systemID);
            var1.append('"');
        } else if (this.systemID != null) {
            var1.append(" SYSTEM \"");
            var1.append(this.systemID);
            var1.append('"');
        }

        if (this.internalDTDSubset.length() != 0) {
            var1.append(" [\n");
            var1.append(this.internalDTDSubset);
            var1.append(']');
        }

        var1.append(">");
        return var1.toString();
    }

    boolean isDocType() {
        return true;
    }

    private static boolean isXMLPublicIDCharacter(char var0) {
        switch(var0) {
            case ' ':
                return true;
            case '!':
                return true;
            case '"':
                return false;
            case '#':
                return true;
            case '$':
                return true;
            case '%':
                return true;
            case '&':
                return false;
            case '\'':
                return true;
            case '(':
                return true;
            case ')':
                return true;
            case '*':
                return true;
            case '+':
                return true;
            case ',':
                return true;
            case '-':
                return true;
            case '.':
                return true;
            case '/':
                return true;
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            case ':':
                return true;
            case ';':
                return true;
            case '<':
                return false;
            case '=':
                return true;
            case '>':
                return false;
            case '?':
                return true;
            case '@':
                return true;
            case 'A':
                return true;
            case 'B':
                return true;
            case 'C':
                return true;
            case 'D':
                return true;
            case 'E':
                return true;
            case 'F':
                return true;
            case 'G':
                return true;
            case 'H':
                return true;
            case 'I':
                return true;
            case 'J':
                return true;
            case 'K':
                return true;
            case 'L':
                return true;
            case 'M':
                return true;
            case 'N':
                return true;
            case 'O':
                return true;
            case 'P':
                return true;
            case 'Q':
                return true;
            case 'R':
                return true;
            case 'S':
                return true;
            case 'T':
                return true;
            case 'U':
                return true;
            case 'V':
                return true;
            case 'W':
                return true;
            case 'X':
                return true;
            case 'Y':
                return true;
            case 'Z':
                return true;
            case '[':
                return false;
            case '\\':
                return false;
            case ']':
                return false;
            case '^':
                return false;
            case '_':
                return true;
            case '`':
                return false;
            case 'a':
                return true;
            case 'b':
                return true;
            case 'c':
                return true;
            case 'd':
                return true;
            case 'e':
                return true;
            case 'f':
                return true;
            case 'g':
                return true;
            case 'h':
                return true;
            case 'i':
                return true;
            case 'j':
                return true;
            case 'k':
                return true;
            case 'l':
                return true;
            case 'm':
                return true;
            case 'n':
                return true;
            case 'o':
                return true;
            case 'p':
                return true;
            case 'q':
                return true;
            case 'r':
                return true;
            case 's':
                return true;
            case 't':
                return true;
            case 'u':
                return true;
            case 'v':
                return true;
            case 'w':
                return true;
            case 'x':
                return true;
            case 'y':
                return true;
            case 'z':
                return true;
            default:
                return false;
        }
    }
}

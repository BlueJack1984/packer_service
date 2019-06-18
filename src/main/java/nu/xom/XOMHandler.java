package nu.xom;


import nu.xom.Attribute.Type;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.Locator;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

import java.util.ArrayList;

class XOMHandler implements ContentHandler, LexicalHandler, DeclHandler, DTDHandler {
    protected Document document;
    protected String documentBaseURI;
    protected ParentNode parent;
    protected ParentNode current;
    protected ArrayList parents;
    protected boolean inProlog;
    protected boolean inDTD;
    protected int position;
    protected Locator locator;
    protected DocType doctype;
    protected StringBuffer internalDTDSubset;
    protected NodeFactory factory;
    boolean usingCrimson = false;
    protected String textString = null;
    protected StringBuffer buffer = null;
    protected boolean inExternalSubset = false;
    protected boolean inCDATA = false;
    protected boolean finishedCDATA = false;

    XOMHandler(NodeFactory var1) {
        this.factory = var1;
    }

    public void setDocumentLocator(Locator var1) {
        this.locator = var1;
    }

    Document getDocument() {
        return this.document;
    }

    void freeMemory() {
        this.document = null;
        this.parent = null;
        this.current = null;
        this.parents = null;
        this.locator = null;
        this.doctype = null;
        this.internalDTDSubset = null;
    }

    public void startDocument() {
        this.inDTD = false;
        this.document = this.factory.startMakingDocument();
        this.parent = this.document;
        this.current = this.document;
        this.parents = new ArrayList();
        this.parents.add(this.document);
        this.inProlog = true;
        this.position = 0;
        this.textString = null;
        this.doctype = null;
        if (this.locator != null) {
            this.documentBaseURI = this.locator.getSystemId();
            this.document.setBaseURI(this.documentBaseURI);
        }

        this.buffer = null;
    }

    public void endDocument() {
        this.factory.finishMakingDocument(this.document);
        this.parents.remove(this.parents.size() - 1);
    }

    public void startElement(String var1, String var2, String var3, Attributes var4) {
        this.flushText();
        Element var5;
        if (this.parent != this.document) {
            var5 = this.factory.startMakingElement(var3, var1);
        } else {
            var5 = this.factory.makeRootElement(var3, var1);
            if (var5 == null) {
                throw new NullPointerException("Factory failed to create root element.");
            }

            this.document.setRootElement(var5);
            this.inProlog = false;
        }

        this.current = var5;
        this.parents.add(var5);
        if (var5 != null) {
            if (this.parent != this.document) {
                this.parent.appendChild(var5);
            }

            if (this.locator != null) {
                String var6 = this.locator.getSystemId();
                if (var6 != null && !var6.equals(this.documentBaseURI)) {
                    var5.setActualBaseURI(var6);
                }
            }

            String var7;
            String var8;
            String var9;
            int var14;
            for(var14 = 0; var14 < var4.getLength(); ++var14) {
                var7 = var4.getQName(var14);
                if (!var7.startsWith("xmlns:") && !var7.equals("xmlns")) {
                    var8 = var4.getURI(var14);
                    var9 = var4.getValue(var14);
                    Nodes var10 = this.factory.makeAttribute(var7, var8, var9, convertStringToType(var4.getType(var14)));
                    int var11 = 0;

                    for(int var12 = 0; var12 < var10.size(); ++var12) {
                        Node var13 = var10.get(var12);
                        if (var13.isAttribute()) {
                            this.factory.addAttribute(var5, (Attribute)var13);
                        } else {
                            this.factory.insertChild(var5, var13, var11++);
                        }
                    }
                }
            }

            for(var14 = 0; var14 < var4.getLength(); ++var14) {
                var7 = var4.getQName(var14);
                String var15;
                if (var7.startsWith("xmlns:")) {
                    var8 = var4.getValue(var14);
                    var9 = var7.substring(6);
                    var15 = var5.getNamespaceURI(var9);
                    if (!var8.equals(var15) && !var9.equals(var5.getNamespacePrefix())) {
                        var5.addNamespaceDeclaration(var9, var8);
                    }
                } else if (var7.equals("xmlns")) {
                    var8 = var4.getValue(var14);
                    var9 = "";
                    var15 = var5.getNamespaceURI(var9);
                    if (!var8.equals(var15) && !"".equals(var5.getNamespacePrefix())) {
                        var5.addNamespaceDeclaration(var9, var8);
                    }
                }
            }

            this.parent = var5;
        }

    }

    public void endElement(String var1, String var2, String var3) {
        this.current = (ParentNode)this.parents.remove(this.parents.size() - 1);
        this.flushText();
        if (this.current != null) {
            this.parent = this.current.getParent();
            Nodes var4 = this.factory.finishMakingElement((Element)this.current);
            if (var4.size() != 1 || var4.get(0) != this.current) {
                if (!this.parent.isDocument()) {
                    int var5 = this.parent.getChildCount();

                    try {
                        this.parent.removeChild(var5 - 1);
                    } catch (IndexOutOfBoundsException var10) {
                        throw new XMLException("Factory detached element in finishMakingElement()", var10);
                    }

                    for(int var6 = 0; var6 < var4.size(); ++var6) {
                        Node var7 = var4.get(var6);
                        if (var7.isAttribute()) {
                            ((Element)this.parent).addAttribute((Attribute)var7);
                        } else {
                            this.parent.appendChild(var7);
                        }
                    }
                } else {
                    Document var11 = (Document)this.parent;
                    Element var12 = var11.getRootElement();
                    boolean var13 = true;

                    for(int var8 = 0; var8 < var4.size(); ++var8) {
                        Node var9 = var4.get(var8);
                        if (var9.isElement()) {
                            if (var9 != var12) {
                                if (!var13) {
                                    throw new IllegalAddException("Factory returned multiple roots");
                                }

                                var11.setRootElement((Element)var9);
                            }

                            var13 = false;
                        } else if (var13) {
                            var11.insertChild(var9, var11.indexOf(var11.getRootElement()));
                        } else {
                            var11.appendChild(var9);
                        }
                    }

                    if (var13) {
                        throw new WellformednessException("Factory attempted to remove the root element");
                    }
                }
            }
        }

    }

    static Type convertStringToType(String var0) {
        if (var0.equals("CDATA")) {
            return Type.CDATA;
        } else if (var0.equals("ID")) {
            return Type.ID;
        } else if (var0.equals("IDREF")) {
            return Type.IDREF;
        } else if (var0.equals("IDREFS")) {
            return Type.IDREFS;
        } else if (var0.equals("NMTOKEN")) {
            return Type.NMTOKEN;
        } else if (var0.equals("NMTOKENS")) {
            return Type.NMTOKENS;
        } else if (var0.equals("ENTITY")) {
            return Type.ENTITY;
        } else if (var0.equals("ENTITIES")) {
            return Type.ENTITIES;
        } else if (var0.equals("NOTATION")) {
            return Type.NOTATION;
        } else if (var0.equals("ENUMERATION")) {
            return Type.ENUMERATION;
        } else {
            return var0.startsWith("(") ? Type.ENUMERATION : Type.UNDECLARED;
        }
    }

    public void characters(char[] var1, int var2, int var3) {
        if (var3 > 0) {
            if (this.textString == null) {
                this.textString = new String(var1, var2, var3);
            } else {
                if (this.buffer == null) {
                    this.buffer = new StringBuffer(this.textString);
                }

                this.buffer.append(var1, var2, var3);
            }

            if (this.finishedCDATA) {
                this.inCDATA = false;
            }

        }
    }

    private void flushText() {
        if (this.buffer != null) {
            this.textString = this.buffer.toString();
            this.buffer = null;
        }

        if (this.textString != null) {
            Nodes var1;
            if (!this.inCDATA) {
                var1 = this.factory.makeText(this.textString);
            } else {
                var1 = this.factory.makeCDATASection(this.textString);
            }

            for(int var2 = 0; var2 < var1.size(); ++var2) {
                Node var3 = var1.get(var2);
                if (var3.isAttribute()) {
                    ((Element)this.parent).addAttribute((Attribute)var3);
                } else {
                    this.parent.appendChild(var3);
                }
            }

            this.textString = null;
        }

        this.inCDATA = false;
        this.finishedCDATA = false;
    }

    public void ignorableWhitespace(char[] var1, int var2, int var3) {
        this.characters(var1, var2, var3);
    }

    public void processingInstruction(String var1, String var2) {
        if (!this.inDTD) {
            this.flushText();
        }

        if (!this.inDTD || this.inInternalSubset()) {
            Nodes var3 = this.factory.makeProcessingInstruction(var1, var2);

            for(int var4 = 0; var4 < var3.size(); ++var4) {
                Node var5 = var3.get(var4);
                if (!this.inDTD) {
                    if (this.inProlog) {
                        this.parent.insertChild(var5, this.position);
                        ++this.position;
                    } else if (var5.isAttribute()) {
                        ((Element)this.parent).addAttribute((Attribute)var5);
                    } else {
                        this.parent.appendChild(var5);
                    }
                } else {
                    if (!var5.isProcessingInstruction() && !var5.isComment()) {
                        throw new XMLException("Factory tried to put a " + var5.getClass().getName() + " in the internal DTD subset");
                    }

                    this.internalDTDSubset.append("  ");
                    this.internalDTDSubset.append(var5.toXML());
                    this.internalDTDSubset.append("\n");
                }
            }

        }
    }

    public void startPrefixMapping(String var1, String var2) {
    }

    public void endPrefixMapping(String var1) {
    }

    public void skippedEntity(String var1) {
        if (!var1.startsWith("%")) {
            this.flushText();
            throw new XMLException("Could not resolve entity " + var1);
        }
    }

    public void startDTD(String var1, String var2, String var3) {
        this.inDTD = true;
        Nodes var4 = this.factory.makeDocType(var1, var2, var3);

        for(int var5 = 0; var5 < var4.size(); ++var5) {
            Node var6 = var4.get(var5);
            this.document.insertChild(var6, this.position);
            ++this.position;
            if (var6.isDocType()) {
                DocType var7 = (DocType)var6;
                this.internalDTDSubset = new StringBuffer();
                this.doctype = var7;
            }
        }

    }

    public void endDTD() {
        this.inDTD = false;
        if (this.doctype != null) {
            this.doctype.setInternalDTDSubset(this.internalDTDSubset.toString());
        }

    }

    public void startEntity(String var1) {
        if (var1.equals("[dtd]")) {
            this.inExternalSubset = true;
        }

    }

    public void endEntity(String var1) {
        if (var1.equals("[dtd]")) {
            this.inExternalSubset = false;
        }

    }

    public void startCDATA() {
        if (this.textString == null) {
            this.inCDATA = true;
        }

        this.finishedCDATA = false;
    }

    public void endCDATA() {
        this.finishedCDATA = true;
    }

    public void comment(char[] var1, int var2, int var3) {
        if (!this.inDTD) {
            this.flushText();
        }

        if (!this.inDTD || this.inInternalSubset()) {
            Nodes var4 = this.factory.makeComment(new String(var1, var2, var3));

            for(int var5 = 0; var5 < var4.size(); ++var5) {
                Node var6 = var4.get(var5);
                if (!this.inDTD) {
                    if (this.inProlog) {
                        this.parent.insertChild(var6, this.position);
                        ++this.position;
                    } else if (var6 instanceof Attribute) {
                        ((Element)this.parent).addAttribute((Attribute)var6);
                    } else {
                        this.parent.appendChild(var6);
                    }
                } else {
                    if (!var6.isComment() && !var6.isProcessingInstruction()) {
                        throw new XMLException("Factory tried to put a " + var6.getClass().getName() + " in the internal DTD subset");
                    }

                    this.internalDTDSubset.append("  ");
                    this.internalDTDSubset.append(var6.toXML());
                    this.internalDTDSubset.append("\n");
                }
            }

        }
    }

    public void elementDecl(String var1, String var2) {
        if (this.inInternalSubset() && this.doctype != null) {
            this.internalDTDSubset.append("  <!ELEMENT ");
            this.internalDTDSubset.append(var1);
            this.internalDTDSubset.append(' ');
            this.internalDTDSubset.append(var2);
            if (var2.indexOf("#PCDATA") > 0 && var2.indexOf(124) > 0 && var2.endsWith(")")) {
                this.internalDTDSubset.append('*');
            }

            this.internalDTDSubset.append(">\n");
        }

    }

    protected boolean inInternalSubset() {
        if (!this.usingCrimson) {
            return !this.inExternalSubset;
        } else {
            String var1 = this.locator.getSystemId();
            if (var1 == this.documentBaseURI) {
                return true;
            } else if (var1 == null) {
                return false;
            } else {
                return var1.equals(this.documentBaseURI);
            }
        }
    }

    public void attributeDecl(String var1, String var2, String var3, String var4, String var5) {
        if (var3.startsWith("NOTATION ") && var3.indexOf(40) == -1 && !var3.endsWith(")")) {
            var3 = "NOTATION (" + var3.substring("NOTATION ".length()) + ")";
        }

        if (this.inInternalSubset() && this.doctype != null) {
            this.internalDTDSubset.append("  <!ATTLIST ");
            this.internalDTDSubset.append(var1);
            this.internalDTDSubset.append(' ');
            this.internalDTDSubset.append(var2);
            this.internalDTDSubset.append(' ');
            this.internalDTDSubset.append(var3);
            if (var4 != null) {
                this.internalDTDSubset.append(' ');
                this.internalDTDSubset.append(var4);
            }

            if (var5 != null) {
                this.internalDTDSubset.append(' ');
                this.internalDTDSubset.append('"');
                this.internalDTDSubset.append(escapeReservedCharactersInDefaultAttributeValues(var5));
                this.internalDTDSubset.append('"');
            }

            this.internalDTDSubset.append(">\n");
        }

    }

    public void internalEntityDecl(String var1, String var2) {
        if (this.inInternalSubset() && this.doctype != null) {
            this.internalDTDSubset.append("  <!ENTITY ");
            if (var1.startsWith("%")) {
                this.internalDTDSubset.append("% ");
                this.internalDTDSubset.append(var1.substring(1));
            } else {
                this.internalDTDSubset.append(var1);
            }

            this.internalDTDSubset.append(" \"");
            this.internalDTDSubset.append(escapeReservedCharactersInDeclarations(var2));
            this.internalDTDSubset.append("\">\n");
        }

    }

    public void externalEntityDecl(String var1, String var2, String var3) {
        if (this.inInternalSubset() && this.doctype != null) {
            this.internalDTDSubset.append("  <!ENTITY ");
            if (var1.startsWith("%")) {
                this.internalDTDSubset.append("% ");
                this.internalDTDSubset.append(var1.substring(1));
            } else {
                this.internalDTDSubset.append(var1);
            }

            if (this.locator != null && nu.xom.URIUtil.isAbsolute(var3)) {
                String var4 = this.locator.getSystemId();
                if (var4 != null) {
                    if (var4.startsWith("file:/") && !var4.startsWith("file:///")) {
                        var4 = "file://" + var4.substring(5);
                    }

                    if (var3.startsWith("file:/") && !var3.startsWith("file:///")) {
                        var3 = "file://" + var3.substring(5);
                    }

                    var3 = nu.xom.URIUtil.relativize(var4, var3);
                }
            }

            if (var2 != null) {
                this.internalDTDSubset.append(" PUBLIC \"");
                this.internalDTDSubset.append(var2);
                this.internalDTDSubset.append("\" \"");
                this.internalDTDSubset.append(var3);
            } else {
                this.internalDTDSubset.append(" SYSTEM \"");
                this.internalDTDSubset.append(var3);
            }

            this.internalDTDSubset.append("\">\n");
        }

    }

    public void notationDecl(String var1, String var2, String var3) {
        if (var3 != null) {
            var3 = escapeReservedCharactersInDeclarations(var3);
        }

        if (this.inInternalSubset() && this.doctype != null) {
            this.internalDTDSubset.append("  <!NOTATION ");
            this.internalDTDSubset.append(var1);
            if (var2 != null) {
                this.internalDTDSubset.append(" PUBLIC \"");
                this.internalDTDSubset.append(var2);
                this.internalDTDSubset.append('"');
                if (var3 != null) {
                    this.internalDTDSubset.append(" \"");
                    this.internalDTDSubset.append(var3);
                    this.internalDTDSubset.append('"');
                }
            } else {
                this.internalDTDSubset.append(" SYSTEM \"");
                this.internalDTDSubset.append(var3);
                this.internalDTDSubset.append('"');
            }

            this.internalDTDSubset.append(">\n");
        }

    }

    public void unparsedEntityDecl(String var1, String var2, String var3, String var4) {
        if (this.inInternalSubset() && this.doctype != null) {
            this.internalDTDSubset.append("  <!ENTITY ");
            if (var2 != null) {
                this.internalDTDSubset.append(var1);
                this.internalDTDSubset.append(" PUBLIC \"");
                this.internalDTDSubset.append(var2);
                this.internalDTDSubset.append("\" \"");
                this.internalDTDSubset.append(var3);
                this.internalDTDSubset.append("\" NDATA ");
                this.internalDTDSubset.append(var4);
            } else {
                this.internalDTDSubset.append(var1);
                this.internalDTDSubset.append(" SYSTEM \"");
                this.internalDTDSubset.append(var3);
                this.internalDTDSubset.append("\" NDATA ");
                this.internalDTDSubset.append(var4);
            }

            this.internalDTDSubset.append(">\n");
        }

    }

    private static String escapeReservedCharactersInDeclarations(String var0) {
        int var1 = var0.length();
        StringBuffer var2 = new StringBuffer(var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case '\r':
                    var2.append("&#x0D;");
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
                case ' ':
                    var2.append(' ');
                    break;
                case '!':
                    var2.append('!');
                    break;
                case '"':
                    var2.append("&#x22;");
                    break;
                case '#':
                    var2.append('#');
                    break;
                case '$':
                    var2.append('$');
                    break;
                case '%':
                    var2.append("&#x25;");
                    break;
                case '&':
                    var2.append("&#x26;");
                    break;
                default:
                    var2.append(var4);
            }
        }

        return var2.toString();
    }

    private static String escapeReservedCharactersInDefaultAttributeValues(String var0) {
        int var1 = var0.length();
        StringBuffer var2 = new StringBuffer(var1);

        for(int var3 = 0; var3 < var1; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case '\r':
                    var2.append("&#x0D;");
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
                    var2.append("&#x25;");
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
                default:
                    var2.append(var4);
            }
        }

        return var2.toString();
    }
}

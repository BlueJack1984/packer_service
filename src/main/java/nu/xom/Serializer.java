package nu.xom;


import org.xml.sax.helpers.NamespaceSupport;

import java.io.*;
import java.util.Locale;

public class Serializer {
    private TextWriter escaper;
    private boolean preserveBaseURI = false;
    private NamespaceSupport namespaces = new NamespaceSupport();

    public Serializer(OutputStream var1) {
        try {
            this.setOutputStream(var1, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException("The VM is broken. It does not understand UTF-8.");
        }
    }

    public Serializer(OutputStream var1, String var2) throws UnsupportedEncodingException {
        if (var2 == null) {
            throw new NullPointerException("Null encoding");
        } else {
            this.setOutputStream(var1, var2);
        }
    }

    public void setOutputStream(OutputStream var1) throws IOException {
        this.flush();
        int var2 = this.getMaxLength();
        int var3 = this.getIndent();
        String var4 = this.getLineSeparator();
        boolean var5 = this.getUnicodeNormalizationFormC();
        String var6 = this.escaper.getEncoding();
        boolean var7 = this.escaper.lineSeparatorSet;
        this.setOutputStream(var1, var6);
        this.setIndent(var3);
        this.setMaxLength(var2);
        this.setUnicodeNormalizationFormC(var5);
        if (var7) {
            this.setLineSeparator(var4);
        }

    }

    private void setOutputStream(OutputStream var1, String var2) throws UnsupportedEncodingException {
        if (var1 == null) {
            throw new NullPointerException("Null OutputStream");
        } else {
            String var3 = var2.toUpperCase(Locale.ENGLISH);
            Object var4;
            if (var3.equals("UTF-8")) {
                var4 = new OutputStreamWriter(var1, "UTF-8");
            } else if (!var3.equals("UTF-16") && !var3.equals("ISO-10646-UCS-2")) {
                if (!var3.equals("IBM037") && !var3.equals("CP037") && !var3.equals("EBCDIC-CP-US") && !var3.equals("EBCDIC-CP-CA") && !var3.equals("EBCDIC-CP-WA") && !var3.equals("EBCDIC-CP-NL") && !var3.equals("CSIBM037")) {
                    if (!var3.equals("ISO-8859-11") && !var3.equals("TIS-620")) {
                        var4 = new OutputStreamWriter(var1, var2);
                    } else {
                        var4 = new OutputStreamWriter(var1, "TIS620");
                    }
                } else {
                    var4 = new nu.xom.EBCDICWriter(var1);
                }
            } else {
                var4 = new OutputStreamWriter(var1, "UnicodeBig");
            }

            nu.xom.UnsynchronizedBufferedWriter var5 = new nu.xom.UnsynchronizedBufferedWriter((Writer)var4);
            this.escaper = TextWriterFactory.getTextWriter(var5, var2);
        }
    }

    public void write(Document var1) throws IOException {
        this.escaper.reset();
        this.namespaces.reset();
        this.namespaces.declarePrefix("", "");
        this.writeXMLDeclaration();
        int var2 = var1.getChildCount();

        for(int var3 = 0; var3 < var2; ++var3) {
            this.writeChild(var1.getChild(var3));
            this.escaper.breakLine();
        }

        this.escaper.flush();
    }

    protected void writeXMLDeclaration() throws IOException {
        this.escaper.writeUncheckedMarkup("<?xml version=\"1.0\" encoding=\"");
        this.escaper.writeUncheckedMarkup(this.escaper.getEncoding());
        this.escaper.writeUncheckedMarkup("\"?>");
        this.escaper.breakLine();
    }

    protected void write(Element var1) throws IOException {
        boolean var2 = false;
        int var3 = var1.getChildCount();
        int var4 = 0;

        while(var4 < var3) {
            Node var5 = var1.getChild(var4);
            if (var5.isText()) {
                Text var6 = (Text)var5;
                if (var6.isEmpty()) {
                    ++var4;
                    continue;
                }
            }

            var2 = true;
            break;
        }

        if (var2) {
            boolean var9 = this.escaper.isPreserveSpace();
            this.writeStartTag(var1);

            for(int var10 = 0; var10 < var3; ++var10) {
                Node var11 = var1.getChild(var10);
                if (this.escaper.getNFC() && var11.isText()) {
                    Text var7;
                    for(var7 = (Text)var11; var10 < var3 - 1; ++var10) {
                        Node var8 = var1.getChild(var10 + 1);
                        if (!var8.isText()) {
                            break;
                        }

                        var7 = new Text(var7.getValue() + var8.getValue());
                    }

                    this.writeChild(var7);
                } else {
                    this.writeChild(var11);
                }
            }

            this.writeEndTag(var1);
            this.escaper.setPreserveSpace(var9);
        } else {
            this.writeEmptyElementTag(var1);
        }

    }

    private boolean hasNonTextChildren(Element var1) {
        int var2 = var1.getChildCount();

        for(int var3 = 0; var3 < var2; ++var3) {
            if (!var1.getChild(var3).isText()) {
                return true;
            }
        }

        return false;
    }

    protected void writeEndTag(Element var1) throws IOException {
        this.escaper.decrementIndent();
        if (this.escaper.getIndent() > 0 && !this.escaper.isPreserveSpace() && this.hasNonTextChildren(var1)) {
            this.escaper.breakLine();
        }

        this.escaper.write('<');
        this.escaper.write('/');
        this.escaper.writeName(var1.getQualifiedName());
        this.escaper.write('>');
        this.namespaces.popContext();
    }

    protected void writeStartTag(Element var1) throws IOException {
        this.writeTagBeginning(var1);
        this.escaper.write('>');
        this.escaper.incrementIndent();
        String var2 = var1.getAttributeValue("space", "http://www.w3.org/XML/1998/namespace");
        if (var2 != null) {
            if ("preserve".equals(var2)) {
                this.escaper.setPreserveSpace(true);
            } else if ("default".equals(var2)) {
                this.escaper.setPreserveSpace(false);
            }
        }

    }

    protected void writeEmptyElementTag(Element var1) throws IOException {
        this.writeTagBeginning(var1);
        this.escaper.write('/');
        this.escaper.write('>');
        this.namespaces.popContext();
    }

    private void writeTagBeginning(Element var1) throws IOException {
        this.namespaces.pushContext();
        if (this.escaper.isIndenting() && !this.escaper.isPreserveSpace() && !this.escaper.justBroke()) {
            this.escaper.breakLine();
        }

        this.escaper.write('<');
        this.escaper.writeName(var1.getQualifiedName());
        this.writeAttributes(var1);
        this.writeNamespaceDeclarations(var1);
    }

    protected void writeAttributes(Element var1) throws IOException {
        Attribute var4;
        if (this.preserveBaseURI) {
            ParentNode var2 = var1.getParent();
            if (var1.getAttribute("base", "http://www.w3.org/XML/1998/namespace") == null) {
                String var3 = var1.getBaseURI();
                if (var2 == null || var2.isDocument() || !var1.getBaseURI().equals(var2.getBaseURI())) {
                    this.escaper.write(' ');
                    var4 = new Attribute("xml:base", "http://www.w3.org/XML/1998/namespace", var3);
                    this.write(var4);
                }
            }
        }

        int var5 = var1.getAttributeCount();

        for(int var6 = 0; var6 < var5; ++var6) {
            var4 = var1.getAttribute(var6);
            this.escaper.write(' ');
            this.write(var4);
        }

    }

    protected void writeNamespaceDeclarations(Element var1) throws IOException {
        String var2 = var1.getNamespacePrefix();
        if (!"xml".equals(var2)) {
            this.writeNamespaceDeclarationIfNecessary(var2, var1.getNamespaceURI());
        }

        int var3 = var1.getAttributeCount();

        for(int var4 = 0; var4 < var3; ++var4) {
            Attribute var5 = var1.getAttribute(var4);
            String var6 = var5.getNamespacePrefix();
            if (var6.length() != 0 && !"xml".equals(var6)) {
                this.writeNamespaceDeclarationIfNecessary(var6, var5.getNamespaceURI());
            }
        }

        Namespaces var9 = var1.namespaces;
        if (var9 != null) {
            int var10 = var9.size();

            for(int var11 = 0; var11 < var10; ++var11) {
                String var7 = var9.getPrefix(var11);
                String var8 = var9.getURI(var7);
                this.writeNamespaceDeclarationIfNecessary(var7, var8);
            }

        }
    }

    private void writeNamespaceDeclarationIfNecessary(String var1, String var2) throws IOException {
        String var3 = this.namespaces.getURI(var1);
        if (var3 != null || !"".equals(var2)) {
            if (!var2.equals(var3)) {
                this.escaper.write(' ');
                this.writeNamespaceDeclaration(var1, var2);
            }
        }
    }

    protected void writeNamespaceDeclaration(String var1, String var2) throws IOException {
        this.namespaces.declarePrefix(var1, var2);
        if ("".equals(var1)) {
            this.escaper.writeUncheckedMarkup("xmlns");
        } else {
            this.escaper.writeUncheckedMarkup("xmlns:");
            this.escaper.writeName(var1);
        }

        this.escaper.write('=');
        this.escaper.write('"');
        this.escaper.writePCDATA(var2);
        this.escaper.write('"');
    }

    protected void write(Attribute var1) throws IOException {
        this.escaper.writeName(var1.getQualifiedName());
        this.escaper.write('=');
        this.escaper.write('"');
        this.escaper.writeAttributeValue(var1.getValue());
        this.escaper.write('"');
    }

    protected void write(Comment var1) throws IOException {
        if (this.escaper.isIndenting()) {
            this.escaper.breakLine();
        }

        this.escaper.writeUncheckedMarkup("<!--");
        this.escaper.writeMarkup(var1.getValue());
        this.escaper.writeUncheckedMarkup("-->");
    }

    protected void write(ProcessingInstruction var1) throws IOException {
        if (this.escaper.isIndenting()) {
            this.escaper.breakLine();
        }

        this.escaper.writeUncheckedMarkup("<?");
        this.escaper.writeName(var1.getTarget());
        String var2 = var1.getValue();
        if (!"".equals(var2)) {
            this.escaper.write(' ');
            this.escaper.writeMarkup(var2);
        }

        this.escaper.writeUncheckedMarkup("?>");
    }

    protected void write(Text var1) throws IOException {
        String var2 = var1.getValue();
        if (var1.isCDATASection() && var2.indexOf("]]>") == -1) {
            if (!(this.escaper instanceof nu.xom.UnicodeWriter)) {
                int var3 = var2.length();

                for(int var4 = 0; var4 < var3; ++var4) {
                    if (this.escaper.needsEscaping(var2.charAt(var4))) {
                        this.escaper.writePCDATA(var2);
                        return;
                    }
                }
            }

            this.escaper.writeUncheckedMarkup("<![CDATA[");
            this.escaper.writeMarkup(var2);
            this.escaper.writeUncheckedMarkup("]]>");
        } else {
            if (this.isBoundaryWhitespace(var1, var2)) {
                return;
            }

            this.escaper.writePCDATA(var2);
        }

    }

    private boolean isBoundaryWhitespace(Text var1, String var2) {
        if (this.getIndent() <= 0) {
            return false;
        } else {
            ParentNode var3 = var1.getParent();
            if (var3 == null) {
                return "".equals(var2.trim());
            } else {
                int var4 = var3.getChildCount();
                if (var4 == 1) {
                    return false;
                } else if (!"".equals(var2.trim())) {
                    return false;
                } else {
                    int var5 = var3.indexOf(var1);
                    Node var6 = null;
                    Node var7 = null;
                    if (var5 != 0) {
                        var6 = var3.getChild(var5 - 1);
                    }

                    if (var5 != var4 - 1) {
                        var7 = var3.getChild(var5 + 1);
                    }

                    return (var6 == null || !var6.isText()) && (var7 == null || !var7.isText());
                }
            }
        }
    }

    protected void write(DocType var1) throws IOException {
        this.escaper.writeUncheckedMarkup("<!DOCTYPE ");
        this.escaper.writeName(var1.getRootElementName());
        if (var1.getPublicID() != null) {
            this.escaper.writeMarkup(" PUBLIC \"" + var1.getPublicID() + "\" \"" + var1.getSystemID() + "\"");
        } else if (var1.getSystemID() != null) {
            this.escaper.writeMarkup(" SYSTEM \"" + var1.getSystemID() + "\"");
        }

        String var2 = var1.getInternalDTDSubset();
        if (!var2.equals("")) {
            this.escaper.writeUncheckedMarkup(" [");
            this.escaper.breakLine();
            this.escaper.setInDocType(true);
            this.escaper.writeMarkup(var2);
            this.escaper.setInDocType(false);
            this.escaper.write(']');
        }

        this.escaper.write('>');
    }

    protected void writeChild(Node var1) throws IOException {
        if (var1.isElement()) {
            this.write((Element)var1);
        } else if (var1.isText()) {
            this.write((Text)var1);
        } else if (var1.isComment()) {
            this.write((Comment)var1);
        } else if (var1.isProcessingInstruction()) {
            this.write((ProcessingInstruction)var1);
        } else {
            if (!var1.isDocType()) {
                throw new XMLException("Cannot write a " + var1.getClass().getName() + " from the writeChild() method");
            }

            this.write((DocType)var1);
        }

    }

    protected final void writeEscaped(String var1) throws IOException {
        this.escaper.writePCDATA(var1);
    }

    protected final void writeAttributeValue(String var1) throws IOException {
        this.escaper.writeAttributeValue(var1);
    }

    protected final void writeRaw(String var1) throws IOException {
        this.escaper.writeMarkup(var1);
    }

    protected final void breakLine() throws IOException {
        this.escaper.breakLine();
    }

    public void flush() throws IOException {
        this.escaper.flush();
    }

    public int getIndent() {
        return this.escaper.getIndent();
    }

    public void setIndent(int var1) {
        if (var1 < 0) {
            throw new IllegalArgumentException("Indent cannot be negative");
        } else {
            this.escaper.setIndent(var1);
        }
    }

    public String getLineSeparator() {
        return this.escaper.getLineSeparator();
    }

    public void setLineSeparator(String var1) {
        this.escaper.setLineSeparator(var1);
    }

    public int getMaxLength() {
        return this.escaper.getMaxLength();
    }

    public void setMaxLength(int var1) {
        this.escaper.setMaxLength(var1);
    }

    public boolean getPreserveBaseURI() {
        return this.preserveBaseURI;
    }

    public void setPreserveBaseURI(boolean var1) {
        this.preserveBaseURI = var1;
    }

    public String getEncoding() {
        return this.escaper.getEncoding();
    }

    public void setUnicodeNormalizationFormC(boolean var1) {
        this.escaper.setNFC(var1);
    }

    public boolean getUnicodeNormalizationFormC() {
        return this.escaper.getNFC();
    }

    protected final int getColumnNumber() {
        return this.escaper.getColumnNumber();
    }
}

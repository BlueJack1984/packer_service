package nu.xom;


import org.apache.xerces.impl.Version;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Builder {
    private XMLReader parser;
    private NodeFactory factory;
    private static double xercesVersion = 2.6D;
    private static String[] parsers;
    private static String fileURLPrefix;

    public Builder() {
        this(false);
    }

    public Builder(boolean var1) {
        this(findParser(var1), var1, (NodeFactory)null);
    }

    public Builder(boolean var1, NodeFactory var2) {
        this(findParser(var1), var1, var2);
    }

    static XMLReader findParser(boolean var0) {
        try {
            nu.xom.XML1_0Parser var14 = new nu.xom.XML1_0Parser();
            setupParser(var14, var0);
            return var14;
        } catch (SAXException var12) {
        } catch (NoClassDefFoundError var13) {
        }

        XMLReader var1;
        try {
            var1 = (XMLReader)Class.forName("nu.xom.JDK15XML1_0Parser").newInstance();
            setupParser(var1, var0);
            return var1;
        } catch (SAXException var7) {
        } catch (InstantiationException var8) {
        } catch (ClassNotFoundException var9) {
        } catch (IllegalAccessException var10) {
        } catch (NoClassDefFoundError var11) {
        }

        for(int var2 = 2; var2 < parsers.length; ++var2) {
            try {
                var1 = XMLReaderFactory.createXMLReader(parsers[var2]);
                setupParser(var1, var0);
                return var1;
            } catch (SAXException var5) {
            } catch (NoClassDefFoundError var6) {
            }
        }

        try {
            var1 = XMLReaderFactory.createXMLReader();
            setupParser(var1, var0);
            return var1;
        } catch (SAXException var4) {
            throw new XMLException("Could not find a suitable SAX2 parser", var4);
        }
    }

    private static void setupParser(XMLReader var0, boolean var1) throws SAXNotRecognizedException, SAXNotSupportedException {
        var0.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
        var0.setFeature("http://xml.org/sax/features/namespaces", true);

        XMLReader var2;
        XMLReader var3;
        for(var2 = var0; var2 instanceof XMLFilter; var2 = var3) {
            var3 = ((XMLFilter)var2).getParent();
            if (var3 == null) {
                break;
            }
        }

        String var8 = var2.getClass().getName();
        if (!var1) {
            if (var8.equals("org.apache.crimson.parser.XMLReaderImpl")) {
                var0.setErrorHandler(new nu.xom.Builder.NamespaceWellformednessRequired());
            } else {
                var0.setFeature("http://xml.org/sax/features/external-general-entities", true);
                var0.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
            }
        } else {
            var0.setFeature("http://xml.org/sax/features/validation", true);
            var0.setErrorHandler(new nu.xom.Builder.ValidityRequired());
        }

        try {
            var0.setFeature("http://xml.org/sax/features/string-interning", true);
        } catch (SAXException var7) {
        }

        if (var8.equals("nu.XML1_0Parser") || var8.equals("nu.xom.JDK15XML1_0Parser") || var8.equals("org.apache.xerces.parsers.SAXParser") || var8.equals("com.sun.org.apache.xerces.internal.parsers.SAXParser") || var8.equals("org.apache.xerces.jaxp.SAXParserImpl$JAXPSAXParser") || var8.equals("com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser")) {
            try {
                var0.setFeature("http://apache.org/xml/features/allow-java-encodings", true);
            } catch (SAXException var6) {
            }

            try {
                var0.setFeature("http://apache.org/xml/features/standard-uri-conformant", true);
            } catch (SAXException var5) {
            }
        }

    }

    public Builder(XMLReader var1) {
        this(var1, false);
    }

    public Builder(NodeFactory var1) {
        this(findParser(false), false, var1);
    }

    public Builder(XMLReader var1, boolean var2) {
        this(var1, var2, (NodeFactory)null);
    }

    public Builder(XMLReader var1, boolean var2, NodeFactory var3) {
        try {
            setupParser(var1, var2);
        } catch (SAXException var5) {
            if (var2) {
                throw new XMLException(var1.getClass().getName() + " does not support validation.", var5);
            }

            throw new XMLException(var1.getClass().getName() + " does not support the entity resolution" + " features XOM requires.", var5);
        }

        this.parser = var1;
        this.factory = var3;
        this.setHandlers();
    }

    private static boolean knownGoodParser(XMLReader var0) {
        String var1 = var0.getClass().getName();
        if (var1.equals("org.apache.xml.resolver.tools.ResolvingXMLFilter")) {
            XMLFilter var2 = (XMLFilter)var0;
            var1 = var2.getParent().getClass().getName();
        }

        if (var1.equals("gnu.xml.aelfred2.XmlReader")) {
            return false;
        } else if (var1.equals("net.sf.saxon.aelfred.SAXDriver")) {
            return false;
        } else if (var1.equals("com.icl.saxon.aelfred.SAXDriver")) {
            return false;
        } else if (var1.equals("org.apache.xerces.parsers.SAXParser") && xercesVersion >= 2.4D) {
            return false;
        } else {
            for(int var3 = 0; var3 < parsers.length; ++var3) {
                if (var1.equals(parsers[var3])) {
                    return true;
                }
            }

            return false;
        }
    }

    private void setHandlers() {
        Object var2;
        if ((this.factory == null || this.factory.getClass().getName().equals("nu.NodeFactory")) && knownGoodParser(this.parser)) {
            NodeFactory var1 = this.factory;
            if (var1 == null) {
                var1 = new NodeFactory();
            }

            var2 = new nu.xom.NonVerifyingHandler(var1);
        } else {
            if (this.factory == null) {
                this.factory = new NodeFactory();
            }

            var2 = new nu.xom.XOMHandler(this.factory);
        }

        this.parser.setContentHandler((ContentHandler)var2);
        this.parser.setDTDHandler((DTDHandler)var2);

        try {
            this.parser.setProperty("http://xml.org/sax/properties/lexical-handler", var2);
        } catch (SAXException var4) {
        }

        try {
            this.parser.setProperty("http://xml.org/sax/properties/declaration-handler", var2);
            if (this.parser.getClass().getName().equals("org.apache.crimson.parser.XMLReaderImpl")) {
                ((nu.xom.XOMHandler)var2).usingCrimson = true;
            }
        } catch (SAXException var3) {
        }

    }

    public Document build(String var1) throws ParsingException, ValidityException, IOException {
        var1 = this.canonicalizeURL(var1);
        InputSource var2 = new InputSource(var1);
        return this.build(var2);
    }

    public Document build(InputStream var1) throws ParsingException, ValidityException, IOException {
        if (var1 == null) {
            throw new NullPointerException("Null InputStream");
        } else {
            InputSource var2 = new InputSource(var1);
            return this.build(var2);
        }
    }

    public Document build(InputStream var1, String var2) throws ParsingException, ValidityException, IOException {
        InputSource var3 = new InputSource(var1);
        if (var2 != null) {
            var2 = this.canonicalizeURL(var2);
            var3.setSystemId(var2);
        }

        return this.build(var3);
    }

    public Document build(File var1) throws ParsingException, ValidityException, IOException {
        FileInputStream var2 = new FileInputStream(var1);
        String var3 = var1.getAbsolutePath();
        StringBuffer var4 = new StringBuffer(fileURLPrefix);
        int var5 = var3.length();
        char var6 = File.separatorChar;

        for(int var7 = 0; var7 < var5; ++var7) {
            char var8 = var3.charAt(var7);
            if (var8 == var6) {
                var4.append('/');
            } else {
                switch(var8) {
                    case ' ':
                        var4.append("%20");
                        break;
                    case '!':
                        var4.append(var8);
                        break;
                    case '"':
                        var4.append("%22");
                        break;
                    case '#':
                        var4.append("%23");
                        break;
                    case '$':
                        var4.append(var8);
                        break;
                    case '%':
                        var4.append("%25");
                        break;
                    case '&':
                        var4.append('&');
                        break;
                    case '\'':
                        var4.append(var8);
                        break;
                    case '(':
                        var4.append(var8);
                        break;
                    case ')':
                        var4.append(var8);
                        break;
                    case '*':
                        var4.append(var8);
                        break;
                    case '+':
                        var4.append("%2B");
                        break;
                    case ',':
                        var4.append(var8);
                        break;
                    case '-':
                        var4.append(var8);
                        break;
                    case '.':
                        var4.append(var8);
                        break;
                    case '/':
                        var4.append("%2F");
                        break;
                    case '0':
                        var4.append(var8);
                        break;
                    case '1':
                        var4.append(var8);
                        break;
                    case '2':
                        var4.append(var8);
                        break;
                    case '3':
                        var4.append(var8);
                        break;
                    case '4':
                        var4.append(var8);
                        break;
                    case '5':
                        var4.append(var8);
                        break;
                    case '6':
                        var4.append(var8);
                        break;
                    case '7':
                        var4.append(var8);
                        break;
                    case '8':
                        var4.append(var8);
                        break;
                    case '9':
                        var4.append(var8);
                        break;
                    case ':':
                        var4.append(var8);
                        break;
                    case ';':
                        var4.append(var8);
                        break;
                    case '<':
                        var4.append("%3C");
                        break;
                    case '=':
                        var4.append(var8);
                        break;
                    case '>':
                        var4.append("%3E");
                        break;
                    case '?':
                        var4.append("%3F");
                        break;
                    case '@':
                        var4.append("%40");
                        break;
                    case 'A':
                        var4.append(var8);
                        break;
                    case 'B':
                        var4.append(var8);
                        break;
                    case 'C':
                        var4.append(var8);
                        break;
                    case 'D':
                        var4.append(var8);
                        break;
                    case 'E':
                        var4.append(var8);
                        break;
                    case 'F':
                        var4.append(var8);
                        break;
                    case 'G':
                        var4.append(var8);
                        break;
                    case 'H':
                        var4.append(var8);
                        break;
                    case 'I':
                        var4.append(var8);
                        break;
                    case 'J':
                        var4.append(var8);
                        break;
                    case 'K':
                        var4.append(var8);
                        break;
                    case 'L':
                        var4.append(var8);
                        break;
                    case 'M':
                        var4.append(var8);
                        break;
                    case 'N':
                        var4.append(var8);
                        break;
                    case 'O':
                        var4.append(var8);
                        break;
                    case 'P':
                        var4.append(var8);
                        break;
                    case 'Q':
                        var4.append(var8);
                        break;
                    case 'R':
                        var4.append(var8);
                        break;
                    case 'S':
                        var4.append(var8);
                        break;
                    case 'T':
                        var4.append(var8);
                        break;
                    case 'U':
                        var4.append(var8);
                        break;
                    case 'V':
                        var4.append(var8);
                        break;
                    case 'W':
                        var4.append(var8);
                        break;
                    case 'X':
                        var4.append(var8);
                        break;
                    case 'Y':
                        var4.append(var8);
                        break;
                    case 'Z':
                        var4.append(var8);
                        break;
                    case '[':
                        var4.append("%5B");
                        break;
                    case '\\':
                        var4.append("%5C");
                        break;
                    case ']':
                        var4.append("%5D");
                        break;
                    case '^':
                        var4.append("%5E");
                        break;
                    case '_':
                        var4.append(var8);
                        break;
                    case '`':
                        var4.append("%60");
                        break;
                    case 'a':
                        var4.append(var8);
                        break;
                    case 'b':
                        var4.append(var8);
                        break;
                    case 'c':
                        var4.append(var8);
                        break;
                    case 'd':
                        var4.append(var8);
                        break;
                    case 'e':
                        var4.append(var8);
                        break;
                    case 'f':
                        var4.append(var8);
                        break;
                    case 'g':
                        var4.append(var8);
                        break;
                    case 'h':
                        var4.append(var8);
                        break;
                    case 'i':
                        var4.append(var8);
                        break;
                    case 'j':
                        var4.append(var8);
                        break;
                    case 'k':
                        var4.append(var8);
                        break;
                    case 'l':
                        var4.append(var8);
                        break;
                    case 'm':
                        var4.append(var8);
                        break;
                    case 'n':
                        var4.append(var8);
                        break;
                    case 'o':
                        var4.append(var8);
                        break;
                    case 'p':
                        var4.append(var8);
                        break;
                    case 'q':
                        var4.append(var8);
                        break;
                    case 'r':
                        var4.append(var8);
                        break;
                    case 's':
                        var4.append(var8);
                        break;
                    case 't':
                        var4.append(var8);
                        break;
                    case 'u':
                        var4.append(var8);
                        break;
                    case 'v':
                        var4.append(var8);
                        break;
                    case 'w':
                        var4.append(var8);
                        break;
                    case 'x':
                        var4.append(var8);
                        break;
                    case 'y':
                        var4.append(var8);
                        break;
                    case 'z':
                        var4.append(var8);
                        break;
                    case '{':
                        var4.append("%7B");
                        break;
                    case '|':
                        var4.append("%7C");
                        break;
                    case '}':
                        var4.append("%7D");
                        break;
                    case '~':
                        var4.append(var8);
                        break;
                    default:
                        if (var8 >= '\ud800' && var8 <= '\udfff') {
                            if (var8 <= '\udbff') {
                                ++var7;

                                try {
                                    char var9 = var3.charAt(var7);
                                    String var10 = String.valueOf(var8) + var9;
                                    byte[] var11 = var10.getBytes("UTF8");

                                    for(int var12 = 0; var12 < 4; ++var12) {
                                        var4.append('%');
                                        String var13 = Integer.toHexString(var11[var12]).toUpperCase();
                                        var4.append(var13.substring(var13.length() - 2));
                                    }
                                } catch (IndexOutOfBoundsException var18) {
                                    var4 = new StringBuffer(0);
                                }
                            } else {
                                var4 = new StringBuffer(0);
                            }
                        } else {
                            var4.append(nu.xom.URIUtil.percentEscape(var8));
                        }
                }
            }
        }

        String var19 = var4.toString();

        Document var21;
        try {
            Document var20 = this.build((InputStream)var2, var19);
            var21 = var20;
        } finally {
            var2.close();
        }

        return var21;
    }

    public Document build(Reader var1) throws ParsingException, ValidityException, IOException {
        if (var1 == null) {
            throw new NullPointerException("Attempted to build from null reader");
        } else {
            InputSource var2 = new InputSource(var1);
            return this.build(var2);
        }
    }

    public Document build(Reader var1, String var2) throws ParsingException, ValidityException, IOException {
        InputSource var3 = new InputSource(var1);
        if (var2 != null) {
            var2 = this.canonicalizeURL(var2);
            var3.setSystemId(var2);
        }

        return this.build(var3);
    }

    public Document build(String var1, String var2) throws ParsingException, ValidityException, IOException {
        StringReader var3 = new StringReader(var1);
        return this.build((Reader)var3, var2);
    }

    private String canonicalizeURL(String var1) {
        try {
            URL var2 = new URL(var1);
            String var3 = var2.getPath();
            String var4 = var2.getProtocol();
            String var5 = var2.getHost();
            String var6 = var2.getQuery();
            int var7 = var2.getPort();
            if (var3 == null || var3.length() == 0) {
                var3 = "/";
            }

            var3 = nu.xom.URIUtil.removeDotSegments(var3);
            StringBuffer var8 = new StringBuffer(var1.length());
            var8.append(var4);
            var8.append("://");
            if (var5 != null) {
                var8.append(var5);
            }

            if (var7 >= 0) {
                var8.append(":" + var7);
            }

            var8.append(var3);
            if (var6 != null) {
                var8.append("?" + var6);
            }

            return var8.toString();
        } catch (MalformedURLException var9) {
            return var1;
        }
    }

    private Document build(InputSource var1) throws ParsingException, ValidityException, IOException {
        nu.xom.XOMHandler var2 = (nu.xom.XOMHandler)this.parser.getContentHandler();
        Document var3 = null;

        try {
            ParsingException var5;
            try {
                this.parser.parse(var1);
                var3 = var2.getDocument();
            } catch (SAXParseException var15) {
                var5 = new ParsingException(var15.getMessage(), var15.getSystemId(), var15.getLineNumber(), var15.getColumnNumber(), var15);
                throw var5;
            } catch (SAXException var16) {
                var5 = new ParsingException(var16.getMessage(), var1.getSystemId(), var16);
                throw var5;
            } catch (XMLException var17) {
                throw new ParsingException(var17.getMessage(), var17);
            } catch (RuntimeException var18) {
                var5 = new ParsingException(var18.getMessage(), var1.getSystemId(), var18);
                throw var5;
            } catch (UTFDataFormatException var19) {
                var5 = new ParsingException(var19.getMessage(), var1.getSystemId(), var19);
                throw var5;
            } catch (CharConversionException var20) {
                var5 = new ParsingException(var20.getMessage(), var1.getSystemId(), var20);
                throw var5;
            } catch (IOException var21) {
                if (var21.getClass().getName().equals("org.apache.xerces.util.URI$MalformedURIException")) {
                    throw new ParsingException(var21.getMessage(), var1.getSystemId(), var21);
                }

                throw var21;
            }
        } finally {
            var2.freeMemory();
        }

        if (var3 == null) {
            ParsingException var23 = new ParsingException("Parser did not build document", var1.getSystemId(), -1, -1);
            throw var23;
        } else {
            if ("".equals(var3.getBaseURI())) {
                var3.setBaseURI(var1.getSystemId());
            }

            ErrorHandler var4 = this.parser.getErrorHandler();
            if (var4 instanceof nu.xom.Builder.ValidityRequired) {
                nu.xom.Builder.ValidityRequired var24 = (nu.xom.Builder.ValidityRequired)var4;
                if (!var24.isValid()) {
                    ValidityException var6 = var24.vexception;
                    var6.setDocument(var3);
                    var24.reset();
                    throw var6;
                }
            }

            return var3;
        }
    }

    public NodeFactory getNodeFactory() {
        return this.factory;
    }

    static {
        String var0;
        try {
            var0 = Version.getVersion();
            String var1 = var0.substring(9);
            int var2 = var1.indexOf(".");
            int var3 = var1.lastIndexOf(".");
            String var4 = var1.substring(0, var2);
            String var5 = var1.substring(var2 + 1, var3);
            if (Integer.parseInt(var5) < 10 && Integer.parseInt(var4) < 3) {
                xercesVersion = Double.parseDouble(var0.substring(9, 12));
            }
        } catch (Exception var6) {
        } catch (Error var7) {
        }

        parsers = new String[]{"nu.XML1_0Parser", "nu.xom.JDK15XML1_0Parser", "org.apache.xerces.parsers.SAXParser", "org.apache.xerces.jaxp.SAXParserImpl$JAXPSAXParser", "com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser", "com.sun.org.apache.xerces.internal.parsers.SAXParser", "gnu.xml.aelfred2.XmlReader", "org.apache.crimson.parser.XMLReaderImpl", "com.bluecast.xml.Piccolo", "oracle.xml.parser.v2.SAXParser", "com.jclark.xml.sax.SAX2Driver", "net.sf.saxon.aelfred.SAXDriver", "com.icl.saxon.aelfred.SAXDriver", "org.dom4j.io.aelfred2.SAXDriver", "org.dom4j.io.aelfred.SAXDriver"};
        fileURLPrefix = "file://";
        var0 = System.getProperty("os.name", "Unix");
        if (var0.indexOf("Windows") >= 0) {
            fileURLPrefix = "file:/";
        }

    }

    private static class NamespaceWellformednessRequired implements ErrorHandler {
        private NamespaceWellformednessRequired() {
        }

        public void warning(SAXParseException var1) {
        }

        public void error(SAXParseException var1) throws SAXParseException {
            if (!var1.getMessage().equals("Illegal Namespace prefix: \"xml\".")) {
                throw var1;
            }
        }

        public void fatalError(SAXParseException var1) throws SAXParseException {
            throw var1;
        }
    }

    private static class ValidityRequired implements ErrorHandler {
        ValidityException vexception;

        private ValidityRequired() {
            this.vexception = null;
        }

        void reset() {
            this.vexception = null;
        }

        public void warning(SAXParseException var1) {
        }

        public void error(SAXParseException var1) {
            if (this.vexception == null) {
                this.vexception = new ValidityException(var1.getMessage(), var1.getSystemId(), var1.getLineNumber(), var1.getColumnNumber(), var1);
            }

            this.vexception.addError(var1);
        }

        public void fatalError(SAXParseException var1) throws SAXParseException {
            throw var1;
        }

        boolean isValid() {
            return this.vexception == null;
        }
    }
}
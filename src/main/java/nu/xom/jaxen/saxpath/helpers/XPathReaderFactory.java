package nu.xom.jaxen.saxpath.helpers;


import nu.xom.jaxen.saxpath.SAXPathException;
import nu.xom.jaxen.saxpath.XPathReader;

public class XPathReaderFactory {
    public static final String DRIVER_PROPERTY = "org.saxpath.driver";
    protected static final String DEFAULT_DRIVER = "nu.xom.jaxen.saxpath.base.XPathReader";

    private XPathReaderFactory() {
    }

    public static XPathReader createReader() throws SAXPathException {
        String var0 = null;

        try {
            var0 = System.getProperty("org.saxpath.driver");
        } catch (SecurityException var2) {
        }

        if (var0 == null || var0.length() == 0) {
            var0 = "nu.xom.jaxen.saxpath.base.XPathReader";
        }

        return createReader(var0);
    }

    public static XPathReader createReader(String var0) throws SAXPathException {
        Class var1 = null;
        XPathReader var2 = null;
        //yangning xiugai
        try {
            var1 = Class.forName(var0, true, nu.xom.jaxen.saxpath.helpers.XPathReaderFactory.class.getClassLoader());
            if (!XPathReader.class.isAssignableFrom(var1)) {
                throw new SAXPathException("Class [" + var0 + "] does not implement the org.jaxen.saxpath.XPathReader interface.");
            }
        } catch (ClassNotFoundException var6) {
            throw new SAXPathException(var6);
        }

        try {
            var2 = (XPathReader)var1.newInstance();
            return var2;
        } catch (IllegalAccessException var4) {
            throw new SAXPathException(var4);
        } catch (InstantiationException var5) {
            throw new SAXPathException(var5);
        }
    }
}

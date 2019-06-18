package nu.xom.jaxen;

import nu.xom.jaxen.saxpath.SAXPathException;

public class JaxenException extends SAXPathException {
    private static final long serialVersionUID = 7132891439526672639L;
    static double javaVersion = 1.4D;

    public JaxenException(String var1) {
        super(var1);
    }

    public JaxenException(Throwable var1) {
        super(var1);
    }

    public JaxenException(String var1, Throwable var2) {
        super(var1, var2);
    }

    static {
        try {
            String var0 = System.getProperty("java.version");
            var0 = var0.substring(0, 3);
            javaVersion = Double.valueOf(var0);
        } catch (RuntimeException var1) {
        }

    }
}
package nu.xom.jaxen.saxpath;


import java.io.PrintStream;
import java.io.PrintWriter;

public class SAXPathException extends Exception {
    private static final long serialVersionUID = 4826444568928720706L;
    private static double javaVersion = 1.4D;
    private Throwable cause;
    private boolean causeSet = false;

    public SAXPathException(String var1) {
        super(var1);
    }

    public SAXPathException(Throwable var1) {
        super(var1.getMessage());
        this.initCause(var1);
    }

    public SAXPathException(String var1, Throwable var2) {
        super(var1);
        this.initCause(var2);
    }

    public Throwable getCause() {
        return this.cause;
    }

    public Throwable initCause(Throwable var1) {
        if (this.causeSet) {
            throw new IllegalStateException("Cause cannot be reset");
        } else if (var1 == this) {
            throw new IllegalArgumentException("Exception cannot be its own cause");
        } else {
            this.causeSet = true;
            this.cause = var1;
            return this;
        }
    }

    public void printStackTrace(PrintStream var1) {
        super.printStackTrace(var1);
        if (javaVersion < 1.4D && this.getCause() != null) {
            var1.print("Caused by: ");
            this.getCause().printStackTrace(var1);
        }

    }

    public void printStackTrace(PrintWriter var1) {
        super.printStackTrace(var1);
        if (javaVersion < 1.4D && this.getCause() != null) {
            var1.print("Caused by: ");
            this.getCause().printStackTrace(var1);
        }

    }

    static {
        try {
            String var0 = System.getProperty("java.version");
            var0 = var0.substring(0, 3);
            javaVersion = Double.valueOf(var0);
        } catch (Exception var1) {
        }

    }
}

package nu.xom.jaxen;


import java.io.PrintStream;
import java.io.PrintWriter;

public class JaxenRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -930309761511911193L;
    private Throwable cause;
    private boolean causeSet = false;

    public JaxenRuntimeException(Throwable var1) {
        super(var1.getMessage());
        this.initCause(var1);
    }

    public JaxenRuntimeException(String var1) {
        super(var1);
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
        if (JaxenException.javaVersion < 1.4D && this.getCause() != null) {
            var1.print("Caused by: ");
            this.getCause().printStackTrace(var1);
        }

    }

    public void printStackTrace(PrintWriter var1) {
        super.printStackTrace(var1);
        if (JaxenException.javaVersion < 1.4D && this.getCause() != null) {
            var1.print("Caused by: ");
            this.getCause().printStackTrace(var1);
        }

    }
}


package nu.xom;


public class XMLException extends RuntimeException {
    private static final long serialVersionUID = -4497254051626978523L;
    private Throwable cause;
    private boolean causeSet = false;

    public XMLException(String var1, Throwable var2) {
        super(var1);
        this.initCause(var2);
    }

    public XMLException(String var1) {
        super(var1);
    }

    public Throwable getCause() {
        return this.cause;
    }

    public final Throwable initCause(Throwable var1) {
        if (this.causeSet) {
            throw new IllegalStateException("Can't overwrite cause");
        } else if (var1 == this) {
            throw new IllegalArgumentException("Self-causation not permitted");
        } else {
            this.cause = var1;
            this.causeSet = true;
            return this;
        }
    }
}

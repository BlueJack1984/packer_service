package nu.xom;


public class XPathException extends RuntimeException {
    private static final long serialVersionUID = 6362087755031657439L;
    private String expression;
    private Throwable cause;
    private boolean causeSet = false;

    public XPathException(String var1) {
        super(var1);
    }

    public XPathException(String var1, Throwable var2) {
        super(var1);
        this.initCause(var2);
    }

    public Throwable getCause() {
        return this.cause;
    }

    public Throwable initCause(Throwable var1) {
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

    void setXPath(String var1) {
        this.expression = var1;
    }

    public String getXPath() {
        return this.expression;
    }
}


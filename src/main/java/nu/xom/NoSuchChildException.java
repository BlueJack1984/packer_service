package nu.xom;

public class NoSuchChildException extends XMLException {
    private static final long serialVersionUID = 1944673590646036964L;

    public NoSuchChildException(String var1) {
        super(var1);
    }

    public NoSuchChildException(String var1, Throwable var2) {
        super(var1);
        this.initCause(var2);
    }
}


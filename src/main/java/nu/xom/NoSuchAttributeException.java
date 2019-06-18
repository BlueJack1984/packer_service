package nu.xom;

public class NoSuchAttributeException extends XMLException {
    private static final long serialVersionUID = -7472517723464699452L;

    public NoSuchAttributeException(String var1) {
        super(var1);
    }

    public NoSuchAttributeException(String var1, Throwable var2) {
        super(var1);
        this.initCause(var2);
    }
}

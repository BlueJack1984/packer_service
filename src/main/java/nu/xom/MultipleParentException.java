package nu.xom;

public class MultipleParentException extends IllegalAddException {
    private static final long serialVersionUID = 2605448365902777548L;

    public MultipleParentException(String var1) {
        super(var1);
    }

    public MultipleParentException(String var1, Throwable var2) {
        super(var1, var2);
    }
}


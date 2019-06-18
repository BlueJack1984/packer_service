package nu.xom;

public class CycleException extends IllegalAddException {
    private static final long serialVersionUID = 6690940405434358625L;

    public CycleException(String var1) {
        super(var1);
    }

    public CycleException(String var1, Throwable var2) {
        super(var1, var2);
    }
}

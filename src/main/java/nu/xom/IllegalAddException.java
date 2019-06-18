package nu.xom;

public class IllegalAddException extends WellformednessException {
    private static final long serialVersionUID = 6153993399665387138L;

    public IllegalAddException(String var1) {
        super(var1);
    }

    public IllegalAddException(String var1, Throwable var2) {
        super(var1, var2);
    }
}

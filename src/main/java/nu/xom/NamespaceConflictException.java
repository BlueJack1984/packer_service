package nu.xom;

public class NamespaceConflictException extends WellformednessException {
    private static final long serialVersionUID = -3527557666747617537L;

    public NamespaceConflictException(String var1) {
        super(var1);
    }

    public NamespaceConflictException(String var1, Throwable var2) {
        super(var1, var2);
    }
}

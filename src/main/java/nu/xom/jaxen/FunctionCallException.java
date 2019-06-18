package nu.xom.jaxen;

public class FunctionCallException extends JaxenException {
    private static final long serialVersionUID = 7908649612495640943L;

    public FunctionCallException(String var1) {
        super(var1);
    }

    public FunctionCallException(Throwable var1) {
        super(var1);
    }

    public FunctionCallException(String var1, Exception var2) {
        super(var1, var2);
    }

    /** @deprecated */
    public Throwable getNestedException() {
        return this.getCause();
    }
}

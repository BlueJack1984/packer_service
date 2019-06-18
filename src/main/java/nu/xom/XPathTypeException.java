package nu.xom;

public class XPathTypeException extends XPathException {
    private static final long serialVersionUID = 1247817719683497718L;
    private final Object returnValue;

    XPathTypeException(Object var1) {
        super("");
        this.returnValue = var1;
    }

    public Object getReturnValue() {
        return this.returnValue;
    }

    public String getMessage() {
        String var1 = this.getXPath();
        String var2 = this.returnValue.getClass().getName();
        return var1 == null ? "XPath expression returned a " + var2 + " instead of a node-set." : "XPath expression " + var1 + " returned a " + var2 + " instead of a node-set.";
    }
}

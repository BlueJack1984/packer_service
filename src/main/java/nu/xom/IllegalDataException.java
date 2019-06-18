package nu.xom;

public class IllegalDataException extends WellformednessException {
    private static final long serialVersionUID = 5116683358318890040L;
    private String data;

    public IllegalDataException(String var1) {
        super(var1);
    }

    public IllegalDataException(String var1, Throwable var2) {
        super(var1, var2);
    }

    public void setData(String var1) {
        this.data = var1;
    }

    public String getData() {
        return this.data;
    }
}

package nu.xom;


public class ParsingException extends Exception {
    private static final long serialVersionUID = -5185450480590174490L;
    private Throwable cause;
    private int lineNumber;
    private int columnNumber;
    private String uri;
    private boolean causeSet;

    public ParsingException(String var1, Throwable var2) {
        super(var1);
        this.lineNumber = -1;
        this.columnNumber = -1;
        this.causeSet = false;
        this.initCause(var2);
    }

    public ParsingException(String var1, String var2, Throwable var3) {
        super(var1);
        this.lineNumber = -1;
        this.columnNumber = -1;
        this.causeSet = false;
        this.uri = var2;
        this.initCause(var3);
    }

    public ParsingException(String var1, int var2, int var3) {
        this(var1, (String)null, var2, var3, (Throwable)null);
    }

    public ParsingException(String var1, String var2, int var3, int var4) {
        this(var1, var2, var3, var4, (Throwable)null);
    }

    public ParsingException(String var1, String var2, int var3, int var4, Throwable var5) {
        super(var1);
        this.lineNumber = -1;
        this.columnNumber = -1;
        this.causeSet = false;
        this.lineNumber = var3;
        this.columnNumber = var4;
        this.uri = var2;
        this.initCause(var5);
    }

    public ParsingException(String var1, int var2, int var3, Throwable var4) {
        super(var1);
        this.lineNumber = -1;
        this.columnNumber = -1;
        this.causeSet = false;
        this.lineNumber = var2;
        this.columnNumber = var3;
        this.initCause(var4);
    }

    public ParsingException(String var1) {
        super(var1);
        this.lineNumber = -1;
        this.columnNumber = -1;
        this.causeSet = false;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public String getURI() {
        return this.uri;
    }

    public final Throwable initCause(Throwable var1) {
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

    public Throwable getCause() {
        return this.cause;
    }

    public String toString() {
        StringBuffer var1 = new StringBuffer(super.toString());
        if (this.lineNumber >= 0) {
            var1.append(" at line ");
            var1.append(this.lineNumber);
            var1.append(", column ");
            var1.append(this.columnNumber);
        }

        if (this.uri != null) {
            var1.append(" in ");
            var1.append(this.uri);
        }

        return var1.toString();
    }
}

package nu.xom;

public class ProcessingInstruction extends Node {
    private String target;
    private String data;

    public ProcessingInstruction(String var1, String var2) {
        this._setTarget(var1);
        this._setValue(var2);
    }

    public ProcessingInstruction(nu.xom.ProcessingInstruction var1) {
        this.target = var1.target;
        this.data = var1.data;
    }

    private ProcessingInstruction() {
    }

    static nu.xom.ProcessingInstruction build(String var0, String var1) {
        nu.xom.ProcessingInstruction var2 = new nu.xom.ProcessingInstruction();
        var2.target = var0;
        var2.data = var1;
        return var2;
    }

    public final String getTarget() {
        return this.target;
    }

    public void setTarget(String var1) {
        this._setTarget(var1);
    }

    private void _setTarget(String var1) {
        try {
            Verifier.checkNCName(var1);
        } catch (IllegalNameException var4) {
            IllegalTargetException var3 = new IllegalTargetException(var4.getMessage());
            var3.setData(var1);
            throw var3;
        }

        if (var1.equalsIgnoreCase("xml")) {
            IllegalTargetException var2 = new IllegalTargetException(var1 + " is not a legal processing instruction target.");
            var2.setData(var1);
            throw var2;
        } else {
            this.target = var1;
        }
    }

    public void setValue(String var1) {
        this._setValue(var1);
    }

    private void _setValue(String var1) {
        Verifier.checkPCDATA(var1);
        if (var1.length() != 0) {
            IllegalDataException var4;
            if (var1.indexOf("?>") >= 0) {
                var4 = new IllegalDataException("Processing instruction data must not contain \"?>\"");
                var4.setData(var1);
                throw var4;
            }

            if (var1.indexOf(13) >= 0) {
                var4 = new IllegalDataException("Processing instruction data cannot contain carriage returns");
                var4.setData(var1);
                throw var4;
            }

            char var2 = var1.charAt(0);
            if (var2 == ' ' || var2 == '\n' || var2 == '\t') {
                IllegalDataException var3 = new IllegalDataException("Processing instruction data cannot contain leading white space");
                var3.setData(var1);
                throw var3;
            }
        }

        this.data = var1;
    }

    public final String getValue() {
        return this.data;
    }

    public final Node getChild(int var1) {
        throw new IndexOutOfBoundsException("LeafNodes do not have children");
    }

    public final int getChildCount() {
        return 0;
    }

    public final String toXML() {
        StringBuffer var1 = new StringBuffer("<?");
        var1.append(this.target);
        if (this.data.length() > 0) {
            var1.append(' ');
            var1.append(this.data);
        }

        var1.append("?>");
        return var1.toString();
    }

    public Node copy() {
        return new nu.xom.ProcessingInstruction(this.target, this.data);
    }

    boolean isProcessingInstruction() {
        return true;
    }

    public final String toString() {
        return "[" + this.getClass().getName() + ": target=\"" + this.target + "\"; data=\"" + Text.escapeLineBreaksAndTruncate(this.data) + "\"]";
    }
}
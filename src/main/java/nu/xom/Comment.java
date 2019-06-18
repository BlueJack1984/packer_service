package nu.xom;


public class Comment extends Node {
    private String data;

    public Comment(String var1) {
        this._setValue(var1);
    }

    public Comment(nu.xom.Comment var1) {
        this.data = var1.data;
    }

    private Comment() {
    }

    static nu.xom.Comment build(String var0) {
        nu.xom.Comment var1 = new nu.xom.Comment();
        var1.data = var0;
        return var1;
    }

    public final String getValue() {
        return this.data;
    }

    public void setValue(String var1) {
        this._setValue(var1);
    }

    private void _setValue(String var1) {
        if (var1 == null) {
            var1 = "";
        } else {
            nu.xom.Verifier.checkPCDATA(var1);
            IllegalDataException var2;
            if (var1.indexOf("--") != -1) {
                var2 = new IllegalDataException("Comment data contains a double hyphen (--).");
                var2.setData(var1);
                throw var2;
            }

            if (var1.indexOf(13) != -1) {
                var2 = new IllegalDataException("Comment data cannot contain carriage returns.");
                var2.setData(var1);
                throw var2;
            }

            if (var1.endsWith("-")) {
                var2 = new IllegalDataException("Comment data ends with a hyphen.");
                var2.setData(var1);
                throw var2;
            }
        }

        this.data = var1;
    }

    public final Node getChild(int var1) {
        throw new IndexOutOfBoundsException("LeafNodes do not have children");
    }

    public final int getChildCount() {
        return 0;
    }

    public Node copy() {
        return new nu.xom.Comment(this.data);
    }

    public final String toXML() {
        StringBuffer var1 = new StringBuffer("<!--");
        var1.append(this.data);
        var1.append("-->");
        return var1.toString();
    }

    public final String toString() {
        String var1 = this.getValue();
        return var1.length() <= 40 ? "[" + this.getClass().getName() + ": " + Text.escapeLineBreaksAndTruncate(var1) + "]" : "[" + this.getClass().getName() + ": " + Text.escapeLineBreaksAndTruncate(var1.substring(0, 35)) + "...]";
    }

    boolean isComment() {
        return true;
    }
}

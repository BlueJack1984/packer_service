package nu.xom;


import java.io.UnsupportedEncodingException;

public class Text extends Node {
    private byte[] data;

    public Text(String var1) {
        this._setValue(var1);
    }

    public Text(nu.xom.Text var1) {
        this.data = var1.data;
    }

    private Text() {
    }

    static nu.xom.Text build(String var0) {
        nu.xom.Text var1 = new nu.xom.Text();

        try {
            var1.data = var0.getBytes("UTF8");
            return var1;
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException("Bad VM! Does not support UTF-8");
        }
    }

    public void setValue(String var1) {
        this._setValue(var1);
    }

    private void _setValue(String var1) {
        if (var1 == null) {
            var1 = "";
        } else {
            Verifier.checkPCDATA(var1);
        }

        try {
            this.data = var1.getBytes("UTF8");
        } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException("Bad VM! Does not support UTF-8");
        }
    }

    public final String getValue() {
        try {
            return new String(this.data, "UTF8");
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("Bad VM! Does not support UTF-8");
        }
    }

    public final Node getChild(int var1) {
        throw new IndexOutOfBoundsException("LeafNodes do not have children");
    }

    public final int getChildCount() {
        return 0;
    }

    public Node copy() {
        return (Node)(this.isCDATASection() ? new nu.xom.CDATASection(this) : new nu.xom.Text(this));
    }

    public final String toXML() {
        return this.escapeText();
    }

    String escapeText() {
        String var1 = this.getValue();
        int var2 = var1.length();
        StringBuffer var3 = new StringBuffer(var2 + 12);

        for(int var4 = 0; var4 < var2; ++var4) {
            char var5 = var1.charAt(var4);
            switch(var5) {
                case '\r':
                    var3.append("&#x0D;");
                case '\u000e':
                case '\u000f':
                case '\u0010':
                case '\u0011':
                case '\u0012':
                case '\u0013':
                case '\u0014':
                case '\u0015':
                case '\u0016':
                case '\u0017':
                case '\u0018':
                case '\u0019':
                case '\u001a':
                case '\u001b':
                case '\u001c':
                case '\u001d':
                case '\u001e':
                case '\u001f':
                    break;
                case ' ':
                    var3.append(' ');
                    break;
                case '!':
                    var3.append('!');
                    break;
                case '"':
                    var3.append('"');
                    break;
                case '#':
                    var3.append('#');
                    break;
                case '$':
                    var3.append('$');
                    break;
                case '%':
                    var3.append('%');
                    break;
                case '&':
                    var3.append("&amp;");
                    break;
                case '\'':
                    var3.append('\'');
                    break;
                case '(':
                    var3.append('(');
                    break;
                case ')':
                    var3.append(')');
                    break;
                case '*':
                    var3.append('*');
                    break;
                case '+':
                    var3.append('+');
                    break;
                case ',':
                    var3.append(',');
                    break;
                case '-':
                    var3.append('-');
                    break;
                case '.':
                    var3.append('.');
                    break;
                case '/':
                    var3.append('/');
                    break;
                case '0':
                    var3.append('0');
                    break;
                case '1':
                    var3.append('1');
                    break;
                case '2':
                    var3.append('2');
                    break;
                case '3':
                    var3.append('3');
                    break;
                case '4':
                    var3.append('4');
                    break;
                case '5':
                    var3.append('5');
                    break;
                case '6':
                    var3.append('6');
                    break;
                case '7':
                    var3.append('7');
                    break;
                case '8':
                    var3.append('8');
                    break;
                case '9':
                    var3.append('9');
                    break;
                case ':':
                    var3.append(':');
                    break;
                case ';':
                    var3.append(';');
                    break;
                case '<':
                    var3.append("&lt;");
                    break;
                case '=':
                    var3.append('=');
                    break;
                case '>':
                    var3.append("&gt;");
                    break;
                default:
                    var3.append(var5);
            }
        }

        return var3.toString();
    }

    boolean isText() {
        return true;
    }

    public final String toString() {
        return "[" + this.getClass().getName() + ": " + escapeLineBreaksAndTruncate(this.getValue()) + "]";
    }

    static String escapeLineBreaksAndTruncate(String var0) {
        int var1 = var0.length();
        boolean var2 = var1 > 40;
        if (var1 > 40) {
            var1 = 35;
            var0 = var0.substring(0, 35);
        }

        StringBuffer var3 = new StringBuffer(var1);

        for(int var4 = 0; var4 < var1; ++var4) {
            char var5 = var0.charAt(var4);
            switch(var5) {
                case '\t':
                    var3.append("\\t");
                    break;
                case '\n':
                    var3.append("\\n");
                    break;
                case '\u000b':
                case '\f':
                default:
                    var3.append(var5);
                    break;
                case '\r':
                    var3.append("\\r");
            }
        }

        if (var2) {
            var3.append("...");
        }

        return var3.toString();
    }

    boolean isCDATASection() {
        return false;
    }

    boolean isEmpty() {
        return this.data.length == 0;
    }
}

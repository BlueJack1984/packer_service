package nu.xom;


import java.io.IOException;
import java.io.Writer;

abstract class TextWriter {
    protected final Writer out;
    protected final String encoding;
    private String lineSeparator = "\r\n";
    boolean lineSeparatorSet = false;
    private boolean inDocType = false;
    private int maxLength = 0;
    private int indent = 0;
    private String indentString = "";
    protected int column = 0;
    private boolean preserveSpace = false;
    protected boolean normalize = false;
    protected boolean lastCharacterWasSpace = false;
    protected boolean skipFollowingLinefeed = false;
    private char highSurrogate;
    protected boolean justBroke = false;
    private int fakeIndents = 0;
    private static final String _128_SPACES = "                                                                                                                                ";
    private static final int _128 = 128;

    protected TextWriter(Writer var1, String var2) {
        this.out = var1;
        this.encoding = var2;
    }

    void reset() {
        this.column = 0;
        this.fakeIndents = 0;
        this.lastCharacterWasSpace = false;
        this.skipFollowingLinefeed = false;
    }

    private boolean isHighSurrogate(int var1) {
        return var1 >= 55296 && var1 <= 56319;
    }

    private boolean isLowSurrogate(int var1) {
        return var1 >= 56320 && var1 <= 57343;
    }

    final void writePCDATA(char var1) throws IOException {
        switch(var1) {
            case '\r':
                if (!this.adjustingWhiteSpace() && !this.lineSeparatorSet) {
                    this.out.write("&#x0D;");
                    this.column += 6;
                    this.justBroke = false;
                } else {
                    this.breakLine();
                    this.lastCharacterWasSpace = true;
                }

                this.skipFollowingLinefeed = true;
                break;
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
                throw new XMLException("Bad character snuck into document");
            case ' ':
                this.write(var1);
                break;
            case '!':
                this.write(var1);
                break;
            case '"':
                this.write(var1);
                break;
            case '#':
                this.write(var1);
                break;
            case '$':
                this.write(var1);
                break;
            case '%':
                this.write(var1);
                break;
            case '&':
                this.out.write("&amp;");
                this.column += 5;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            case '\'':
                this.write(var1);
                break;
            case '(':
                this.write(var1);
                break;
            case ')':
                this.write(var1);
                break;
            case '*':
                this.write(var1);
                break;
            case '+':
                this.write(var1);
                break;
            case ',':
                this.write(var1);
                break;
            case '-':
                this.write(var1);
                break;
            case '.':
                this.write(var1);
                break;
            case '/':
                this.write(var1);
                break;
            case '0':
                this.write(var1);
                break;
            case '1':
                this.write(var1);
                break;
            case '2':
                this.write(var1);
                break;
            case '3':
                this.write(var1);
                break;
            case '4':
                this.write(var1);
                break;
            case '5':
                this.write(var1);
                break;
            case '6':
                this.write(var1);
                break;
            case '7':
                this.write(var1);
                break;
            case '8':
                this.write(var1);
                break;
            case '9':
                this.write(var1);
                break;
            case ':':
                this.write(var1);
                break;
            case ';':
                this.write(var1);
                break;
            case '<':
                this.out.write("&lt;");
                this.column += 4;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            case '=':
                this.write(var1);
                break;
            case '>':
                this.out.write("&gt;");
                this.column += 4;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            default:
                if (this.needsEscaping(var1)) {
                    this.writeEscapedChar(var1);
                } else {
                    this.write(var1);
                }
        }

    }

    private void writeEscapedChar(char var1) throws IOException {
        if (this.isHighSurrogate(var1)) {
            this.highSurrogate = var1;
        } else if (this.isLowSurrogate(var1)) {
            int var2 = nu.xom.UnicodeUtil.combineSurrogatePair(this.highSurrogate, var1);
            String var3 = "&#x" + Integer.toHexString(var2).toUpperCase() + ';';
            this.out.write(var3);
            this.column += var3.length();
            this.lastCharacterWasSpace = false;
            this.skipFollowingLinefeed = false;
            this.justBroke = false;
        } else {
            String var4 = "&#x" + Integer.toHexString(var1).toUpperCase() + ';';
            this.out.write(var4);
            this.column += var4.length();
            this.lastCharacterWasSpace = false;
            this.skipFollowingLinefeed = false;
            this.justBroke = false;
        }

    }

    private boolean adjustingWhiteSpace() {
        return this.maxLength > 0 || this.indent > 0;
    }

    final void writeAttributeValue(char var1) throws IOException {
        switch(var1) {
            case '\t':
                if (!this.adjustingWhiteSpace()) {
                    this.out.write("&#x09;");
                    this.column += 6;
                    this.lastCharacterWasSpace = true;
                    this.skipFollowingLinefeed = false;
                    this.justBroke = false;
                } else {
                    this.write(' ');
                }
                break;
            case '\n':
                if (this.skipFollowingLinefeed) {
                    this.skipFollowingLinefeed = false;
                    return;
                }

                if (this.adjustingWhiteSpace()) {
                    this.out.write(" ");
                    this.lastCharacterWasSpace = true;
                    this.justBroke = false;
                } else {
                    if (this.lineSeparatorSet) {
                        this.escapeBreakLine();
                    } else {
                        this.out.write("&#x0A;");
                        this.column += 6;
                        this.justBroke = false;
                    }

                    this.lastCharacterWasSpace = true;
                }
                break;
            case '\u000b':
            case '\f':
                throw new XMLException("Bad character snuck into document");
            case '\r':
                if (this.adjustingWhiteSpace()) {
                    this.out.write(" ");
                    this.lastCharacterWasSpace = true;
                    this.skipFollowingLinefeed = true;
                    this.justBroke = false;
                } else if (this.lineSeparatorSet) {
                    this.escapeBreakLine();
                    this.skipFollowingLinefeed = true;
                } else {
                    this.out.write("&#x0D;");
                    this.column += 6;
                    this.justBroke = false;
                }
                break;
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
                throw new XMLException("Bad character snuck into document");
            case ' ':
                this.write(var1);
                break;
            case '!':
                this.write(var1);
                break;
            case '"':
                this.out.write("&quot;");
                this.column += 6;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            case '#':
                this.write(var1);
                break;
            case '$':
                this.write(var1);
                break;
            case '%':
                this.write(var1);
                break;
            case '&':
                this.out.write("&amp;");
                this.column += 5;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            case '\'':
                this.write(var1);
                break;
            case '(':
                this.write(var1);
                break;
            case ')':
                this.write(var1);
                break;
            case '*':
                this.write(var1);
                break;
            case '+':
                this.write(var1);
                break;
            case ',':
                this.write(var1);
                break;
            case '-':
                this.write(var1);
                break;
            case '.':
                this.write(var1);
                break;
            case '/':
                this.write(var1);
                break;
            case '0':
                this.write(var1);
                break;
            case '1':
                this.write(var1);
                break;
            case '2':
                this.write(var1);
                break;
            case '3':
                this.write(var1);
                break;
            case '4':
                this.write(var1);
                break;
            case '5':
                this.write(var1);
                break;
            case '6':
                this.write(var1);
                break;
            case '7':
                this.write(var1);
                break;
            case '8':
                this.write(var1);
                break;
            case '9':
                this.write(var1);
                break;
            case ':':
                this.write(var1);
                break;
            case ';':
                this.write(var1);
                break;
            case '<':
                this.out.write("&lt;");
                this.column += 4;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            case '=':
                this.write(var1);
                break;
            case '>':
                this.out.write("&gt;");
                this.column += 4;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
                break;
            default:
                if (this.needsEscaping(var1)) {
                    this.writeEscapedChar(var1);
                } else {
                    this.write(var1);
                }
        }

    }

    void write(char var1) throws IOException {
        if (var1 != ' ' && var1 != '\n' && var1 != '\t') {
            this.out.write(var1);
            if (var1 < '\ud800' || var1 > '\udbff') {
                ++this.column;
            }

            this.lastCharacterWasSpace = false;
            this.skipFollowingLinefeed = false;
            this.justBroke = false;
        } else {
            if (this.needsBreak()) {
                this.breakLine();
                this.skipFollowingLinefeed = false;
            } else if (!this.preserveSpace && (this.indent > 0 || this.maxLength > 0)) {
                if (!this.lastCharacterWasSpace) {
                    this.out.write(32);
                    ++this.column;
                    this.skipFollowingLinefeed = false;
                    this.justBroke = false;
                }
            } else if (var1 != ' ' && var1 != '\t') {
                if (!this.lineSeparatorSet || !this.skipFollowingLinefeed) {
                    this.writeLineSeparator(var1);
                }

                this.skipFollowingLinefeed = false;
                this.column = 0;
            } else {
                this.out.write(var1);
                this.skipFollowingLinefeed = false;
                ++this.column;
                this.justBroke = false;
            }

            this.lastCharacterWasSpace = true;
        }

    }

    private void writeLineSeparator(char var1) throws IOException {
        if (this.inDocType || this.lineSeparatorSet && !this.preserveSpace) {
            if (this.lineSeparator.equals("\r\n")) {
                this.out.write("\r\n");
            } else if (this.lineSeparator.equals("\n")) {
                this.out.write(10);
            } else {
                this.out.write(13);
            }
        } else {
            this.out.write(var1);
        }

    }

    private boolean needsBreak() {
        if (this.maxLength > 0 && !this.preserveSpace) {
            return this.column >= this.maxLength - 10;
        } else {
            return false;
        }
    }

    boolean justBroke() {
        return this.justBroke;
    }

    final void breakLine() throws IOException {
        this.out.write(this.lineSeparator);
        this.out.write(this.indentString);
        this.column = this.indentString.length();
        this.lastCharacterWasSpace = true;
        this.justBroke = true;
    }

    private final void escapeBreakLine() throws IOException {
        if ("\n".equals(this.lineSeparator)) {
            this.out.write("&#x0A;");
            this.column += 6;
        } else if ("\r\n".equals(this.lineSeparator)) {
            this.out.write("&#x0D;&#x0A;");
            this.column += 12;
        } else {
            this.out.write("&#x0D;");
            this.column += 6;
        }

        this.lastCharacterWasSpace = true;
    }

    final void writeMarkup(char var1) throws IOException {
        if (this.needsEscaping(var1)) {
            throw new UnavailableCharacterException(var1, this.encoding);
        } else {
            this.write(var1);
        }
    }

    void writePCDATA(String var1) throws IOException {
        var1 = this.normalize(var1);
        int var2 = var1.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            this.writePCDATA(var1.charAt(var3));
        }

    }

    void writeAttributeValue(String var1) throws IOException {
        var1 = this.normalize(var1);
        int var2 = var1.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            this.writeAttributeValue(var1.charAt(var3));
        }

    }

    void writeMarkup(String var1) throws IOException {
        var1 = this.normalize(var1);
        int var2 = var1.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            this.writeMarkup(var1.charAt(var3));
        }

    }

    void writeUncheckedMarkup(String var1) throws IOException {
        int var2 = var1.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            this.write(var1.charAt(var3));
        }

    }

    protected String normalize(String var1) {
        return this.normalize ? nu.xom.UnicodeUtil.normalize(var1) : var1;
    }

    boolean isIndenting() {
        return this.indentString.length() > 0;
    }

    void incrementIndent() {
        if (this.indent != 0) {
            int var1 = this.indentString.length() + this.indent;
            String var2;
            if (this.indentString.length() + this.indent < 128) {
                var2 = "                                                                                                                                ".substring(0, var1);
            } else {
                StringBuffer var3 = new StringBuffer(var1);
                var3.append("                                                                                                                                ");

                for(int var4 = 128; var4 < var1; ++var4) {
                    var3.append(' ');
                }

                var2 = var3.toString();
            }

            if (this.maxLength > 0 && var2.length() > this.maxLength / 2) {
                ++this.fakeIndents;
            } else {
                this.indentString = var2;
            }

        }
    }

    void decrementIndent() {
        if (this.indent != 0) {
            if (this.fakeIndents > 0) {
                --this.fakeIndents;
            } else {
                this.indentString = this.indentString.substring(0, this.indentString.length() - this.indent);
            }

        }
    }

    String getEncoding() {
        return this.encoding;
    }

    String getLineSeparator() {
        return this.lineSeparator;
    }

    void setLineSeparator(String var1) {
        if (!var1.equals("\n") && !var1.equals("\r") && !var1.equals("\r\n")) {
            throw new IllegalArgumentException("Illegal Line Separator");
        } else {
            this.lineSeparator = var1;
            this.lineSeparatorSet = true;
        }
    }

    void setInDocType(boolean var1) {
        this.inDocType = var1;
    }

    int getIndent() {
        return this.indent;
    }

    int getMaxLength() {
        return this.maxLength;
    }

    void setMaxLength(int var1) {
        if (var1 < 0) {
            var1 = 0;
        }

        this.maxLength = var1;
    }

    void setIndent(int var1) {
        this.indent = var1;
    }

    void flush() throws IOException {
        this.out.flush();
    }

    abstract boolean needsEscaping(char var1);

    boolean isPreserveSpace() {
        return this.preserveSpace;
    }

    void setPreserveSpace(boolean var1) {
        this.preserveSpace = var1;
    }

    int getColumnNumber() {
        return this.column;
    }

    void setNFC(boolean var1) {
        this.normalize = var1;
    }

    boolean getNFC() {
        return this.normalize;
    }

    void writeName(String var1) throws IOException {
        this.writeMarkup(var1);
    }
}

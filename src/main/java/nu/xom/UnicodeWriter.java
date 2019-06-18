//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nu.xom;

import java.io.IOException;
import java.io.Writer;

final class UnicodeWriter extends TextWriter {
    UnicodeWriter(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        return false;
    }

    void writeMarkup(String var1) throws IOException {
        if (this.normalize) {
            var1 = this.normalize(var1);
        }

        int var2 = getUnicodeLengthForMarkup(var1);
        if (var2 >= 0) {
            this.out.write(var1);
            if (var2 > 0) {
                this.column += var2;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
            }
        } else {
            int var3 = var1.length();

            for(int var4 = 0; var4 < var3; ++var4) {
                this.writeMarkup(var1.charAt(var4));
            }
        }

    }

    void writeName(String var1) throws IOException {
        if (this.normalize) {
            var1 = this.normalize(var1);
        }

        int var2 = getUnicodeLengthForName(var1);
        this.out.write(var1);
        this.column += var2;
        this.lastCharacterWasSpace = false;
        this.skipFollowingLinefeed = false;
        this.justBroke = false;
    }

    private static int getUnicodeLengthForMarkup(String var0) {
        int var1 = 0;
        int var2 = var0.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            char var4 = var0.charAt(var3);
            if (var4 <= ' ') {
                return -1;
            }

            if (var4 < '\ud800' || var4 > '\udbff') {
                ++var1;
            }
        }

        return var1;
    }

    private static int getUnicodeLengthForName(String var0) {
        int var1 = 0;
        int var2 = var0.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            char var4 = var0.charAt(var3);
            if (var4 < '\ud800' || var4 > '\udbff') {
                ++var1;
            }
        }

        return var1;
    }

    void writeAttributeValue(String var1) throws IOException {
        if (this.normalize) {
            var1 = this.normalize(var1);
        }

        int var2 = getUnicodeLengthForAttributeValue(var1);
        if (var2 >= 0) {
            this.out.write(var1);
            if (var2 > 0) {
                this.column += var2;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
            }
        } else {
            int var3 = var1.length();

            for(int var4 = 0; var4 < var3; ++var4) {
                this.writeAttributeValue(var1.charAt(var4));
            }
        }

    }

    private static int getUnicodeLengthForAttributeValue(String var0) {
        int var1 = 0;
        int var2 = var0.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case '\t':
                    return -1;
                case '\n':
                    return -1;
                case '\u000b':
                case '\f':
                    throw new XMLException("Bad character snuck into document");
                case '\r':
                    return -1;
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
                    return -1;
                case '!':
                    ++var1;
                    break;
                case '"':
                    return -1;
                case '#':
                    ++var1;
                    break;
                case '$':
                    ++var1;
                    break;
                case '%':
                    ++var1;
                    break;
                case '&':
                    return -1;
                case '\'':
                    ++var1;
                    break;
                case '(':
                    ++var1;
                    break;
                case ')':
                    ++var1;
                    break;
                case '*':
                    ++var1;
                    break;
                case '+':
                    ++var1;
                    break;
                case ',':
                    ++var1;
                    break;
                case '-':
                    ++var1;
                    break;
                case '.':
                    ++var1;
                    break;
                case '/':
                    ++var1;
                    break;
                case '0':
                    ++var1;
                    break;
                case '1':
                    ++var1;
                    break;
                case '2':
                    ++var1;
                    break;
                case '3':
                    ++var1;
                    break;
                case '4':
                    ++var1;
                    break;
                case '5':
                    ++var1;
                    break;
                case '6':
                    ++var1;
                    break;
                case '7':
                    ++var1;
                    break;
                case '8':
                    ++var1;
                    break;
                case '9':
                    ++var1;
                    break;
                case ':':
                    ++var1;
                    break;
                case ';':
                    ++var1;
                    break;
                case '<':
                    return -1;
                case '=':
                    ++var1;
                    break;
                case '>':
                    return -1;
                default:
                    if (var4 < '\ud800' || var4 > '\udbff') {
                        ++var1;
                    }
            }
        }

        return var1;
    }

    void writePCDATA(String var1) throws IOException {
        if (this.normalize) {
            var1 = this.normalize(var1);
        }

        int var2 = getUnicodeLengthForPCDATA(var1);
        if (var2 >= 0) {
            this.out.write(var1);
            if (var2 > 0) {
                this.column += var2;
                this.lastCharacterWasSpace = false;
                this.skipFollowingLinefeed = false;
                this.justBroke = false;
            }
        } else {
            int var3 = var1.length();

            for(int var4 = 0; var4 < var3; ++var4) {
                this.writePCDATA(var1.charAt(var4));
            }
        }

    }

    private static int getUnicodeLengthForPCDATA(String var0) {
        int var1 = 0;
        int var2 = var0.length();

        for(int var3 = 0; var3 < var2; ++var3) {
            char var4 = var0.charAt(var3);
            switch(var4) {
                case '\t':
                    return -1;
                case '\n':
                    return -1;
                case '\u000b':
                case '\f':
                    throw new XMLException("Bad character snuck into document");
                case '\r':
                    return -1;
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
                    return -1;
                case '!':
                    ++var1;
                    break;
                case '"':
                    ++var1;
                    break;
                case '#':
                    ++var1;
                    break;
                case '$':
                    ++var1;
                    break;
                case '%':
                    ++var1;
                    break;
                case '&':
                    return -1;
                case '\'':
                    ++var1;
                    break;
                case '(':
                    ++var1;
                    break;
                case ')':
                    ++var1;
                    break;
                case '*':
                    ++var1;
                    break;
                case '+':
                    ++var1;
                    break;
                case ',':
                    ++var1;
                    break;
                case '-':
                    ++var1;
                    break;
                case '.':
                    ++var1;
                    break;
                case '/':
                    ++var1;
                    break;
                case '0':
                    ++var1;
                    break;
                case '1':
                    ++var1;
                    break;
                case '2':
                    ++var1;
                    break;
                case '3':
                    ++var1;
                    break;
                case '4':
                    ++var1;
                    break;
                case '5':
                    ++var1;
                    break;
                case '6':
                    ++var1;
                    break;
                case '7':
                    ++var1;
                    break;
                case '8':
                    ++var1;
                    break;
                case '9':
                    ++var1;
                    break;
                case ':':
                    ++var1;
                    break;
                case ';':
                    ++var1;
                    break;
                case '<':
                    return -1;
                case '=':
                    ++var1;
                    break;
                case '>':
                    return -1;
                default:
                    if (var4 < '\ud800' || var4 > '\udbff') {
                        ++var1;
                    }
            }
        }

        return var1;
    }
}

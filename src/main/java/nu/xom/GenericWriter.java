
package nu.xom;

import java.io.*;
import java.util.Locale;

class GenericWriter extends TextWriter {
    private final ByteArrayOutputStream bout = new ByteArrayOutputStream(32);
    private final OutputStreamWriter wout;
    private final boolean isJapanese;

    GenericWriter(Writer var1, String var2) throws UnsupportedEncodingException {
        super(var1, var2);
        this.wout = new OutputStreamWriter(this.bout, var2);
        var2 = var2.toUpperCase(Locale.ENGLISH);
        if (var2.indexOf("EUC-JP") <= -1 && !var2.startsWith("EUC_JP") && !var2.equals("SHIFT_JIS") && !var2.equals("SJIS") && !var2.equals("ISO-2022-JP")) {
            this.isJapanese = false;
        } else {
            this.isJapanese = true;
        }

    }

    boolean needsEscaping(char var1) {
        if (var1 <= 127) {
            return false;
        } else {
            if (this.isJapanese) {
                if (var1 == 165) {
                    return true;
                }

                if (var1 == 8254) {
                    return true;
                }
            }

            boolean var2 = false;

            boolean var4;
            try {
                this.wout.write(var1);
                this.wout.flush();
                byte[] var3 = this.bout.toByteArray();
                if (var3.length == 0) {
                    var2 = true;
                    return var2;
                } else {
                    if (var3[0] == 63) {
                        var2 = true;
                    } else if (this.isJapanese && var3[0] == 33) {
                        var2 = true;
                        return var2;
                    }

                    return var2;
                }
            } catch (IOException var9) {
                var4 = true;
            } catch (Error var10) {
                var4 = true;
                return var4;
            } finally {
                this.bout.reset();
            }

            return var4;
        }
    }
}
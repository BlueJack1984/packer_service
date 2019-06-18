package nu.xom;

import java.io.Writer;

class UCSWriter extends TextWriter {
    UCSWriter(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        if (var1 < '\ud800') {
            return false;
        } else {
            return var1 <= '\udfff';
        }
    }
}

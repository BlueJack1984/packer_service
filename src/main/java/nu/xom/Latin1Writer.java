package nu.xom;

import java.io.Writer;

class Latin1Writer extends TextWriter {
    Latin1Writer(Writer var1, String var2) {
        super(var1, var2);
    }

    boolean needsEscaping(char var1) {
        return var1 > 255;
    }
}


package nu.xom;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

class EBCDICWriter extends OutputStreamWriter {
    private static final int NEL = 133;
    private OutputStream raw;

    public EBCDICWriter(OutputStream var1) throws UnsupportedEncodingException {
        super(var1, "Cp037");
        this.raw = var1;
    }

    public void write(int var1) throws IOException {
        if (var1 == 133) {
            this.flush();
            this.raw.write(21);
        } else {
            super.write(var1);
        }

    }
}
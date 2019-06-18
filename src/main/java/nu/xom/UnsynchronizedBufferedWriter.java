package nu.xom;


import java.io.IOException;
import java.io.Writer;

final class UnsynchronizedBufferedWriter extends Writer {
    private static final int CAPACITY = 8192;
    private char[] buffer = new char[8192];
    private int position = 0;
    private Writer out;

    public UnsynchronizedBufferedWriter(Writer var1) {
        this.out = var1;
    }

    public void write(char[] var1, int var2, int var3) throws IOException {
        throw new UnsupportedOperationException("XOM bug: this statement shouldn't be reachable.");
    }

    public void write(String var1) throws IOException {
        this.write((String)var1, 0, var1.length());
    }

    public void write(String var1, int var2, int var3) throws IOException {
        while(var3 > 0) {
            int var4 = 8192 - this.position;
            if (var3 < var4) {
                var4 = var3;
            }

            var1.getChars(var2, var2 + var4, this.buffer, this.position);
            this.position += var4;
            var2 += var4;
            var3 -= var4;
            if (this.position >= 8192) {
                this.flushInternal();
            }
        }

    }

    public void write(int var1) throws IOException {
        if (this.position >= 8192) {
            this.flushInternal();
        }

        this.buffer[this.position] = (char)var1;
        ++this.position;
    }

    public void flush() throws IOException {
        this.flushInternal();
        this.out.flush();
    }

    private void flushInternal() throws IOException {
        if (this.position != 0) {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

    }

    public void close() throws IOException {
        throw new UnsupportedOperationException("How'd we get here?");
    }
}

package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.io.Serializable;
import java.util.Iterator;

public abstract class IterableAxis implements Serializable {
    private int value;

    public IterableAxis(int var1) {
        this.value = var1;
    }

    public int value() {
        return this.value;
    }

    public abstract Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException;

    public Iterator namedAccessIterator(Object var1, ContextSupport var2, String var3, String var4, String var5) throws UnsupportedAxisException {
        throw new UnsupportedOperationException("Named access unsupported");
    }

    public boolean supportsNamedAccess(ContextSupport var1) {
        return false;
    }
}

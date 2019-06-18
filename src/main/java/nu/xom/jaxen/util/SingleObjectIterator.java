package nu.xom.jaxen.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleObjectIterator implements Iterator {
    private Object object;
    private boolean seen;

    public SingleObjectIterator(Object var1) {
        this.object = var1;
        this.seen = false;
    }

    public boolean hasNext() {
        return !this.seen;
    }

    public Object next() {
        if (this.hasNext()) {
            this.seen = true;
            return this.object;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}


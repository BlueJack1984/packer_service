package nu.xom.jaxen.util;

import nu.xom.jaxen.JaxenRuntimeException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DescendantAxisIterator implements Iterator {
    private ArrayList stack;
    private Iterator children;
    private Navigator navigator;

    public DescendantAxisIterator(Object var1, Navigator var2) throws UnsupportedAxisException {
        this(var2, var2.getChildAxisIterator(var1));
    }

    public DescendantAxisIterator(Navigator var1, Iterator var2) {
        this.stack = new ArrayList();
        this.navigator = var1;
        this.children = var2;
    }

    public boolean hasNext() {
        while(!this.children.hasNext()) {
            if (this.stack.isEmpty()) {
                return false;
            }

            this.children = (Iterator)this.stack.remove(this.stack.size() - 1);
        }

        return true;
    }

    public Object next() {
        try {
            if (this.hasNext()) {
                Object var1 = this.children.next();
                this.stack.add(this.children);
                this.children = this.navigator.getChildAxisIterator(var1);
                return var1;
            } else {
                throw new NoSuchElementException();
            }
        } catch (UnsupportedAxisException var2) {
            throw new JaxenRuntimeException(var2);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

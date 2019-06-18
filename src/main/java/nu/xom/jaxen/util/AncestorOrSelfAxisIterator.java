package nu.xom.jaxen.util;


import nu.xom.jaxen.JaxenRuntimeException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AncestorOrSelfAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;

    public AncestorOrSelfAxisIterator(Object var1, Navigator var2) {
        this.contextNode = var1;
        this.navigator = var2;
    }

    public boolean hasNext() {
        return this.contextNode != null;
    }

    public Object next() {
        try {
            if (this.hasNext()) {
                Object var1 = this.contextNode;
                this.contextNode = this.navigator.getParentNode(this.contextNode);
                return var1;
            } else {
                throw new NoSuchElementException("Exhausted ancestor-or-self axis");
            }
        } catch (UnsupportedAxisException var2) {
            throw new JaxenRuntimeException(var2);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

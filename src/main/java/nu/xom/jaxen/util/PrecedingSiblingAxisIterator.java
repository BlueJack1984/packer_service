package nu.xom.jaxen.util;

import nu.xom.jaxen.JaxenConstants;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class PrecedingSiblingAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;
    private Iterator siblingIter;
    private Object nextObj;

    public PrecedingSiblingAxisIterator(Object var1, Navigator var2) throws UnsupportedAxisException {
        this.contextNode = var1;
        this.navigator = var2;
        this.init();
        if (this.siblingIter.hasNext()) {
            this.nextObj = this.siblingIter.next();
        }

    }

    private void init() throws UnsupportedAxisException {
        Object var1 = this.navigator.getParentNode(this.contextNode);
        if (var1 != null) {
            Iterator var2 = this.navigator.getChildAxisIterator(var1);
            LinkedList var3 = new LinkedList();

            while(var2.hasNext()) {
                Object var4 = var2.next();
                if (var4.equals(this.contextNode)) {
                    break;
                }

                var3.addFirst(var4);
            }

            this.siblingIter = var3.iterator();
        } else {
            this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
        }

    }

    public boolean hasNext() {
        return this.nextObj != null;
    }

    public Object next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            Object var1 = this.nextObj;
            if (this.siblingIter.hasNext()) {
                this.nextObj = this.siblingIter.next();
            } else {
                this.nextObj = null;
            }

            return var1;
        }
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

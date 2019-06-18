package nu.xom.jaxen.util;


import nu.xom.jaxen.JaxenConstants;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FollowingSiblingAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;
    private Iterator siblingIter;

    public FollowingSiblingAxisIterator(Object var1, Navigator var2) throws UnsupportedAxisException {
        this.contextNode = var1;
        this.navigator = var2;
        this.init();
    }

    private void init() throws UnsupportedAxisException {
        Object var1 = this.navigator.getParentNode(this.contextNode);
        if (var1 != null) {
            this.siblingIter = this.navigator.getChildAxisIterator(var1);

            while(this.siblingIter.hasNext()) {
                Object var2 = this.siblingIter.next();
                if (var2.equals(this.contextNode)) {
                    break;
                }
            }
        } else {
            this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
        }

    }

    public boolean hasNext() {
        return this.siblingIter.hasNext();
    }

    public Object next() throws NoSuchElementException {
        return this.siblingIter.next();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

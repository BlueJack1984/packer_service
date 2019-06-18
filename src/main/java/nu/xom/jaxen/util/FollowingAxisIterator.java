package nu.xom.jaxen.util;


import nu.xom.jaxen.JaxenConstants;
import nu.xom.jaxen.JaxenRuntimeException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FollowingAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;
    private Iterator siblings;
    private Iterator currentSibling;

    public FollowingAxisIterator(Object var1, Navigator var2) throws UnsupportedAxisException {
        this.contextNode = var1;
        this.navigator = var2;
        this.siblings = var2.getFollowingSiblingAxisIterator(var1);
        this.currentSibling = JaxenConstants.EMPTY_ITERATOR;
    }

    private boolean goForward() {
        while(true) {
            if (!this.siblings.hasNext()) {
                if (this.goUp()) {
                    continue;
                }

                return false;
            }

            Object var1 = this.siblings.next();
            this.currentSibling = new DescendantOrSelfAxisIterator(var1, this.navigator);
            return true;
        }
    }

    private boolean goUp() {
        if (this.contextNode != null && !this.navigator.isDocument(this.contextNode)) {
            try {
                this.contextNode = this.navigator.getParentNode(this.contextNode);
                if (this.contextNode != null && !this.navigator.isDocument(this.contextNode)) {
                    this.siblings = this.navigator.getFollowingSiblingAxisIterator(this.contextNode);
                    return true;
                } else {
                    return false;
                }
            } catch (UnsupportedAxisException var2) {
                throw new JaxenRuntimeException(var2);
            }
        } else {
            return false;
        }
    }

    public boolean hasNext() {
        while(true) {
            if (!this.currentSibling.hasNext()) {
                if (this.goForward()) {
                    continue;
                }

                return false;
            }

            return true;
        }
    }

    public Object next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            return this.currentSibling.next();
        }
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}


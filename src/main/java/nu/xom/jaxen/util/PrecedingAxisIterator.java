package nu.xom.jaxen.util;


import nu.xom.jaxen.JaxenConstants;
import nu.xom.jaxen.JaxenRuntimeException;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class PrecedingAxisIterator implements Iterator {
    private Iterator ancestorOrSelf;
    private Iterator precedingSibling;
    private ListIterator childrenOrSelf;
    private ArrayList stack;
    private Navigator navigator;

    public PrecedingAxisIterator(Object var1, Navigator var2) throws UnsupportedAxisException {
        this.navigator = var2;
        this.ancestorOrSelf = var2.getAncestorOrSelfAxisIterator(var1);
        this.precedingSibling = JaxenConstants.EMPTY_ITERATOR;
        this.childrenOrSelf = JaxenConstants.EMPTY_LIST_ITERATOR;
        this.stack = new ArrayList();
    }

    public boolean hasNext() {
        try {
            while(!this.childrenOrSelf.hasPrevious()) {
                if (this.stack.isEmpty()) {
                    Object var1;
                    while(!this.precedingSibling.hasNext()) {
                        if (!this.ancestorOrSelf.hasNext()) {
                            return false;
                        }

                        var1 = this.ancestorOrSelf.next();
                        this.precedingSibling = new PrecedingSiblingAxisIterator(var1, this.navigator);
                    }

                    var1 = this.precedingSibling.next();
                    this.childrenOrSelf = this.childrenOrSelf(var1);
                } else {
                    this.childrenOrSelf = (ListIterator)this.stack.remove(this.stack.size() - 1);
                }
            }

            return true;
        } catch (UnsupportedAxisException var2) {
            throw new JaxenRuntimeException(var2);
        }
    }

    private ListIterator childrenOrSelf(Object var1) {
        try {
            ArrayList var2 = new ArrayList();
            var2.add(var1);
            Iterator var3 = this.navigator.getChildAxisIterator(var1);
            if (var3 != null) {
                while(var3.hasNext()) {
                    var2.add(var3.next());
                }
            }

            return var2.listIterator(var2.size());
        } catch (UnsupportedAxisException var4) {
            throw new JaxenRuntimeException(var4);
        }
    }

    public Object next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            while(true) {
                Object var1 = this.childrenOrSelf.previous();
                if (!this.childrenOrSelf.hasPrevious()) {
                    return var1;
                }

                this.stack.add(this.childrenOrSelf);
                this.childrenOrSelf = this.childrenOrSelf(var1);
            }
        }
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}

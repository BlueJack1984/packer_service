package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableAncestorAxis extends IterableAxis {
    private static final long serialVersionUID = 1L;

    public IterableAncestorAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getAncestorAxisIterator(var1);
    }
}
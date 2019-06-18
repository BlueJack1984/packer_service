package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableAncestorOrSelfAxis extends IterableAxis {
    private static final long serialVersionUID = 1L;

    public IterableAncestorOrSelfAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getAncestorOrSelfAxisIterator(var1);
    }
}

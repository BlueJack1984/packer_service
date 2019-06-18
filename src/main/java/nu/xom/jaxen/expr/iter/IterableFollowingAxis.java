package nu.xom.jaxen.expr.iter;


import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableFollowingAxis extends IterableAxis {
    private static final long serialVersionUID = -7100245752300813209L;

    public IterableFollowingAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getFollowingAxisIterator(var1);
    }
}

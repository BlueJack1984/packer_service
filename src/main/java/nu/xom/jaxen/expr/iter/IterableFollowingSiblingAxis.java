package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableFollowingSiblingAxis extends IterableAxis {
    private static final long serialVersionUID = 4412705219546610009L;

    public IterableFollowingSiblingAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getFollowingSiblingAxisIterator(var1);
    }
}

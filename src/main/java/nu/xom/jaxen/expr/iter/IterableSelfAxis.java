package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableSelfAxis extends IterableAxis {
    private static final long serialVersionUID = 8292222516706760134L;

    public IterableSelfAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getSelfAxisIterator(var1);
    }
}

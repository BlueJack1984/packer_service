package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterablePrecedingAxis extends IterableAxis {
    private static final long serialVersionUID = 587333938258540052L;

    public IterablePrecedingAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getPrecedingAxisIterator(var1);
    }
}

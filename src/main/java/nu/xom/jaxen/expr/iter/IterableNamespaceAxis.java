package nu.xom.jaxen.expr.iter;


import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableNamespaceAxis extends IterableAxis {
    private static final long serialVersionUID = -8022585664651357087L;

    public IterableNamespaceAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getNamespaceAxisIterator(var1);
    }
}
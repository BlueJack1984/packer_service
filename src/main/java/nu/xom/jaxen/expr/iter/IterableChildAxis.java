package nu.xom.jaxen.expr.iter;

import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.NamedAccessNavigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;

public class IterableChildAxis extends IterableAxis {
    private static final long serialVersionUID = 1L;

    public IterableChildAxis(int var1) {
        super(var1);
    }

    public Iterator iterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return var2.getNavigator().getChildAxisIterator(var1);
    }

    public Iterator namedAccessIterator(Object var1, ContextSupport var2, String var3, String var4, String var5) throws UnsupportedAxisException {
        NamedAccessNavigator var6 = (NamedAccessNavigator)var2.getNavigator();
        return var6.getChildAxisIterator(var1, var3, var4, var5);
    }

    public boolean supportsNamedAccess(ContextSupport var1) {
        return var1.getNavigator() instanceof NamedAccessNavigator;
    }
}

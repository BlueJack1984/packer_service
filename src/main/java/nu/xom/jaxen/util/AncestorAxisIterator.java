package nu.xom.jaxen.util;

import nu.xom.jaxen.Navigator;

public class AncestorAxisIterator extends AncestorOrSelfAxisIterator {
    public AncestorAxisIterator(Object var1, Navigator var2) {
        super(var1, var2);
        this.next();
    }
}


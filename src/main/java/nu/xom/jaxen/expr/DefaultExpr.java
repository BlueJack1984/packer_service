package nu.xom.jaxen.expr;


import nu.xom.jaxen.util.SingleObjectIterator;
import nu.xom.jaxen.util.SingletonList;

import java.util.Iterator;
import java.util.List;

/** @deprecated */
public abstract class DefaultExpr implements Expr {
    public DefaultExpr() {
    }

    public Expr simplify() {
        return this;
    }

    public static Iterator convertToIterator(Object var0) {
        if (var0 instanceof Iterator) {
            return (Iterator)var0;
        } else {
            return (Iterator)(var0 instanceof List ? ((List)var0).iterator() : new SingleObjectIterator(var0));
        }
    }

    public static List convertToList(Object var0) {
        return (List)(var0 instanceof List ? (List)var0 : new SingletonList(var0));
    }
}

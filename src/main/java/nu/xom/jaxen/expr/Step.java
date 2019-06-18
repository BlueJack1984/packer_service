package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Iterator;
import java.util.List;

public interface Step extends Predicated {
    boolean matches(Object var1, ContextSupport var2) throws JaxenException;

    String getText();

    void simplify();

    int getAxis();

    Iterator axisIterator(Object var1, ContextSupport var2) throws UnsupportedAxisException;

    List evaluate(Context var1) throws JaxenException;
}


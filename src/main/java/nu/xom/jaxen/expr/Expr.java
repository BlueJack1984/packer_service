package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

import java.io.Serializable;

public interface Expr extends Serializable {
    String getText();

    nu.xom.jaxen.expr.Expr simplify();

    Object evaluate(Context var1) throws JaxenException;
}

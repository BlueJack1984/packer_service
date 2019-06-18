package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

public interface FilterExpr extends Expr, Predicated {
    boolean asBoolean(Context var1) throws JaxenException;

    Expr getExpr();
}

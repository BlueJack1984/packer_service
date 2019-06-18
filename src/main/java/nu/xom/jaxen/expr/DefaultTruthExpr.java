package nu.xom.jaxen.expr;


import java.util.List;

abstract class DefaultTruthExpr extends nu.xom.jaxen.expr.DefaultBinaryExpr {
    DefaultTruthExpr(Expr var1, Expr var2) {
        super(var1, var2);
    }

    public String toString() {
        return "[(DefaultTruthExpr): " + this.getLHS() + ", " + this.getRHS() + "]";
    }

    protected boolean bothAreSets(Object var1, Object var2) {
        return var1 instanceof List && var2 instanceof List;
    }

    protected boolean eitherIsSet(Object var1, Object var2) {
        return var1 instanceof List || var2 instanceof List;
    }

    protected boolean isSet(Object var1) {
        return var1 instanceof List;
    }

    protected boolean isBoolean(Object var1) {
        return var1 instanceof Boolean;
    }

    protected boolean setIsEmpty(List var1) {
        return var1 == null || var1.size() == 0;
    }

    protected boolean eitherIsBoolean(Object var1, Object var2) {
        return var1 instanceof Boolean || var2 instanceof Boolean;
    }

    protected boolean bothAreBoolean(Object var1, Object var2) {
        return var1 instanceof Boolean && var2 instanceof Boolean;
    }

    protected boolean eitherIsNumber(Object var1, Object var2) {
        return var1 instanceof Number || var2 instanceof Number;
    }
}

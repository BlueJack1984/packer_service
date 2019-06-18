package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.JaxenException;

import java.util.ArrayList;
import java.util.List;

/** @deprecated */
public class DefaultFilterExpr extends DefaultExpr implements FilterExpr, Predicated {
    private static final long serialVersionUID = -549640659288005735L;
    private Expr expr;
    private PredicateSet predicates;

    public DefaultFilterExpr(PredicateSet var1) {
        this.predicates = var1;
    }

    public DefaultFilterExpr(Expr var1, PredicateSet var2) {
        this.expr = var1;
        this.predicates = var2;
    }

    public void addPredicate(Predicate var1) {
        this.predicates.addPredicate(var1);
    }

    public List getPredicates() {
        return this.predicates.getPredicates();
    }

    public PredicateSet getPredicateSet() {
        return this.predicates;
    }

    public Expr getExpr() {
        return this.expr;
    }

    public String toString() {
        return "[(DefaultFilterExpr): expr: " + this.expr + " predicates: " + this.predicates + " ]";
    }

    public String getText() {
        String var1 = "";
        if (this.expr != null) {
            var1 = this.expr.getText();
        }

        var1 = var1 + this.predicates.getText();
        return var1;
    }

    public Expr simplify() {
        this.predicates.simplify();
        if (this.expr != null) {
            this.expr = this.expr.simplify();
        }

        return (Expr)(this.predicates.getPredicates().size() == 0 ? this.getExpr() : this);
    }

    public boolean asBoolean(Context var1) throws JaxenException {
        Object var2 = null;
        if (this.expr != null) {
            var2 = this.expr.evaluate(var1);
        } else {
            List var3 = var1.getNodeSet();
            ArrayList var4 = new ArrayList(var3.size());
            var4.addAll(var3);
            var2 = var4;
        }

        if (var2 instanceof Boolean) {
            Boolean var5 = (Boolean)var2;
            return var5;
        } else {
            return var2 instanceof List ? this.getPredicateSet().evaluateAsBoolean((List)var2, var1.getContextSupport()) : false;
        }
    }

    public Object evaluate(Context var1) throws JaxenException {
        Object var2 = this.getExpr().evaluate(var1);
        if (var2 instanceof List) {
            List var3 = this.getPredicateSet().evaluatePredicates((List)var2, var1.getContextSupport());
            var2 = var3;
        }

        return var2;
    }
}

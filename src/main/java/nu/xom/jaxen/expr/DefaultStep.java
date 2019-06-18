package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.UnsupportedAxisException;
import nu.xom.jaxen.expr.iter.IterableAxis;
import nu.xom.jaxen.saxpath.Axis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** @deprecated */
public abstract class DefaultStep implements Step {
    private IterableAxis axis;
    private PredicateSet predicates;

    public DefaultStep(IterableAxis var1, PredicateSet var2) {
        this.axis = var1;
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

    public int getAxis() {
        return this.axis.value();
    }

    public IterableAxis getIterableAxis() {
        return this.axis;
    }

    public String getAxisName() {
        return Axis.lookup(this.getAxis());
    }

    public String getText() {
        return this.predicates.getText();
    }

    public String toString() {
        return this.getIterableAxis() + " " + super.toString();
    }

    public void simplify() {
        this.predicates.simplify();
    }

    public Iterator axisIterator(Object var1, ContextSupport var2) throws UnsupportedAxisException {
        return this.getIterableAxis().iterator(var1, var2);
    }

    public List evaluate(Context var1) throws JaxenException {
        List var2 = var1.getNodeSet();
        IdentitySet var3 = new IdentitySet();
        int var4 = var2.size();
        ArrayList var5 = new ArrayList();
        ArrayList var6 = new ArrayList();
        ContextSupport var7 = var1.getContextSupport();

        for(int var8 = 0; var8 < var4; ++var8) {
            Object var9 = var2.get(var8);
            Iterator var10 = this.axis.iterator(var9, var7);

            while(var10.hasNext()) {
                Object var11 = var10.next();
                if (!var3.contains(var11) && this.matches(var11, var7)) {
                    var3.add(var11);
                    var5.add(var11);
                }
            }

            var6.addAll(this.getPredicateSet().evaluatePredicates(var5, var7));
            var5.clear();
        }

        return var6;
    }
}


package nu.xom.jaxen.expr;

import nu.xom.jaxen.Context;
import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.function.BooleanFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PredicateSet implements Serializable {
    private static final long serialVersionUID = -7166491740228977853L;
    private List predicates;

    public PredicateSet() {
        this.predicates = Collections.EMPTY_LIST;
    }

    public void addPredicate(Predicate var1) {
        if (this.predicates == Collections.EMPTY_LIST) {
            this.predicates = new ArrayList();
        }

        this.predicates.add(var1);
    }

    public List getPredicates() {
        return this.predicates;
    }

    public void simplify() {
        Iterator var1 = this.predicates.iterator();
        Predicate var2 = null;

        while(var1.hasNext()) {
            var2 = (Predicate)var1.next();
            var2.simplify();
        }

    }

    public String getText() {
        StringBuffer var1 = new StringBuffer();
        Iterator var2 = this.predicates.iterator();
        Predicate var3 = null;

        while(var2.hasNext()) {
            var3 = (Predicate)var2.next();
            var1.append(var3.getText());
        }

        return var1.toString();
    }

    protected boolean evaluateAsBoolean(List var1, ContextSupport var2) throws JaxenException {
        return this.anyMatchingNode(var1, var2);
    }

    private boolean anyMatchingNode(List var1, ContextSupport var2) throws JaxenException {
        if (this.predicates.size() == 0) {
            return false;
        } else {
            Iterator var3 = this.predicates.iterator();
            List var4 = var1;

            while(var3.hasNext()) {
                int var5 = var4.size();
                Context var6 = new Context(var2);
                ArrayList var7 = new ArrayList(1);
                var6.setNodeSet(var7);

                for(int var8 = 0; var8 < var5; ++var8) {
                    Object var9 = var4.get(var8);
                    var7.clear();
                    var7.add(var9);
                    var6.setNodeSet(var7);
                    var6.setPosition(var8 + 1);
                    var6.setSize(var5);
                    Object var10 = ((Predicate)var3.next()).evaluate(var6);
                    if (var10 instanceof Number) {
                        int var11 = ((Number)var10).intValue();
                        if (var11 == var8 + 1) {
                            return true;
                        }
                    } else {
                        Boolean var12 = BooleanFunction.evaluate(var10, var6.getNavigator());
                        if (var12) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    protected List evaluatePredicates(List var1, ContextSupport var2) throws JaxenException {
        if (this.predicates.size() == 0) {
            return var1;
        } else {
            Iterator var3 = this.predicates.iterator();

            List var4;
            for(var4 = var1; var3.hasNext(); var4 = this.applyPredicate((Predicate)var3.next(), var4, var2)) {
            }

            return var4;
        }
    }

    public List applyPredicate(Predicate var1, List var2, ContextSupport var3) throws JaxenException {
        int var4 = var2.size();
        ArrayList var5 = new ArrayList(var4);
        Context var6 = new Context(var3);
        ArrayList var7 = new ArrayList(1);
        var6.setNodeSet(var7);

        for(int var8 = 0; var8 < var4; ++var8) {
            Object var9 = var2.get(var8);
            var7.clear();
            var7.add(var9);
            var6.setNodeSet(var7);
            var6.setPosition(var8 + 1);
            var6.setSize(var4);
            Object var10 = var1.evaluate(var6);
            if (var10 instanceof Number) {
                int var11 = ((Number)var10).intValue();
                if (var11 == var8 + 1) {
                    var5.add(var9);
                }
            } else {
                Boolean var12 = BooleanFunction.evaluate(var10, var6.getNavigator());
                if (var12) {
                    var5.add(var9);
                }
            }
        }

        return var5;
    }
}

package nu.xom.jaxen.expr;


import nu.xom.jaxen.Context;
import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.JaxenException;

import java.util.*;

abstract class DefaultLocationPath extends DefaultExpr implements LocationPath {
    private List steps = new LinkedList();

    DefaultLocationPath() {
    }

    public void addStep(Step var1) {
        this.getSteps().add(var1);
    }

    public List getSteps() {
        return this.steps;
    }

    public Expr simplify() {
        Iterator var1 = this.getSteps().iterator();
        Step var2 = null;

        while(var1.hasNext()) {
            var2 = (Step)var1.next();
            var2.simplify();
        }

        return this;
    }

    public String getText() {
        StringBuffer var1 = new StringBuffer();
        Iterator var2 = this.getSteps().iterator();

        while(var2.hasNext()) {
            var1.append(((Step)var2.next()).getText());
            if (var2.hasNext()) {
                var1.append("/");
            }
        }

        return var1.toString();
    }

    public String toString() {
        StringBuffer var1 = new StringBuffer();
        Iterator var2 = this.getSteps().iterator();

        while(var2.hasNext()) {
            var1.append(var2.next().toString());
            if (var2.hasNext()) {
                var1.append("/");
            }
        }

        return var1.toString();
    }

    public boolean isAbsolute() {
        return false;
    }

    public Object evaluate(Context var1) throws JaxenException {
        List var2 = var1.getNodeSet();
        Object var3 = new ArrayList(var2);
        ContextSupport var4 = var1.getContextSupport();
        Context var5 = new Context(var4);
        Iterator var6 = this.getSteps().iterator();

        while(var6.hasNext()) {
            Step var7 = (Step)var6.next();
            var5.setNodeSet((List)var3);
            var3 = var7.evaluate(var5);
            if (this.isReverseAxis(var7)) {
                Collections.reverse((List)var3);
            }
        }

        if (this.getSteps().size() > 1 || var2.size() > 1) {
            Collections.sort((List)var3, new NodeComparator(var4.getNavigator()));
        }

        return var3;
    }

    private boolean isReverseAxis(Step var1) {
        int var2 = var1.getAxis();
        return var2 == 8 || var2 == 6 || var2 == 4 || var2 == 13;
    }
}

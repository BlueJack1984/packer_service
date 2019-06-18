package nu.xom.jaxen.expr;


import nu.xom.jaxen.ContextSupport;
import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.expr.iter.IterableAxis;

/** @deprecated */
public class DefaultProcessingInstructionNodeStep extends DefaultStep implements ProcessingInstructionNodeStep {
    private static final long serialVersionUID = -4825000697808126927L;
    private String name;

    public DefaultProcessingInstructionNodeStep(IterableAxis var1, String var2, PredicateSet var3) {
        super(var1, var3);
        this.name = var2;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        StringBuffer var1 = new StringBuffer();
        var1.append(this.getAxisName());
        var1.append("::processing-instruction(");
        String var2 = this.getName();
        if (var2 != null && var2.length() != 0) {
            var1.append("'");
            var1.append(var2);
            var1.append("'");
        }

        var1.append(")");
        var1.append(super.getText());
        return var1.toString();
    }

    public boolean matches(Object var1, ContextSupport var2) {
        Navigator var3 = var2.getNavigator();
        if (var3.isProcessingInstruction(var1)) {
            String var4 = this.getName();
            return var4 != null && var4.length() != 0 ? var4.equals(var3.getProcessingInstructionTarget(var1)) : true;
        } else {
            return false;
        }
    }
}


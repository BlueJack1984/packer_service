package nu.xom.jaxen;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleVariableContext implements VariableContext, Serializable {
    private static final long serialVersionUID = 961322093794516518L;
    private Map variables = new HashMap();

    public SimpleVariableContext() {
    }

    public void setVariableValue(String var1, String var2, Object var3) {
        this.variables.put(new QualifiedName(var1, var2), var3);
    }

    public void setVariableValue(String var1, Object var2) {
        this.variables.put(new QualifiedName((String)null, var1), var2);
    }

    public Object getVariableValue(String var1, String var2, String var3) throws UnresolvableException {
        QualifiedName var4 = new QualifiedName(var1, var3);
        if (this.variables.containsKey(var4)) {
            return this.variables.get(var4);
        } else {
            throw new UnresolvableException("Variable " + var4.getClarkForm());
        }
    }
}

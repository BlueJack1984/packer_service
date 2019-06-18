package nu.xom.jaxen;


import java.util.HashMap;

public class SimpleFunctionContext implements FunctionContext {
    private HashMap functions = new HashMap();

    public SimpleFunctionContext() {
    }

    public void registerFunction(String var1, String var2, Function var3) {
        this.functions.put(new nu.xom.jaxen.QualifiedName(var1, var2), var3);
    }

    public Function getFunction(String var1, String var2, String var3) throws UnresolvableException {
        nu.xom.jaxen.QualifiedName var4 = new nu.xom.jaxen.QualifiedName(var1, var3);
        if (this.functions.containsKey(var4)) {
            return (Function)this.functions.get(var4);
        } else {
            throw new UnresolvableException("No Such Function " + var4.getClarkForm());
        }
    }
}

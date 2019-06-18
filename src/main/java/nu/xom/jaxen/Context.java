package nu.xom.jaxen;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Context implements Serializable {
    private static final long serialVersionUID = 2315979994685591055L;
    private ContextSupport contextSupport;
    private List nodeSet;
    private int size;
    private int position;

    public Context(ContextSupport var1) {
        this.contextSupport = var1;
        this.nodeSet = Collections.EMPTY_LIST;
        this.size = 0;
        this.position = 0;
    }

    public void setNodeSet(List var1) {
        this.nodeSet = var1;
        this.size = var1.size();
        if (this.position >= this.size) {
            this.position = 0;
        }

    }

    public List getNodeSet() {
        return this.nodeSet;
    }

    public void setContextSupport(ContextSupport var1) {
        this.contextSupport = var1;
    }

    public ContextSupport getContextSupport() {
        return this.contextSupport;
    }

    public Navigator getNavigator() {
        return this.getContextSupport().getNavigator();
    }

    public String translateNamespacePrefixToUri(String var1) {
        return this.getContextSupport().translateNamespacePrefixToUri(var1);
    }

    public Object getVariableValue(String var1, String var2, String var3) throws UnresolvableException {
        return this.getContextSupport().getVariableValue(var1, var2, var3);
    }

    public Function getFunction(String var1, String var2, String var3) throws UnresolvableException {
        return this.getContextSupport().getFunction(var1, var2, var3);
    }

    public void setSize(int var1) {
        this.size = var1;
    }

    public int getSize() {
        return this.size;
    }

    public void setPosition(int var1) {
        this.position = var1;
    }

    public int getPosition() {
        return this.position;
    }

    public nu.xom.jaxen.Context duplicate() {
        nu.xom.jaxen.Context var1 = new nu.xom.jaxen.Context(this.getContextSupport());
        List var2 = this.getNodeSet();
        if (var2 != null) {
            ArrayList var3 = new ArrayList(var2.size());
            var3.addAll(var2);
            var1.setNodeSet(var3);
            var1.setPosition(this.position);
        }

        return var1;
    }
}

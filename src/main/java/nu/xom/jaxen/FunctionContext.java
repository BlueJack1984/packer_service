package nu.xom.jaxen;

public interface FunctionContext {
    Function getFunction(String var1, String var2, String var3) throws UnresolvableException;
}

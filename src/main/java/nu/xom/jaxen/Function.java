package nu.xom.jaxen;

import java.util.List;

public interface Function {
    Object call(Context var1, List var2) throws FunctionCallException;
}

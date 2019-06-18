package nu.xom.jaxen.util;
import nu.xom.jaxen.Navigator;

public class DescendantOrSelfAxisIterator extends DescendantAxisIterator {
    public DescendantOrSelfAxisIterator(Object var1, Navigator var2) {
        super(var2, new SingleObjectIterator(var1));
    }
}


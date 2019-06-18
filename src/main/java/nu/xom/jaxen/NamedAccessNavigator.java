package nu.xom.jaxen;

import java.util.Iterator;

public interface NamedAccessNavigator extends Navigator {
    Iterator getChildAxisIterator(Object var1, String var2, String var3, String var4) throws UnsupportedAxisException;

    Iterator getAttributeAxisIterator(Object var1, String var2, String var3, String var4) throws UnsupportedAxisException;
}

package nu.xom.jaxen;

import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public class JaxenConstants {
    public static final Iterator EMPTY_ITERATOR;
    public static final ListIterator EMPTY_LIST_ITERATOR;

    private JaxenConstants() {
    }

    static {
        EMPTY_ITERATOR = Collections.EMPTY_LIST.iterator();
        EMPTY_LIST_ITERATOR = Collections.EMPTY_LIST.listIterator();
    }
}

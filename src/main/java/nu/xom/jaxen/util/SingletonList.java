package nu.xom.jaxen.util;

import java.util.AbstractList;

public class SingletonList extends AbstractList {
    private final Object element;

    public SingletonList(Object var1) {
        this.element = var1;
    }

    public int size() {
        return 1;
    }

    public Object get(int var1) {
        if (var1 == 0) {
            return this.element;
        } else {
            throw new IndexOutOfBoundsException(var1 + " != 0");
        }
    }
}
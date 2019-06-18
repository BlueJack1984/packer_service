package nu.xom;


import java.util.ArrayList;
import java.util.List;

public final class Elements {
    private List elements = new ArrayList(1);

    Elements() {
    }

    public int size() {
        return this.elements.size();
    }

    public Element get(int var1) {
        return (Element)this.elements.get(var1);
    }

    void add(Element var1) {
        this.elements.add(var1);
    }
}
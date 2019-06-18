package nu.xom;


import java.util.ArrayList;
import java.util.List;

public final class Nodes {
    private final List nodes;

    public Nodes() {
        this.nodes = new ArrayList();
    }

    public Nodes(Node var1) {
        if (var1 == null) {
            throw new NullPointerException("Nodes content must be non-null");
        } else {
            this.nodes = new ArrayList(1);
            this.nodes.add(var1);
        }
    }

    Nodes(List var1) {
        this.nodes = var1;
    }

    public int size() {
        return this.nodes.size();
    }

    public Node get(int var1) {
        return (Node)this.nodes.get(var1);
    }

    public Node remove(int var1) {
        return (Node)this.nodes.remove(var1);
    }

    public void insert(Node var1, int var2) {
        if (var1 == null) {
            throw new NullPointerException("Nodes content must be non-null");
        } else {
            this.nodes.add(var2, var1);
        }
    }

    public void append(Node var1) {
        if (var1 == null) {
            throw new NullPointerException("Nodes content must be non-null");
        } else {
            this.nodes.add(var1);
        }
    }

    public boolean contains(Node var1) {
        return this.nodes.contains(var1);
    }
}

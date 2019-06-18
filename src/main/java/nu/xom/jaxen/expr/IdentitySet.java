package nu.xom.jaxen.expr;

import java.util.HashSet;

final class IdentitySet {
    private HashSet contents = new HashSet();

    IdentitySet() {
    }

    void add(Object var1) {
        nu.xom.jaxen.expr.IdentitySet.IdentityWrapper var2 = new nu.xom.jaxen.expr.IdentitySet.IdentityWrapper(var1);
        this.contents.add(var2);
    }

    public boolean contains(Object var1) {
        nu.xom.jaxen.expr.IdentitySet.IdentityWrapper var2 = new nu.xom.jaxen.expr.IdentitySet.IdentityWrapper(var1);
        return this.contents.contains(var2);
    }

    private static class IdentityWrapper {
        private Object object;

        IdentityWrapper(Object var1) {
            this.object = var1;
        }

        public boolean equals(Object var1) {
            nu.xom.jaxen.expr.IdentitySet.IdentityWrapper var2 = (nu.xom.jaxen.expr.IdentitySet.IdentityWrapper)var1;
            return this.object == var2.object;
        }

        public int hashCode() {
            return System.identityHashCode(this.object);
        }
    }
}


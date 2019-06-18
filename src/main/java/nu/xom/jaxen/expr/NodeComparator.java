package nu.xom.jaxen.expr;

import nu.xom.jaxen.Navigator;
import nu.xom.jaxen.UnsupportedAxisException;

import java.util.Comparator;
import java.util.Iterator;

class NodeComparator implements Comparator {
    private Navigator navigator;

    NodeComparator(Navigator var1) {
        this.navigator = var1;
    }

    public int compare(Object var1, Object var2) {
        if (var1 == var2) {
            return 0;
        } else if (this.navigator == null) {
            return 0;
        } else if (this.isNonChild(var1) && this.isNonChild(var2)) {
            try {
                Object var11 = this.navigator.getParentNode(var1);
                Object var12 = this.navigator.getParentNode(var2);
                if (var11 == var12) {
                    if (this.navigator.isNamespace(var1) && this.navigator.isAttribute(var2)) {
                        return -1;
                    }

                    if (this.navigator.isNamespace(var2) && this.navigator.isAttribute(var1)) {
                        return 1;
                    }

                    String var13;
                    String var14;
                    if (this.navigator.isNamespace(var1)) {
                        var13 = this.navigator.getNamespacePrefix(var1);
                        var14 = this.navigator.getNamespacePrefix(var2);
                        return var13.compareTo(var14);
                    }

                    if (this.navigator.isAttribute(var1)) {
                        var13 = this.navigator.getAttributeQName(var1);
                        var14 = this.navigator.getAttributeQName(var2);
                        return var13.compareTo(var14);
                    }
                }

                return this.compare(var11, var12);
            } catch (UnsupportedAxisException var9) {
                return 0;
            }
        } else {
            try {
                int var3 = this.getDepth(var1);
                int var4 = this.getDepth(var2);
                Object var5 = var1;

                Object var6;
                for(var6 = var2; var3 > var4; --var3) {
                    var5 = this.navigator.getParentNode(var5);
                }

                if (var5 == var2) {
                    return 1;
                } else {
                    while(var4 > var3) {
                        var6 = this.navigator.getParentNode(var6);
                        --var4;
                    }

                    if (var6 == var1) {
                        return -1;
                    } else {
                        while(true) {
                            Object var7 = this.navigator.getParentNode(var5);
                            Object var8 = this.navigator.getParentNode(var6);
                            if (var7 == var8) {
                                return this.compareSiblings(var5, var6);
                            }

                            var5 = var7;
                            var6 = var8;
                        }
                    }
                }
            } catch (UnsupportedAxisException var10) {
                return 0;
            }
        }
    }

    private boolean isNonChild(Object var1) {
        return this.navigator.isAttribute(var1) || this.navigator.isNamespace(var1);
    }

    private int compareSiblings(Object var1, Object var2) throws UnsupportedAxisException {
        if (this.isNonChild(var1)) {
            return 1;
        } else if (this.isNonChild(var2)) {
            return -1;
        } else {
            Iterator var3 = this.navigator.getFollowingSiblingAxisIterator(var1);

            Object var4;
            do {
                if (!var3.hasNext()) {
                    return 1;
                }

                var4 = var3.next();
            } while(!var4.equals(var2));

            return -1;
        }
    }

    private int getDepth(Object var1) throws UnsupportedAxisException {
        int var2 = 0;

        for(Object var3 = var1; (var3 = this.navigator.getParentNode(var3)) != null; ++var2) {
        }

        return var2;
    }
}

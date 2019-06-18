package nu.xom.jaxen.expr;

import nu.xom.jaxen.*;
import nu.xom.jaxen.expr.iter.IterableAxis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/** @deprecated */
public class DefaultNameStep extends DefaultStep implements NameStep {
    private static final long serialVersionUID = 428414912247718390L;
    private String prefix;
    private String localName;
    private boolean matchesAnyName;
    private boolean hasPrefix;

    public DefaultNameStep(IterableAxis var1, String var2, String var3, PredicateSet var4) {
        super(var1, var4);
        this.prefix = var2;
        this.localName = var3;
        this.matchesAnyName = "*".equals(var3);
        this.hasPrefix = this.prefix != null && this.prefix.length() > 0;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getLocalName() {
        return this.localName;
    }

    public boolean isMatchesAnyName() {
        return this.matchesAnyName;
    }

    public String getText() {
        StringBuffer var1 = new StringBuffer(64);
        var1.append(this.getAxisName()).append("::");
        if (this.getPrefix() != null && this.getPrefix().length() > 0) {
            var1.append(this.getPrefix()).append(':');
        }

        return var1.append(this.getLocalName()).append(super.getText()).toString();
    }

    public List evaluate(Context var1) throws JaxenException {
        List var2 = var1.getNodeSet();
        int var3 = var2.size();
        if (var3 == 0) {
            return Collections.EMPTY_LIST;
        } else {
            ContextSupport var4 = var1.getContextSupport();
            IterableAxis var5 = this.getIterableAxis();
            boolean var6 = !this.matchesAnyName && var5.supportsNamedAccess(var4);
            ArrayList var9;
            if (var3 == 1) {
                Object var17 = var2.get(0);
                if (var6) {
                    String var19 = null;
                    if (this.hasPrefix) {
                        var19 = var4.translateNamespacePrefixToUri(this.prefix);
                        if (var19 == null) {
                            throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
                        }
                    }

                    Iterator var20 = var5.namedAccessIterator(var17, var4, this.localName, this.prefix, var19);
                    if (var20 != null && var20.hasNext()) {
                        ArrayList var24 = new ArrayList();

                        while(var20.hasNext()) {
                            var24.add(var20.next());
                        }

                        return this.getPredicateSet().evaluatePredicates(var24, var4);
                    } else {
                        return Collections.EMPTY_LIST;
                    }
                } else {
                    Iterator var18 = var5.iterator(var17, var4);
                    if (var18 != null && var18.hasNext()) {
                        var9 = new ArrayList(var3);

                        while(var18.hasNext()) {
                            Object var23 = var18.next();
                            if (this.matches(var23, var4)) {
                                var9.add(var23);
                            }
                        }

                        return this.getPredicateSet().evaluatePredicates(var9, var4);
                    } else {
                        return Collections.EMPTY_LIST;
                    }
                }
            } else {
                IdentitySet var7 = new IdentitySet();
                ArrayList var8 = new ArrayList(var3);
                var9 = new ArrayList(var3);
                if (var6) {
                    String var10 = null;
                    if (this.hasPrefix) {
                        var10 = var4.translateNamespacePrefixToUri(this.prefix);
                        if (var10 == null) {
                            throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
                        }
                    }

                    for(int var11 = 0; var11 < var3; ++var11) {
                        Object var12 = var2.get(var11);
                        Iterator var13 = var5.namedAccessIterator(var12, var4, this.localName, this.prefix, var10);
                        if (var13 != null && var13.hasNext()) {
                            while(var13.hasNext()) {
                                Object var14 = var13.next();
                                var8.add(var14);
                            }

                            List var28 = this.getPredicateSet().evaluatePredicates(var8, var4);
                            Iterator var15 = var28.iterator();

                            while(var15.hasNext()) {
                                Object var16 = var15.next();
                                if (!var7.contains(var16)) {
                                    var7.add(var16);
                                    var9.add(var16);
                                }
                            }

                            var8.clear();
                        }
                    }
                } else {
                    for(int var21 = 0; var21 < var3; ++var21) {
                        Object var22 = var2.get(var21);
                        Iterator var25 = this.axisIterator(var22, var4);
                        if (var25 != null && var25.hasNext()) {
                            while(var25.hasNext()) {
                                Object var26 = var25.next();
                                if (this.matches(var26, var4)) {
                                    var8.add(var26);
                                }
                            }

                            List var27 = this.getPredicateSet().evaluatePredicates(var8, var4);
                            Iterator var29 = var27.iterator();

                            while(var29.hasNext()) {
                                Object var30 = var29.next();
                                if (!var7.contains(var30)) {
                                    var7.add(var30);
                                    var9.add(var30);
                                }
                            }

                            var8.clear();
                        }
                    }
                }

                return var9;
            }
        }
    }

    public boolean matches(Object var1, ContextSupport var2) throws JaxenException {
        Navigator var3 = var2.getNavigator();
        String var4 = null;
        String var5 = null;
        String var6 = null;
        if (var3.isElement(var1)) {
            var5 = var3.getElementName(var1);
            var6 = var3.getElementNamespaceUri(var1);
        } else {
            if (var3.isText(var1)) {
                return false;
            }

            if (var3.isAttribute(var1)) {
                if (this.getAxis() != 9) {
                    return false;
                }

                var5 = var3.getAttributeName(var1);
                var6 = var3.getAttributeNamespaceUri(var1);
            } else {
                if (var3.isDocument(var1)) {
                    return false;
                }

                if (!var3.isNamespace(var1)) {
                    return false;
                }

                if (this.getAxis() != 10) {
                    return false;
                }

                var5 = var3.getNamespacePrefix(var1);
            }
        }

        if (this.hasPrefix) {
            var4 = var2.translateNamespacePrefixToUri(this.prefix);
            if (var4 == null) {
                throw new UnresolvableException("Cannot resolve namespace prefix '" + this.prefix + "'");
            }
        } else if (this.matchesAnyName) {
            return true;
        }

        if (this.hasNamespace(var4) != this.hasNamespace(var6)) {
            return false;
        } else {
            return !this.matchesAnyName && !var5.equals(this.getLocalName()) ? false : this.matchesNamespaceURIs(var4, var6);
        }
    }

    private boolean hasNamespace(String var1) {
        return var1 != null && var1.length() > 0;
    }

    protected boolean matchesNamespaceURIs(String var1, String var2) {
        if (var1 == var2) {
            return true;
        } else if (var1 == null) {
            return var2.length() == 0;
        } else if (var2 == null) {
            return var1.length() == 0;
        } else {
            return var1.equals(var2);
        }
    }

    public String toString() {
        String var1 = this.getPrefix();
        String var2 = "".equals(var1) ? this.getLocalName() : this.getPrefix() + ":" + this.getLocalName();
        return "[(DefaultNameStep): " + var2 + "]";
    }
}


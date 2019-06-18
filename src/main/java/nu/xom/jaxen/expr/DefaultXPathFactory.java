package nu.xom.jaxen.expr;


import nu.xom.jaxen.JaxenException;
import nu.xom.jaxen.expr.iter.*;

public class DefaultXPathFactory implements XPathFactory {
    public DefaultXPathFactory() {
    }

    public XPathExpr createXPath(Expr var1) throws JaxenException {
        return new DefaultXPathExpr(var1);
    }

    public PathExpr createPathExpr(FilterExpr var1, LocationPath var2) throws JaxenException {
        return new DefaultPathExpr(var1, var2);
    }

    public LocationPath createRelativeLocationPath() throws JaxenException {
        return new DefaultRelativeLocationPath();
    }

    public LocationPath createAbsoluteLocationPath() throws JaxenException {
        return new DefaultAbsoluteLocationPath();
    }

    public BinaryExpr createOrExpr(Expr var1, Expr var2) throws JaxenException {
        return new DefaultOrExpr(var1, var2);
    }

    public BinaryExpr createAndExpr(Expr var1, Expr var2) throws JaxenException {
        return new DefaultAndExpr(var1, var2);
    }

    public BinaryExpr createEqualityExpr(Expr var1, Expr var2, int var3) throws JaxenException {
        switch(var3) {
            case 1:
                return new DefaultEqualsExpr(var1, var2);
            case 2:
                return new DefaultNotEqualsExpr(var1, var2);
            default:
                throw new JaxenException("Unhandled operator in createEqualityExpr(): " + var3);
        }
    }

    public BinaryExpr createRelationalExpr(Expr var1, Expr var2, int var3) throws JaxenException {
        switch(var3) {
            case 3:
                return new nu.xom.jaxen.expr.DefaultLessThanExpr(var1, var2);
            case 4:
                return new nu.xom.jaxen.expr.DefaultLessThanEqualExpr(var1, var2);
            case 5:
                return new DefaultGreaterThanExpr(var1, var2);
            case 6:
                return new DefaultGreaterThanEqualExpr(var1, var2);
            default:
                throw new JaxenException("Unhandled operator in createRelationalExpr(): " + var3);
        }
    }

    public BinaryExpr createAdditiveExpr(Expr var1, Expr var2, int var3) throws JaxenException {
        switch(var3) {
            case 7:
                return new DefaultPlusExpr(var1, var2);
            case 8:
                return new DefaultMinusExpr(var1, var2);
            default:
                throw new JaxenException("Unhandled operator in createAdditiveExpr(): " + var3);
        }
    }

    public BinaryExpr createMultiplicativeExpr(Expr var1, Expr var2, int var3) throws JaxenException {
        switch(var3) {
            case 9:
                return new DefaultMultiplyExpr(var1, var2);
            case 10:
                return new DefaultModExpr(var1, var2);
            case 11:
                return new DefaultDivExpr(var1, var2);
            default:
                throw new JaxenException("Unhandled operator in createMultiplicativeExpr(): " + var3);
        }
    }

    public Expr createUnaryExpr(Expr var1, int var2) throws JaxenException {
        switch(var2) {
            case 12:
                return new DefaultUnaryExpr(var1);
            default:
                return var1;
        }
    }

    public UnionExpr createUnionExpr(Expr var1, Expr var2) throws JaxenException {
        return new DefaultUnionExpr(var1, var2);
    }

    public FilterExpr createFilterExpr(Expr var1) throws JaxenException {
        return new DefaultFilterExpr(var1, this.createPredicateSet());
    }

    public FunctionCallExpr createFunctionCallExpr(String var1, String var2) throws JaxenException {
        return new DefaultFunctionCallExpr(var1, var2);
    }

    public NumberExpr createNumberExpr(int var1) throws JaxenException {
        return new DefaultNumberExpr(new Double((double)var1));
    }

    public NumberExpr createNumberExpr(double var1) throws JaxenException {
        return new DefaultNumberExpr(new Double(var1));
    }

    public LiteralExpr createLiteralExpr(String var1) throws JaxenException {
        return new DefaultLiteralExpr(var1);
    }

    public VariableReferenceExpr createVariableReferenceExpr(String var1, String var2) throws JaxenException {
        return new DefaultVariableReferenceExpr(var1, var2);
    }

    public Step createNameStep(int var1, String var2, String var3) throws JaxenException {
        IterableAxis var4 = this.getIterableAxis(var1);
        return new DefaultNameStep(var4, var2, var3, this.createPredicateSet());
    }

    public Step createTextNodeStep(int var1) throws JaxenException {
        IterableAxis var2 = this.getIterableAxis(var1);
        return new DefaultTextNodeStep(var2, this.createPredicateSet());
    }

    public Step createCommentNodeStep(int var1) throws JaxenException {
        IterableAxis var2 = this.getIterableAxis(var1);
        return new DefaultCommentNodeStep(var2, this.createPredicateSet());
    }

    public Step createAllNodeStep(int var1) throws JaxenException {
        IterableAxis var2 = this.getIterableAxis(var1);
        return new DefaultAllNodeStep(var2, this.createPredicateSet());
    }

    public Step createProcessingInstructionNodeStep(int var1, String var2) throws JaxenException {
        IterableAxis var3 = this.getIterableAxis(var1);
        return new DefaultProcessingInstructionNodeStep(var3, var2, this.createPredicateSet());
    }

    public Predicate createPredicate(Expr var1) throws JaxenException {
        return new DefaultPredicate(var1);
    }

    protected IterableAxis getIterableAxis(int var1) throws JaxenException {
        switch(var1) {
            case 1:
                return new IterableChildAxis(var1);
            case 2:
                return new IterableDescendantAxis(var1);
            case 3:
                return new IterableParentAxis(var1);
            case 4:
                return new IterableAncestorAxis(var1);
            case 5:
                return new IterableFollowingSiblingAxis(var1);
            case 6:
                return new IterablePrecedingSiblingAxis(var1);
            case 7:
                return new IterableFollowingAxis(var1);
            case 8:
                return new IterablePrecedingAxis(var1);
            case 9:
                return new IterableAttributeAxis(var1);
            case 10:
                return new IterableNamespaceAxis(var1);
            case 11:
                return new IterableSelfAxis(var1);
            case 12:
                return new IterableDescendantOrSelfAxis(var1);
            case 13:
                return new IterableAncestorOrSelfAxis(var1);
            default:
                throw new JaxenException("Unrecognized axis code: " + var1);
        }
    }

    public PredicateSet createPredicateSet() throws JaxenException {
        return new PredicateSet();
    }
}

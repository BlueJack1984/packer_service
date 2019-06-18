package nu.xom.jaxen.expr;


import nu.xom.jaxen.JaxenException;

public interface XPathFactory {
    XPathExpr createXPath(Expr var1) throws JaxenException;

    PathExpr createPathExpr(FilterExpr var1, LocationPath var2) throws JaxenException;

    LocationPath createRelativeLocationPath() throws JaxenException;

    LocationPath createAbsoluteLocationPath() throws JaxenException;

    BinaryExpr createOrExpr(Expr var1, Expr var2) throws JaxenException;

    BinaryExpr createAndExpr(Expr var1, Expr var2) throws JaxenException;

    BinaryExpr createEqualityExpr(Expr var1, Expr var2, int var3) throws JaxenException;

    BinaryExpr createRelationalExpr(Expr var1, Expr var2, int var3) throws JaxenException;

    BinaryExpr createAdditiveExpr(Expr var1, Expr var2, int var3) throws JaxenException;

    BinaryExpr createMultiplicativeExpr(Expr var1, Expr var2, int var3) throws JaxenException;

    Expr createUnaryExpr(Expr var1, int var2) throws JaxenException;

    UnionExpr createUnionExpr(Expr var1, Expr var2) throws JaxenException;

    FilterExpr createFilterExpr(Expr var1) throws JaxenException;

    FunctionCallExpr createFunctionCallExpr(String var1, String var2) throws JaxenException;

    NumberExpr createNumberExpr(int var1) throws JaxenException;

    NumberExpr createNumberExpr(double var1) throws JaxenException;

    LiteralExpr createLiteralExpr(String var1) throws JaxenException;

    VariableReferenceExpr createVariableReferenceExpr(String var1, String var2) throws JaxenException;

    Step createNameStep(int var1, String var2, String var3) throws JaxenException;

    Step createAllNodeStep(int var1) throws JaxenException;

    Step createCommentNodeStep(int var1) throws JaxenException;

    Step createTextNodeStep(int var1) throws JaxenException;

    Step createProcessingInstructionNodeStep(int var1, String var2) throws JaxenException;

    Predicate createPredicate(Expr var1) throws JaxenException;

    PredicateSet createPredicateSet() throws JaxenException;
}

package nu.xom.jaxen.expr;


public interface BinaryExpr extends Expr {
    Expr getLHS();

    Expr getRHS();

    String getOperator();
}

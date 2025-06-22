package ast;

public abstract class ExpressionNode implements Node {
    @Override
    public abstract void print(String indent);
}
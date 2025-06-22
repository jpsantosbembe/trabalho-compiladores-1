package ast;

import lexer.TokenType;

public class BinaryOpNode extends ExpressionNode {
    private final TokenType operator;
    private final ExpressionNode left;
    private final ExpressionNode right;

    public BinaryOpNode(TokenType operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public TokenType getOperator() {
        return operator;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "BINARY_OP: " + operator);
        left.print(indent + "  ");
        right.print(indent + "  ");
    }
}
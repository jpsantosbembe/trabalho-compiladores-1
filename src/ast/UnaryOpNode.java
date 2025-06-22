package ast;

import lexer.TokenType;

public class UnaryOpNode extends ExpressionNode {
    private final TokenType operator;
    private final ExpressionNode operand;

    public UnaryOpNode(TokenType operator, ExpressionNode operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public TokenType getOperator() {
        return operator;
    }

    public ExpressionNode getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return "(" + operator + " " + operand + ")";
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "UNARY_OP: " + operator);
        operand.print(indent + "  ");
    }
}

package ast;

public class NumberNode extends ExpressionNode {
    private final String value;

    public NumberNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "NUMBER: " + value);
    }
}

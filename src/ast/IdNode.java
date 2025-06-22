package ast;

public class IdNode extends ExpressionNode {
    private final String name;

    public IdNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "ID: '" + name + "'");
    }
}
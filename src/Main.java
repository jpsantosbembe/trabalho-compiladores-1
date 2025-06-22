import ast.ExpressionNode;
import lexer.Lexer;
import parser.Parser;
import parser.SyntaxError;

public class Main {
    public static void main(String[] args) {
        String expression = "a+b-c*d/e^(f*-g)^3";

            try {
                Lexer lexer = new Lexer(expression);
                Parser parser = new Parser(lexer);

                ExpressionNode ast = parser.parse();

                ast.print("");

            } catch (SyntaxError e) {
                System.err.println("ERRO DE SINTAXE: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("ERRO INESPERADO: " + e.getMessage());
                e.printStackTrace();
            }

    }
}
import ast.ExpressionNode;
import lexer.Lexer;
import parser.Parser;
import parser.SyntaxError;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma expressão: ");
        String expression = scanner.nextLine();

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
        } finally {
            scanner.close();
        }
    }
}
package parser;

import ast.*;
import lexer.Lexer;
import lexer.Token;
import lexer.TokenType;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    private void error(String message) {
        throw new SyntaxError("Erro de sintaxe na posição " + lexer.getPosition() + ": " + message +
                ". Token atual: " + currentToken.getType() + (currentToken.getValue().isEmpty() ? "" : " ('" + currentToken.getValue() + "')"));
    }

    private void match(TokenType expectedType) {
        if (currentToken.getType() == expectedType) {
            currentToken = lexer.getNextToken();
        } else {
            error("Esperava " + expectedType + ", mas encontrou " + currentToken.getType());
        }
    }

    // EXPR ::= TERM EXPR_PRIME
    public ExpressionNode parseExpression() {
        ExpressionNode node = parseTerm();
        return parseExpressionPrime(node);
    }

    // EXPR_PRIME ::= + TERM EXPR_PRIME | - TERM EXPR_PRIME | ε
    private ExpressionNode parseExpressionPrime(ExpressionNode leftNode) {
        while (currentToken.getType() == TokenType.PLUS ||
                currentToken.getType() == TokenType.MINUS) {

            TokenType opType = currentToken.getType();
            match(opType);

            ExpressionNode rightNode = parseTerm();

            leftNode = new BinaryOpNode(opType, leftNode, rightNode);
        }
        return leftNode;
    }

    // TERM ::= UNARY TERM_PRIME
    private ExpressionNode parseTerm() {
        ExpressionNode node = parseUnary();
        return parseTermPrime(node);
    }

    // TERM_PRIME ::= * UNARY TERM_PRIME | / UNARY TERM_PRIME | ε
    private ExpressionNode parseTermPrime(ExpressionNode leftNode) {
        while (currentToken.getType() == TokenType.STAR ||
                currentToken.getType() == TokenType.SLASH) {

            TokenType opType = currentToken.getType();
            match(opType);

            ExpressionNode rightNode = parseUnary();

            leftNode = new BinaryOpNode(opType, leftNode, rightNode);
        }
        return leftNode;
    }

    // UNARY ::= + UNARY | - UNARY | POW
    private ExpressionNode parseUnary() {
        if (currentToken.getType() == TokenType.PLUS ||
                currentToken.getType() == TokenType.MINUS) {

            TokenType opType = currentToken.getType();
            match(opType);

            ExpressionNode operand = parseUnary();
            return new UnaryOpNode(opType, operand);
        } else {
            return parsePow();
        }
    }

    // POW ::= FACTOR ^ UNARY | FACTOR
    private ExpressionNode parsePow() {
        ExpressionNode base = parseFactor();

        if (currentToken.getType() == TokenType.CARET) {
            match(TokenType.CARET);

            ExpressionNode exponent = parseUnary();
            return new BinaryOpNode(TokenType.CARET, base, exponent);
        }
        return base;
    }

    // FACTOR ::= ( EXPR ) | id | digit
    private ExpressionNode parseFactor() {
        switch (currentToken.getType()) {
            case LPAREN:
                match(TokenType.LPAREN);
                ExpressionNode expr = parseExpression();
                match(TokenType.RPAREN);
                return expr;
            case ID:
                String idName = currentToken.getValue();
                match(TokenType.ID);
                return new IdNode(idName);
            case NUMBER:
                String numberValue = currentToken.getValue();
                match(TokenType.NUMBER);
                return new NumberNode(numberValue);
            default:
                error("Esperava '(', ID ou Número, mas encontrou " + currentToken.getType());
                return null;
        }
    }

    public ExpressionNode parse() {
        ExpressionNode ast = parseExpression();
        if (currentToken.getType() != TokenType.EOF) {
            error("Caracteres extras no final da expressão.");
        }
        return ast;
    }
}
package lexer;

public class Lexer {
    private final String input;
    private int position;
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.currentChar = (this.position < this.input.length()) ? this.input.charAt(this.position) : '\0';
    }

    public int getPosition() {
        return position;
    }

    private void advance() {
        position++;
        if (position < input.length()) {
            currentChar = input.charAt(position);
        } else {
            currentChar = '\0';
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private Token number() {
        StringBuilder sb = new StringBuilder();
        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            sb.append(currentChar);
            advance();
        }
        return new Token(TokenType.NUMBER, sb.toString());
    }

    private Token id() {
        StringBuilder sb = new StringBuilder();

        while (currentChar != '\0' && Character.isLetter(currentChar)) {
            sb.append(currentChar);
            advance();
        }
        return new Token(TokenType.ID, sb.toString());
    }

    public Token getNextToken() {
        while (currentChar != '\0') {
            skipWhitespace();

            if (currentChar == '\0') {
                break;
            }

            if (Character.isDigit(currentChar)) {
                return number();
            }

            if (Character.isLetter(currentChar)) {
                return id();
            }

            switch (currentChar) {
                case '+':
                    advance();
                    return Token.create(TokenType.PLUS);
                case '-':
                    advance();
                    return Token.create(TokenType.MINUS);
                case '*':
                    advance();
                    return Token.create(TokenType.STAR);
                case '/':
                    advance();
                    return Token.create(TokenType.SLASH);
                case '^':
                    advance();
                    return Token.create(TokenType.CARET);
                case '(':
                    advance();
                    return Token.create(TokenType.LPAREN);
                case ')':
                    advance();
                    return Token.create(TokenType.RPAREN);
                default:
                    char unknownChar = currentChar;
                    advance();
                    System.err.println("Erro léxico: Caractere desconhecido '" + unknownChar + "' na posição " + (position - 1));
                    return new Token(TokenType.UNKNOWN, String.valueOf(unknownChar));
            }
        }
        return Token.create(TokenType.EOF);
    }
}
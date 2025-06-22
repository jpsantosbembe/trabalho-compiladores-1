package lexer;

public class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value != null && !value.isEmpty()) {
            return "Token(" + type + ", '" + value + "')";
        } else {
            return "Token(" + type + ")";
        }
    }

    public static Token create(TokenType type) {
        return new Token(type, "");
    }
}

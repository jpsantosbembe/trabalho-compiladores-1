package lexer;

public enum TokenType {
    // Operadores Aritméticos
    PLUS,
    MINUS,
    STAR,
    SLASH,
    CARET,

    // Símbolos de Agrupamento
    LPAREN,
    RPAREN,

    // Literais
    ID,
    NUMBER,

    // Fim do Arquivo
    EOF,

    // Caracteres desconhecidos
    UNKNOWN
}
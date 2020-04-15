package game;

public enum Token {
    YELLOW("Y"),
    RED("R"),
    EMPTY("O");

    private final String sign;

    Token(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}

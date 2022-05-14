package ru.dwerd.telegram.keyboard.layout.translator.common;

public enum EnglishRussianKeyboard {
    Q('й'),
    W('ц'),
    E('у'),
    R('к'),
    T('е'),
    Y('н'),
    U('г'),
    I('ш'),
    O('щ'),
    P('з'),
    RUSSIAN_X('х'),
    RUSSIAN_TVERDY_ZNAK('ъ'),
    A('ф'),
    S('ы'),
    D('в'),
    F('а'),
    G('п'),
    H('р'),
    J('л'),
    K('л'),
    L('д'),
    RUSSIAN_G('ж'),
    RUSSIAN_A('э'),
    Z('я'),
    X('x'),
    C('с'),
    V('м'),
    B('и'),
    N('т'),
    M('ь'),
    RUSSIAN_B('б'),
    RUSSIAN_Y('ю'),
    SPACE(' '),
    EXCLAMATION_POINT('!'),
    COMMA(','),
    POINT('.');

    private final char symbol;

    EnglishRussianKeyboard(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static EnglishRussianKeyboard getEnglishKeyboard(char symbol) {
        return switch (symbol) {
            case '[', '{' -> RUSSIAN_X;
            case ']', '}' -> RUSSIAN_TVERDY_ZNAK;
            case ':', ';' -> RUSSIAN_G;
            case '\"', '\'' -> RUSSIAN_A;
            case ',', '<' -> RUSSIAN_B;
            case '>', '.' -> RUSSIAN_Y;
            case 'q' -> Q;
            case 'w' -> W;
            case 'e' -> E;
            case 'r' -> R;
            case 't' -> T;
            case 'y' -> Y;
            case 'u' -> U;
            case 'i' -> I;
            case 'o' -> O;
            case 'a' -> A;
            case 'b' -> B;
            case 's' -> S;
            case 'd' -> D;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'j' -> J;
            case 'l' -> L;
            case 'z' -> Z;
            case 'x' -> X;
            case 'c' -> C;
            case 'v' -> V;
            case 'n' -> N;
            case 'm' -> M;
            case '!' -> EXCLAMATION_POINT;
            default -> SPACE;
        };
    }

    public static EnglishRussianKeyboard getRussianKeyboard(char symbol) {
        return switch (symbol) {
            case 'й' -> Q;
            case 'ц' -> W;
            case 'у' -> E;
            case 'к' -> R;
            case 'е' -> T;
            case 'н' -> Y;
            case 'г' -> U;
            case 'ш' -> I;
            case 'з' -> P;
            case 'х' -> RUSSIAN_X;
            case 'ъ' -> RUSSIAN_TVERDY_ZNAK;
            case 'ф' -> A;
            case 'ы' -> S;
            case 'в' -> D;
            case 'а' -> F;
            case 'п' -> G;
            case 'р' -> H;
            case 'о' -> J;
            case 'л' -> K;
            case 'д' -> L;
            case 'я' -> Z;
            case 'ч' -> X;
            case 'с' -> C;
            case 'м' -> V;
            case 'и' -> B;
            case 'т' -> N;
            case 'ь' -> M;
            case 'щ' -> O;
            case '.' -> POINT;
            case ',' -> COMMA;
            case '!' -> EXCLAMATION_POINT;
            default -> SPACE;
        };
    }
}

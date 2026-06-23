package customer_system;

public class ConsoleUI {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    //public static final String UNDERLINE = "\u001B[4m";

    public static String color256(int code) {
        return "\u001B[38;5;" + code + "m";
    }
}

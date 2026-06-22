package customer_system;

public class Customer {
    private final String c_name, c_email;
    private final Grade c_grade;

    public Customer(String c_name, String c_email, Grade c_grade) {
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_grade = c_grade;
    }

    public Grade getGrade() {
        return c_grade;
    }

    public void getInfo() {
        System.out.print(ConsoleColor.PURPLE);
        System.out.printf("""
                                [회원정보]
                           회원이름: %s
                           회원이메일: %s
                           회원등급: %s%n""", c_name, c_email, c_grade);
        System.out.println(ConsoleColor.RESET);
    }
}

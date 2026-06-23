package customer_system.customermanager;

import customer_system.ConsoleUI;

public class Customer {
    private final String c_name, c_email;
    private final Grade c_grade;
    private long money;

    public Customer(String c_name, String c_email, Grade c_grade) {
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_grade = c_grade;
    }

    public Grade getGrade() {
        return c_grade;
    }

    public void setMoney(long m) {
        this.money = m;
    }

    public void getInfo() {
        System.out.print(ConsoleUI.color256(141));
        System.out.printf("""
                                [회원정보]
                           회원이름: %s
                           회원이메일: %s
                           회원등급: %s
                           잔액: %,d%n""", c_name, c_email, c_grade, money);
        System.out.println(ConsoleUI.RESET);
    }
}

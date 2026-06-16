package Customer_System;

public class Product {
    private String p_name, description;
    private int s_amount;
    private long price;

    public Product (String name, long p, String d, int a) {
        this.p_name = name;
        this.price = p;
        this.description = d;
        this.s_amount = a;
    }
}

package customer_system.productmanager;

public class Product {
    private final String p_name, description;
    private final int s_amount;
    private final long price;

    public Product(String name, long p, String d, int a) {
        this.p_name = name;
        this.price = p;
        this.description = d;
        this.s_amount = a;
    }

    public String getRecord() {
        return p_name + "----" + String.format("%,d", price) + "원----" + description + "----" + s_amount + "개";
    }

    public Cart addNewProductInfo() {
        return new Cart(p_name, s_amount, price);
    }
}

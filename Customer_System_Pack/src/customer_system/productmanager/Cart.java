package customer_system.productmanager;

public class Cart {
    private final String sl_product;
    private final int amount;
    private final long sl_price;

    public Cart(String sl_product, int amount, long sl_price) {
        this.sl_product = sl_product;
        this.amount = amount;
        this.sl_price = sl_price;
    }

    public String getCartRecord() {
        return sl_product + "----" + String.format("%,d", sl_price) + "원----" + amount + "개";
    }
}

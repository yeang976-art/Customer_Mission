package customer_system.productmanager;

import customer_system.custom_exceptions.*;

public class Product {
    private final String p_name, description;
    private int s_amount;
    private final long price;

    public Product(String name, long p, String d, int a) {
        this.p_name = name;
        this.price = p;
        this.description = d;
        this.s_amount = a;
    }

    public String getName() {
        return p_name;
    }

    // 상품 레코드 나열
    public String getRecord() {
        return p_name + "----" + String.format("%,d", price) + "원----" + description + "----" + s_amount + "개";
    }

    // 장바구니 추가 양식
    public Cart addNewProductInfo() {
        return new Cart(p_name, s_amount - (s_amount - 1), price);
    }

    // 재고 차감 작업
    public void decreaseAmount(int amount) {
        if (amount <= 0) throw new InvalidStockAmountException();
        else if (this.s_amount < amount) throw new InsufficientStockException();
        else this.s_amount -= amount;
    }

    // 재고 추가 작업
    public void increaseAmount(int amount) {
        this.s_amount += amount;
    }
}

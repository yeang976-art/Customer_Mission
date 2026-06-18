package customer_system;

import java.util.*;

public class CommerceSystem {
    private final Scanner sc;
    private final Category c;

    public CommerceSystem() {
        sc = new Scanner(System.in);
        c = new Category();
    }

    public void start() {
        int a = sc.nextInt();
        c.setProducts(a);
        c.getProducts();
    }
}

package customer_system;

import java.util.*;

public class CommerceSystem {
    private final Scanner sc;
    private final Category c;
    private boolean isRunning;

    public CommerceSystem() {
        sc = new Scanner(System.in);
        isRunning = true;
        c = new Category();
    }

    public void start() throws InterruptedException {
        try {
            int a = sc.nextInt();
            c.setProducts(a);
            c.getProducts();
            int b = sc.nextInt();
            c.selectedProduct(b);
        } catch (IllegalArgumentException e) {
            System.err.println("잘못된 카테고리 번호입니다.");
            Thread.sleep(500);
            c.getCategories();
        } catch (ExecutiveCloseException ce) {
            System.out.println("프로그램을 종료합니다.");
            sc.close();
            setRunState();
        }
    }

    public void setRunState() {
        isRunning = false;
    }

    public boolean getRunState() {
        return isRunning;
    }
}

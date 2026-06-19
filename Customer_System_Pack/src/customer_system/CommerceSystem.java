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
        } catch (InputMismatchException e) {
            System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
            sc.nextLine();
            Thread.sleep(500);
            c.getCategories();
        } catch (IllegalArgumentException e) {
            System.err.println("[ERROR 404] 유효하지 않은 카테고리입니다.");
            Thread.sleep(500);
            c.getCategories();
        } catch (ExecutiveCloseException ce) {
            System.out.println("[INFO 200] 프로그램을 정상 종료합니다.");
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

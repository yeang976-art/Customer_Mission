package customer_system;

import customer_system.products.*;

import java.util.*;

public class CommerceSystem {
    private final Scanner sc;
    private final Category c;
    private boolean isRunning;
    private boolean canAddCart = false;

    public CommerceSystem() {
        sc = new Scanner(System.in);
        isRunning = true;
        c = new Category();
    }

    // 상호작용 절차
    public void start() throws InterruptedException {
        while (!canAddCart) {
            c.getCategories();
            mainState();
            if (!isRunning) return;
            productListState();
        }

        sc.nextLine(); // 버퍼

        addCartState();
        canAddCart = false;
        System.out.println("\n\n"); // 줄간
    }

    // 메인 카테고리 창으로 이동
    private void mainState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                int a = sc.nextInt();
                // 종료
                if (a == 0) {
                    setRunState();
                    return;
                }
                c.setProducts(a);
                c.getProducts();
                isVerified = true;

            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
                Thread.sleep(500);
                c.getCategories();
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR 404] 유효하지 않은 카테고리입니다.");
                Thread.sleep(500);
                c.getCategories();
            } catch (NullPointerException e) {
                sc.close();
            }
        }
    }

    // 해당 카테고리의 상품 목록 창으로 이동
    private void productListState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                int b = sc.nextInt();
                c.selectedProduct(b);
                isVerified = true;
                if (b != 0) canAddCart = true; // 상품 선택 완료시
            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
                Thread.sleep(500);
                c.getProducts();
            } catch (IndexOutOfBoundsException e) {
                System.err.println("[ERROR 405] 존재하지 않는 상품 번호입니다.");
                Thread.sleep(500);
                c.getProducts();
            }
        }
    }

    // 장바구니 추가여부 창으로 이동
    private void addCartState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                System.out.println("""
                        선택한 상품을 장바구니에 추가하시겠습니까?
                        (예/아니오) 혹은 (yes/no) 로 입력""");
                String s = sc.nextLine();
                c.setCart(s);
                isVerified = true;
            } catch (InvalidYesNoInputException ce) {
                System.err.println("[ERROR 406] 예/아니오 형식만 입력할 수 있습니다.");
                Thread.sleep(500);
            }
        }
    }

    // 프로그램 비활성화 플래그
    private void setRunState() {
        isRunning = false;
    }

    // 프로그램 활성여부
    public boolean getRunState() {
        return isRunning;
    }
}

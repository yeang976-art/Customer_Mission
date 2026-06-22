package customer_system;

import customer_system.productmanager.*;
import customer_system.productmanager.custom_exceptions.EmptyCartOrderException;
import customer_system.productmanager.custom_exceptions.NoCancelableOrderException;

import java.util.*;

public class CommerceSystem {
    private final Scanner sc;
    private final Category c;
    private boolean isRunning;
    private boolean canAddCart = false;
    private int a;

    public CommerceSystem() {
        sc = new Scanner(System.in);
        isRunning = true;
        c = new Category();
    }

    // 상호작용 절차
    public void start() throws InterruptedException {
        // 상품을 선택하지 않은 경우
        while (!canAddCart) {
            c.getCategories();
            mainState();
            if (!isRunning) {
                sc.close();
                return;
            }

            if (a > 0 && a < 91) productListState();
            else if (a == 91 || a == 92) orderState();
        }

        addCartState();
        canAddCart = false;
        System.out.println("\n\n"); // 줄간격
    }

    // 메인 카테고리 창으로 이동
    private void mainState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                a = sc.nextInt();
                // 종료
                if (a == 0) {
                    setRunState();
                    return;
                } else {
                    System.out.println(setTitle(a));
                    Thread.sleep(300);
                    c.setProducts(a);
                }
                isVerified = true;

            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (EmptyCartOrderException | NoCancelableOrderException ce) {
                System.err.println(ce.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR 404] 유효하지 않은 항목입니다.");
            }
        }
    }

    // 창 이름
    private String setTitle(int a) {
        if (a == 91) return ConsoleColor.CYAN + "\t\t## 주문 확인 ##" + ConsoleColor.RESET;
        else if (a == 92) return ConsoleColor.YELLOW + "\t\t## 주문 취소 ##" + ConsoleColor.RESET;
        else if (a > 0 && a < 4) return ConsoleColor.BLUE + "\t\t## 상품 목록 ##" + ConsoleColor.RESET;
        else if (a >= 4 && a < 91) throw new IllegalArgumentException();
        else throw new InputMismatchException();
    }

    // 해당 카테고리의 상품 목록 창으로 이동
    private void productListState() throws InterruptedException {
        boolean isVerified = false;
        c.getProducts();
        while (!isVerified) {
            try {
                int b = sc.nextInt();
                c.selectedProduct(b);
                Thread.sleep(500);
                isVerified = true;
                if (b != 0) canAddCart = true; // 상품 선택 완료시
            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.err.println("[ERROR 405] 존재하지 않는 상품 번호입니다.");
            }
        }
    }

    // 주문 창으로 이동
    private void orderState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                int p = sc.nextInt();
                if (a == 91) c.setOrder(p);
                else c.clearCart(p);
                Thread.sleep(1500);
                isVerified = true;
            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR 404] 유효하지 않은 항목입니다.");
            }
        }
    }

    // 장바구니 추가여부 창으로 이동
    private void addCartState() throws InterruptedException {
        boolean isVerified = false;
        System.out.println("""
                        선택한 상품을 장바구니에 추가하시겠습니까?
                        1.추가            2.취소""");
        while (!isVerified) {
            try {
                int p = sc.nextInt();
                c.setCart(p); // 장바구니 업데이트
                Thread.sleep(1500); // 메인화면으로 로딩
                isVerified = true;
            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR 404] 유효하지 않은 항목입니다.");
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


package customer_system;

import customer_system.productmanager.*;
import customer_system.productmanager.custom_exceptions.*;
import java.util.*;

public class CommerceSystem {
    private final Scanner sc;
    private final ListManager listManager;
    private Customer customer;
    private Grade grade;
    private boolean isRunning;
    private boolean canAddCart = false;
    private int a;

    public CommerceSystem() {
        sc = new Scanner(System.in);
        isRunning = true;
        listManager = new ListManager();
    }

    // 고객 등록
    public void register() {
        System.out.println(ConsoleColor.BLUE + "\t\t## 회원 등록 ##" + ConsoleColor.RESET);
        System.out.println("회원 이름을 입력하시오");
        String name = sc.nextLine();
        System.out.println("회원 이메일을 입력하시오");
        String email = sc.nextLine();

        boolean isVerified = false;
        DialogManager.getDialog().gradeList(); // 회원등급표
        while (!isVerified) {
            try {
                int g = sc.nextInt();

                if (g < 0 || g > Grade.values().length)
                    throw new IllegalArgumentException();
                else if (g == 0) {
                    setRunState();
                    return;
                }

                grade = Grade.values()[g - 1];
                isVerified = true;
            } catch (InputMismatchException e) {
                System.err.println("[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR 404] 유효하지 않은 회원 등급입니다.");
            }
        }
        customer = new Customer(name, email, grade);
        customer.getInfo();
    }

    // 상호작용 절차
    public void start() throws InterruptedException {
        Thread.sleep(1500); // 메인화면으로 로딩
        // 상품을 선택하지 않은 경우
        while (!canAddCart) {
            customer.getInfo();
            System.out.println(ConsoleColor.BLUE + "\t\t## 메인화면 ##" + ConsoleColor.RESET);
            DialogManager.getDialog().mainDialog();
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
                    System.out.println(DialogManager.getDialog().title(a));
                    Thread.sleep(300);
                    listManager.setState(a);
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

    // 해당 카테고리의 상품 목록 창으로 이동
    private void productListState() throws InterruptedException {
        boolean isVerified = false;
        listManager.getProducts();
        while (!isVerified) {
            try {
                int b = sc.nextInt();
                listManager.selectedProduct(b);
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
                if (a == 91) listManager.setOrder(p);
                else listManager.clearCart(p);
                Thread.sleep(600);
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
        DialogManager.getDialog().sureAddCartPopup();
        while (!isVerified) {
            try {
                int p = sc.nextInt();
                listManager.setCart(p); // 장바구니 업데이트
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
        System.out.println(ConsoleColor.GREEN + "[INFO 200] 커머스 플랫폼을 정상 종료합니다." + ConsoleColor.RESET);
    }

    // 프로그램 활성여부
    public boolean getRunState() {
        return isRunning;
    }
}


package customer_system;

import customer_system.customermanager.*;
import customer_system.managment.*;
import customer_system.custom_exceptions.*;
import java.util.*;

public class CommerceSystem {
    private final Scanner sc;
    private Customer customer;
    private Grade grade;
    private boolean isRunning;
    private boolean canAddCart = false;
    private int a;

    public CommerceSystem() {
        sc = new Scanner(System.in);
        isRunning = true;
    }

    // 고객 등록
    public void register() {
        System.out.println(ConsoleUI.BOLD + ConsoleUI.color256(141) + "\t\t## 회원 등록 ##" + ConsoleUI.RESET);
        System.out.println("회원 이름을 입력하시오");
        String name = sc.nextLine();
        System.out.println("회원 이메일을 입력하시오");
        String email = sc.nextLine();

        boolean isVerified = false;
        DialogManager.getDialog().gradeList(); // 회원등급
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
                handleInputMismatch();
            } catch (IllegalArgumentException e) {
                System.err.println(ConsoleUI.color256(196) + "[ERROR 404] 유효하지 않은 회원 등급입니다." + ConsoleUI.RESET);
            }
        }
        customer = new Customer(name, email, grade);
        ListManager.getInstance().setCustomer(customer);
    }

    // 상호작용 절차
    public void start() throws InterruptedException {
        Thread.sleep(1500); // 메인화면으로 로딩
        // 상품을 선택하지 않은 경우
        while (!canAddCart) {
            customer.getInfo();
            System.out.println(ConsoleUI.BOLD + ConsoleUI.color256(45) + "\t\t## 메인화면 ##" + ConsoleUI.RESET);
            DialogManager.getDialog().mainDialog();
            mainState();
            if (!isRunning) {
                sc.close();
                return;
            }

            if (a > 0 && a < 90) productListState();
            else if (a == 91) insertState();
            else if (a == 92 || a == 93) orderState();
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
                    ListManager.getInstance().setState(a);
                }
                isVerified = true;

            } catch (InputMismatchException e) {
                handleInputMismatch();
            } catch (EmptyCartOrderException | NoCancelableOrderException ce) {
                System.err.println(ce.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(ConsoleUI.color256(196) + "[ERROR 404] 유효하지 않은 항목입니다." + ConsoleUI.RESET);
            }
        }
    }

    // 해당 카테고리의 상품 목록 창으로 이동
    private void productListState() throws InterruptedException {
        boolean isVerified = false;
        ListManager.getInstance().getProducts();
        while (!isVerified) {
            try {
                int b = sc.nextInt();
                ListManager.getInstance().selectedProduct(b);
                Thread.sleep(500);
                isVerified = true;
                if (b != 0) canAddCart = true; // 상품 선택 완료시
            } catch (InputMismatchException e) {
                handleInputMismatch();
            } catch (IndexOutOfBoundsException e) {
                System.err.println(ConsoleUI.color256(196) + "[ERROR 405] 존재하지 않는 상품 번호입니다." + ConsoleUI.RESET);
            }
        }
    }

    // 입금 창으로 이동
    private void insertState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                long money = sc.nextLong();
                if (money < 0 || money > 5000000) throw new InvalidDepositAmountException();
                customer.setMoney(money);
                System.out.printf(ConsoleUI.color256(48) + "[INFO 220] %,d원이 입금되었습니다.%n" + ConsoleUI.RESET, money);
                Thread.sleep(800);
                isVerified = true;
            } catch (InputMismatchException e) {
                handleInputMismatch();
            } catch (InvalidDepositAmountException ce) {
                System.err.println(ce.getMessage());
            }
        }
    }

    // 주문 창으로 이동
    private void orderState() throws InterruptedException {
        boolean isVerified = false;
        while (!isVerified) {
            try {
                int p = sc.nextInt();
                if (a == 92) ListManager.getInstance().setOrder(p);
                else ListManager.getInstance().clearCart(p);
                Thread.sleep(600);
                isVerified = true;
            } catch (InputMismatchException e) {
                handleInputMismatch();
            } catch (IllegalArgumentException e) {
                System.err.println(ConsoleUI.color256(196) + "[ERROR 404] 유효하지 않은 항목입니다." + ConsoleUI.RESET);
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
                ListManager.getInstance().setCart(p); // 장바구니 업데이트
                isVerified = true;
            } catch (InputMismatchException e) {
                handleInputMismatch();
            } catch (IllegalArgumentException e) {
                System.err.println(ConsoleUI.color256(196) + "[ERROR 404] 유효하지 않은 항목입니다." + ConsoleUI.RESET);
            }
        }
    }

    // 숫자가 아닌 입력 처리
    private void handleInputMismatch() {
        System.err.println(ConsoleUI.color256(196) + "[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다." + ConsoleUI.RESET);
        sc.nextLine();
    }

    // 프로그램 비활성화 플래그
    private void setRunState() {
        isRunning = false;
        System.out.println(ConsoleUI.color256(46) + "[INFO 200] 커머스 플랫폼을 정상 종료합니다." + ConsoleUI.RESET);
    }

    // 프로그램 활성여부
    public boolean getRunState() {
        return isRunning;
    }
}

package customer_system.managment;

import customer_system.ConsoleUI;
import customer_system.customermanager.Customer;
import customer_system.productmanager.*;
import customer_system.custom_exceptions.*;
import customer_system.productmanager.products.*;
import java.util.*;

public class ListManager {
    private static final ListManager instance = new ListManager();

    private List<Product> products;
    private final List<Cart> cart, debugCart;
    private int index;
    private Customer customer;

    public static ListManager getInstance() {
        return instance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private ListManager() {
        cart = new ArrayList<>();
        debugCart = new ArrayList<>();
    }

    // 상품목록 설정 및 주문관리 및 종료
    public void setState(int id) {
        switch (id) {
            case 0 -> System.out.println(ConsoleUI.color256(46) + "[INFO 200] 프로그램을 정상 종료합니다." + ConsoleUI.RESET);
            case 1 -> products = new SmartDevices().loadProducts();
            case 2 -> products = new Clothes().loadProducts();
            case 3 -> products = new Foods().loadProducts();
            case 91 -> System.out.println(ConsoleUI.color256(48) + "입금할 금액을 입력하시오. (최대 5,000,000원까지 입력 가능)" + ConsoleUI.RESET);
            case 92 -> {
                if (cart.isEmpty()) throw new EmptyCartOrderException();
                else {
                    getCartInfo();
                    DialogManager.getDialog().sureOrderPopup();
                }
            }
            case 93 -> {
                if (debugCart.isEmpty()) throw new NoCancelableOrderException();
                else DialogManager.getDialog().cancelOrderPopup();
            }
            default -> throw new IllegalArgumentException();
        }
    }

    // 상품목록 출력
    public void getProducts() {
        System.out.println("상품을 선택하시오." + ConsoleUI.color256(39) + """

                              [상품목록]
                [번호] 상품명----가격(원)----설명----재고(개)""" + ConsoleUI.RESET);
        for (Product auto : products)
            System.out.println("[" + (products.indexOf(auto) + 1) + "] " + auto.getRecord());
        System.out.println("[0] 뒤로가기");
    }

    // 장바구니 추가 후 정보 출력
    public void setCart(int p) throws InterruptedException {
        switch (p) {
            case 1 -> {
                cart.add(products.get(index).addNewProductInfo());
                System.out.println(ConsoleUI.color256(87) + "[INFO 211] " + products.get(index).getName() + " 상품을 장바구니에 추가했습니다." + ConsoleUI.RESET);
                getCartInfo();
            }
            case 2 -> {
                getCartInfo();
                Thread.sleep(50);
                System.out.println(ConsoleUI.color256(120) + "[INFO 410] 메인으로 돌아갑니다.\n" + ConsoleUI.RESET);
            }
            default -> throw new IllegalArgumentException();
        }
    }

    // 장바구니 정보 및 총 금액
    public void getCartInfo() {
        long sumPrice = 0;
        System.out.println(ConsoleUI.color256(141) + """
                         [장바구니]
                상품명----가격(원)----재고(개)""" + ConsoleUI.RESET);
        for (Cart auto : cart) {
            sumPrice += auto.getPrice();
            System.out.println(auto.getCartRecord());
        }
        double discountRate = customer == null ? 0.0 : customer.getGrade().discount();
        long finalPrice = Math.round(sumPrice * (1 - discountRate));

        System.out.printf(ConsoleUI.color256(141) + """
                
                        [총 주문금액]
                %,d원
                        [회원등급 할인율]
                %.1f%%
                        [최종 결제금액]
                %,d원
                
                """ + ConsoleUI.RESET, sumPrice, discountRate * 100, finalPrice);
    }

    // 상품 선택
    public void selectedProduct(int id) {
        if (id == 0) System.out.println(ConsoleUI.color256(120) + "[INFO 410] 이전 메뉴로 돌아갑니다.\n\n" + ConsoleUI.RESET);
        else {
            index = id - 1;
            System.out.println(ConsoleUI.color256(87) + "선택한 상품: " + products.get(index).getRecord() + "\n\n" + ConsoleUI.RESET);
        }
    }

    // 주문 처리
    public void setOrder(int p) {
        switch (p) {
            case 1 -> {
                try {
                    System.out.println(ConsoleUI.color256(51) + "[INFO 201] 주문을 완료했습니다.\n" + ConsoleUI.RESET);
                    for (Cart cartItem : cart) {
                        for (Product product : products) {
                            if (product.getName().equals(cartItem.getName())) {
                                product.decreaseAmount(cartItem.getAmount());
                                break;
                            }
                        }
                    }
                    debugCart.addAll(cart);
                    cart.clear();
                } catch (InsufficientStockException | InvalidStockAmountException ce) {
                    System.err.println(ce.getMessage());
                }
            }
            case 2 -> System.out.println(ConsoleUI.color256(120) + "[INFO 410] 메인으로 돌아갑니다.\n" + ConsoleUI.RESET);
            default -> throw new IllegalArgumentException();
        }
    }

    // 주문 목록 초기화
    public void clearCart(int p) {
        switch (p) {
            case 1 -> {
                System.out.println(ConsoleUI.color256(208) + "[INFO 202] 모든 주문을 취소합니다." + ConsoleUI.RESET);
                for (Cart cartItem : debugCart) {
                    for (Product product : products) {
                        if (product.getName().equals(cartItem.getName())) {
                            product.increaseAmount(cartItem.getAmount());
                            break;
                        }
                    }
                }
                debugCart.clear();
            }
            case 2 -> System.out.println(ConsoleUI.color256(120) + "[INFO 410] 메인으로 돌아갑니다." + ConsoleUI.RESET);
            default -> throw new IllegalArgumentException();
        }
    }
}

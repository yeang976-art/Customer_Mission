package customer_system.productmanager;

import customer_system.InvalidYesNoInputException;
import customer_system.products.*;

import java.util.*;

public class Category {
    private List<Product> products;
    private final List<Cart> cart;
    private int index;

    public Category() {
        cart = new ArrayList<>();
    }

    public void getCategories() {
        System.out.println("""
                목록을 선택하시오.
                (예: "3" 입력시 식품 카테고리로 이동)
                
                [ 실시간 커머스 플랫폼 메인 ]
                [1] 전자제품
                [2] 의류
                [3] 식품
                [0] 프로그램 종료
                """);
    }

    public void setProducts(int id) throws NullPointerException {
        if (id == 0) {
            System.out.println("[INFO 200] 프로그램을 정상 종료합니다.");
        } else {
            products = switch (id) {
                case 1 -> new SmartDevices().createProducts();
                case 2 -> new Clothes().createProducts();
                case 3 -> new Foods().createProducts();
                default -> throw new IllegalArgumentException();
            };
        }
    }

    public void getProducts() {
        System.out.println("""
                상품을 선택하시오.
                              [상품목록]
                [번호] 상품명----가격(원)----설명----재고(개)""");
        for (Product auto : products)
            System.out.println("[" + (products.indexOf(auto) + 1) + "] " + auto.getRecord());
        System.out.println("[0] 뒤로가기");
    }

    public void setCart(String s) {
        if (s.equals("예") || s.equals("yes")) {
            cart.add(products.get(index).addNewProductInfo());
            getCartInfo();
        } else if (s.equals("아니오") || s.equals("no")) {
            getCartInfo();
        } else throw new InvalidYesNoInputException("406 Not Acceptable");
    }

    public void getCartInfo() {
        System.out.println("""
                         [장바구니]
                상품명----가격(원)----재고(개)""");
        for (Cart auto : cart)
            System.out.println(auto.getCartRecord());
    }

    public void selectedProduct(int id) throws InterruptedException {
        if (id == 0) {
            System.out.println("[INFO 410] 이전 메뉴로 돌아갑니다.");
            Thread.sleep(200);
        } else {
            index = id - 1;
            System.out.println("선택한 상품: " + products.get(index).getRecord() + "\n\n");
            Thread.sleep(200);
        }
    }
}

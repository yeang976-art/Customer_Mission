package customer_system;

import customer_system.products.*;
import java.util.*;

public class Category {
    private List<Product> products;

    public Category() {
        getCategories();
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

    public void setProducts(int id) {
        if (id == 0) throw new ExecutiveCloseException("프로그램을 종료합니다.");
        else {
            products = switch (id) {
                case 1 -> new SmartDevices().createProducts();
                case 2 -> new Clothes().createProducts();
                case 3 -> new Foods().createProducts();
                default -> throw new IllegalArgumentException();
            };
        }
    }

    public void selectedProduct(int id) throws InterruptedException {
        if (id == 0) {
            Thread.sleep(200);
            getCategories();
        }
        else {
            System.out.println("선택한 상품: " + switch (id) {
                case 1 -> products.getFirst().getRecord();
                case 2 -> products.get(1).getRecord();
                case 3 -> products.get(2).getRecord();
                case 4 -> products.get(3).getRecord();
                default -> throw new IllegalArgumentException();
            } + "\n\n");
            Thread.sleep(1500);
            getCategories();
        }
    }

    public void getProducts() {
        System.out.println("상품명----가격(원)----설명----재고(개)");
        for (Product auto : products)
            System.out.println("[" + (products.indexOf(auto) + 1) + "] " + auto.getRecord());
        System.out.println("[0] 뒤로가기");
    }
}

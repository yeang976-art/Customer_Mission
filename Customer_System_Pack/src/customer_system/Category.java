package customer_system;

import customer_system.products.*;

import java.util.*;

public class Category {
    private List<Product> products;

    public Category() {
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

    public List<Product> setProducts(int id) {
        products = switch (id) {
            case 1 -> new SmartDevices().createProducts();
            case 2 -> new Clothes().createProducts();
            case 3 -> new Foods().createProducts();
            default -> throw new IllegalArgumentException("잘못된 카테고리 번호입니다.");
        };
        return products;
    }

    public void getProducts() {
        System.out.println("상품명---가격(원)---설명---재고(개)");
        for (Product auto : products)
            System.out.println("[" + products.indexOf(auto) + "] " + auto.getRecord());
    }
}

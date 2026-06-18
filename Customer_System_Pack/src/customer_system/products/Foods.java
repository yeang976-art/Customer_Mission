package customer_system.products;

import java.util.*;

public class Foods implements IProuducts{

    public Foods() {
        System.out.println("\t\t\t< 식품 >");
    }

    @Override
    public List<Product> createProducts() {
        return new ArrayList<>(List.of(
                new Product("제주 감귤 세트", 29000L, "산지 직송 달콤한 제철 감귤", 60),
                new Product("한우 불고기 팩", 45000L, "간편하게 조리 가능한 양념 한우 불고기", 25),
                new Product("프리미엄 샐러드", 12000L, "신선한 채소와 닭가슴살이 들어간 샐러드", 40),
                new Product("수제 마카롱 세트", 18000L, "다양한 맛으로 구성된 디저트 세트", 32)
        ));
    }
}

package customer_system.productmanager.products;

import customer_system.productmanager.*;

import java.util.*;

public class Clothes implements IProuducts {

    private static final List<Product> PRODUCTS = new ArrayList<>(List.of(
            new Product("오버핏 후드티", 59000L, "데일리로 입기 좋은 캐주얼 후드티", 50),
            new Product("와이드 데님 팬츠", 79000L, "편안한 핏의 스트릿 스타일 청바지", 35),
            new Product("블랙 숏패딩", 149000L, "겨울철 보온성이 좋은 기본 패딩", 20),
            new Product("니트 가디건", 69000L, "간절기에 입기 좋은 부드러운 가디건", 28)
    ));

    public Clothes() {
        System.out.println("\t\t\t< 의류 >");
    }

    @Override
    public List<Product> loadProducts() {
        return PRODUCTS;
    }
}

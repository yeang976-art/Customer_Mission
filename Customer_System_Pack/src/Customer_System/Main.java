package Customer_System;

import java.util.*;

public class Main {
    static void main(String[] args) {
        List<Product> products = createProducts();
    }

    private static List<Product> createProducts() {
        return new ArrayList<>(List.of(
                new Product("Galaxy S25", 1200000L, "최신 안드로이드 스마트폰", 5),
                new Product("iPhone 16", 1350000L, "Apple의 최신 스마트폰", 5),
                new Product("MacBook Pro", 2400000L, "M3 칩셋이 탑재된 노트북", 5),
                new Product("AirPods Pro", 350000L, "노이즈 캔슬링 무선 이어폰", 5)
        ));
    }
}

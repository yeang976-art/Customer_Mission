package customer_system.products;

import java.util.*;

public class SmartDevices implements IProuducts {

    public SmartDevices() {
        System.out.println("\t\t\t< 전자제품 >");
    }

    @Override
    public List<Product> createProducts() {
        return new ArrayList<>(List.of(
                new Product("Galaxy S25", 1200000L, "최신 안드로이드 스마트폰", 25),
                new Product("iPhone 16", 1350000L, "Apple의 최신 스마트폰", 30),
                new Product("MacBook Pro", 2400000L, "M3 칩셋이 탑재된 노트북", 12),
                new Product("AirPods Pro", 350000L, "노이즈 캔슬링 무선 이어폰", 40)
        ));
    }
}

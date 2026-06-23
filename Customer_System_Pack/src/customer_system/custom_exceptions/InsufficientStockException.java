package customer_system.custom_exceptions;

import customer_system.ConsoleUI;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super(ConsoleUI.color256(196) + "[ERROR 409] 재고가 부족합니다." + ConsoleUI.RESET);
    }
}

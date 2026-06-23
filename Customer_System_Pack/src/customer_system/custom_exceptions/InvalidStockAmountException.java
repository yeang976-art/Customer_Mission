package customer_system.custom_exceptions;

import customer_system.ConsoleUI;

public class InvalidStockAmountException extends RuntimeException {
    public InvalidStockAmountException() {
        super(ConsoleUI.color256(196) + "[ERROR 400] 재고 수량은 1개 이상이어야 합니다." + ConsoleUI.RESET);
    }
}

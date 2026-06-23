package customer_system.custom_exceptions;

import customer_system.ConsoleUI;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super(ConsoleUI.color256(196) + "[ERROR 402] 잔액이 부족하여 주문할 수 없습니다." + ConsoleUI.RESET);
    }
}

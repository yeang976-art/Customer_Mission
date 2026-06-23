package customer_system.custom_exceptions;

import customer_system.ConsoleUI;

public class EmptyCartOrderException extends RuntimeException {
    public EmptyCartOrderException() {
        super(ConsoleUI.color256(196) + "[ERROR 409] 장바구니가 비어 있어 주문할 수 없습니다." + ConsoleUI.RESET);
    }
}

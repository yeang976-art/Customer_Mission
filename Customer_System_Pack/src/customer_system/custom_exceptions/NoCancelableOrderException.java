package customer_system.custom_exceptions;

import customer_system.ConsoleUI;

public class NoCancelableOrderException extends RuntimeException {
    public NoCancelableOrderException() {
        super(ConsoleUI.color256(196) + "[ERROR 409] 취소 가능한 주문이 없습니다." + ConsoleUI.RESET);
    }
}

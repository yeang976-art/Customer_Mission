package customer_system.custom_exceptions;

import customer_system.ConsoleUI;

public class InvalidDepositAmountException extends RuntimeException {
    public InvalidDepositAmountException() {
        super(ConsoleUI.color256(196) + "[ERROR 422] 입금 금액은 0원 이상 1,000,000원 이하만 입력할 수 있습니다." + ConsoleUI.RESET);
    }
}

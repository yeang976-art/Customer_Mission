package customer_system.custom_exceptions;

public class NoCancelableOrderException extends RuntimeException {
    public NoCancelableOrderException() {
        super("[ERROR 409] 취소 가능한 주문이 없습니다.");
    }
}

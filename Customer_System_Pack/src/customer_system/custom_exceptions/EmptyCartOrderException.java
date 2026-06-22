package customer_system.custom_exceptions;

public class EmptyCartOrderException extends RuntimeException {
    public EmptyCartOrderException() {
        super("[ERROR 409] 장바구니가 비어 있어 주문할 수 없습니다.");
    }
}

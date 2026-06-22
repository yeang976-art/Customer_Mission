package customer_system.productmanager.custom_exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super("[ERROR 409] 재고가 부족합니다.");
    }
}

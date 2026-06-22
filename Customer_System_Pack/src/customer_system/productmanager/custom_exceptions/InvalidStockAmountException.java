package customer_system.productmanager.custom_exceptions;

public class InvalidStockAmountException extends RuntimeException {
    public InvalidStockAmountException() {
        super("[ERROR 400] 재고 수량은 1개 이상이어야 합니다.");
    }
}

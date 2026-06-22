package customer_system;

public class DialogManager {
    private static final DialogManager dialog = new DialogManager();

    public static DialogManager getDialog() {
        return dialog;
    }

    // 창 이름
    public String title(int a) {
        if (a == 91) return ConsoleColor.CYAN + "\t\t## 주문 확인 ##" + ConsoleColor.RESET;
        else if (a == 92) return ConsoleColor.YELLOW + "\t\t## 주문 취소 ##" + ConsoleColor.RESET;
        else if (a > 0 && a < 4) return ConsoleColor.BLUE + "\t\t## 상품 목록 ##" + ConsoleColor.RESET;
        else throw new IllegalArgumentException();
    }

    public void gradeList() {
        System.out.println("""
                        회원 등급을 입력하시오
                        
                        [1] SIENNA
                        [2] COBALT
                        [3] DANDELION
                        [4] CYAN
                        [5] SCARLET
                        [6] CORAL
                        [7] ARGENTO
                        [8] ELDORA
                        [9] CRIMSON
                        [10] IMPERIAL
                        
                        [0] 프로그램 종료""");
    }

    public void mainDialog() {
        System.out.println("""
                목록을 선택하시오.
                (예: "3" 입력시 식품 카테고리로 이동)
                
                [ 실시간 커머스 플랫폼 메인 ]
                [1] 전자제품
                [2] 의류
                [3] 식품
                [0] 종료      | 프로그램 종료
                
                [ 주문 관리 ]
                [91] 장바구니 확인    | 장바구니를 확인 후 주문합니다.
                [92] 주문 취소       | 진행중인 주문을 취소합니다.
                """);
    }

    public void sureAddCartPopup() {
        System.out.println("""
                        선택한 상품을 장바구니에 추가하시겠습니까?
                        1.추가            2.취소""");
    }

    public void sureOrderPopup() {
        System.out.println("""
                        위와 같이 주문 하시겠습니까?
                        1.주문확정            2.메인으로""");
    }

    public void cancelOrderPopup() {
        System.out.println("""
                        주문을 취소하시겠습니까?
                        1.주문취소            2.메인으로""");
    }
}

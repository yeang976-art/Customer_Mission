package customer_system;

public class Main {
    static void main() throws InterruptedException {
        CommerceSystem cs = new CommerceSystem();
        while (cs.getRunState()) {
            cs.start();
        }
    }
}

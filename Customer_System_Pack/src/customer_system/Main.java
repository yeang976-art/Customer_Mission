package customer_system;

public class Main {
    static void main(String[] ignoredArgs) throws InterruptedException {
        CommerceSystem cs = new CommerceSystem();
        while (cs.getRunState()) {
            cs.start();
        }
    }
}

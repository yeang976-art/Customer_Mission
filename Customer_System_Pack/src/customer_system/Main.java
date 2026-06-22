package customer_system;
// v.1.1.3
public class Main {
    static void main(String[] ignoredArgs) throws InterruptedException {
        CommerceSystem cs = new CommerceSystem();
        while (cs.getRunState()) {
            cs.start();
        }
    }
}

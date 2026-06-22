package customer_system;
// v.2.0.1
public class Main {
    static void main(String[] ignoredArgs) throws InterruptedException {
        CommerceSystem cs = new CommerceSystem();
        cs.register();
        while (cs.getRunState()) {
            cs.start();
        }
    }
}

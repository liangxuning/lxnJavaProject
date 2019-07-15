package sometest;

import java.util.Random;

public class Prientfortest {
    public static void main(String[] args) {
        Prientfortest prientfortest = new Prientfortest();
        prientfortest.sot();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        prientfortest.sot();
        prientfortest.sot();
        prientfortest.sot();
        prientfortest.sot();
        prientfortest.sot();
        prientfortest.sot();
        prientfortest.sot();
    }
    private void sot() {
        while (true) {
            System.out.println("---");
            Random random = new Random();
            int i = random.nextInt(2);
            if (i == 1) {
                break;
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

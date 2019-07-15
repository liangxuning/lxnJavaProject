package codepassage;

public class TestSleep {
    public static void sleep1() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1111111");
    }
    public static void sleep2() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("2222222");
    }
    static class TestSleep2 {
        public static void sleep2() {
            System.out.println("2222222");
        }
    }
    public static void main(String[] args) {
        sleep1();
        TestSleep2.sleep2();
    }
}

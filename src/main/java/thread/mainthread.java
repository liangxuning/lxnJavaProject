package thread;

public class mainthread extends Thread{
    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("啦啦啦");
        }
    }

    public static void main(String[] args) {
        new mainthread().start();
        System.out.println("lalala");

    }
}

package sometest;

class Singleton {
    private static Singleton sin = new Singleton();
    public static int counter1;
    public static int counter2 = 0;

    private Singleton() {
        counter1++;
        counter2++;
    }

    public static Singleton getInstance() {
        return sin;
    }
}

class Test {
    public static void main(String[] args) {
        Singleton sin = Singleton.getInstance();
        System.out.println(sin.counter1);
        System.out.println(sin.counter2);
    }
}

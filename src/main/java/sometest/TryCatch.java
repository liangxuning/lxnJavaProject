package sometest;

public class TryCatch {
    public static void main(String[] args) {
        try {
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("11");
    }
}

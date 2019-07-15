package sometest;

public class Double2String {
    public static void main(String[] args) {
        String s = "0.22342";
        Double d = Double.parseDouble(s);
        double dd = d * 1000;
        try {
            Thread.sleep((long) dd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(dd);
    }
}

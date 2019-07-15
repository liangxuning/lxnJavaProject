package fanxing;

import main.java.ehcachermi.vo.NeInfo;

public class FanXin {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String neinfo = "NeInfo";
        fanXin(neinfo);
    }

    private static void fanXin(String neinfo) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("main.java.ehcachermi.vo." + neinfo);
        NeInfo neInfo = (NeInfo)clazz.newInstance();
        neInfo.setNe_id("123");
        System.out.println();
    }
}

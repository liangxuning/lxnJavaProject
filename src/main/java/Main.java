import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws ParseException {
        String s = "Sat Jan  9 18:29:17 2013";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
        Date date = sdf.parse(s);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ss = sdf2.format(date);
        System.out.println(ss);


//        String s = "   Sat Jan 19 18:29:17 2013 aaa bbb";
//
//        String [] ss = s.trim().split(" ");
//        String sss = s.trim().substring(0,ss[0].length() + ss[1].length() + ss[2].length() + ss[3].length() + ss[4].length() + 4);
//        String ssss = s.trim().substring(0, 3);
//        String sssss = s.trim().substring(0, s.trim().indexOf(ss[4]));
//        System.out.println(ss[5]);
//        System.out.println(sssss);

//        String FileList = "aaa/bbb/ccc.csv";
//        String fileout = FileList.substring(FileList.lastIndexOf("/") + 1, FileList.length());
//        System.out.println(fileout);

//        String d = "0.0";
//        Double dd = 0.0;
//        dd.intValue();
//        System.out.println(Integer.parseInt(d));
//        System.out.println((int)Float.parseFloat(d));

//        String s = map2map();
//        System.out.println(s);

//        String s = "WL_LTE_COMMON_TDD_V3.0_XML_PM_810";
//        String ss = "WL_LTE_COMMON_TDD_V3.0_XML_PM_810_2";
//        System.out.println(ss.charAt(ss.length()-2));
//        System.out.println(ss.split("_")[ss.split("_").length-2]);
//        System.out.println(s.substring(s.length()-3, s.length()));
//        String[] sss = ss.split("_", ss.lastIndexOf("_"));
//        System.out.println(sss[0]);
//        String s = "liangxuning";
//        System.out.println(new String(s));
    }

    private static String map2map() {
        String s = "WL_LTE_COMMON_TDD_V3.0_XML_PM_810";
        String ss = "WL_LTE_COMMON_TDD_V3.0_XML_PM_810_2";
        return s.charAt(s.length()-2)=='_'?s.split("_")[s.split("_").length-2]:s.substring(s.length()-3, s.length());
    }
}

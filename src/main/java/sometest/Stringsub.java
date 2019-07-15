package sometest;

import java.io.*;
import java.util.Arrays;

public class Stringsub {
    public static void main(String[] args) {
//        String s = "Fri Mar  8 16:14:59 2019 1 10.228.248.73 22 /data/Gframe/north/a.txt b _ o r gframe ftp 0 * c";
//        String ss = "Sat Jan 19 18:29:17 2013 1 10.22.1.127 31394 /tmp/linux.x64_11gR2_database_1of2/database/stage/Components/oracle.rdbms.rman/11.2.0.1.0/1/DataFiles/filegroup1.8.1.jar b _ i r root ftp 0 * c";
//        String sxs = "Fri Mar  8 16:01:32 2019 1 10.228.248.73 22 /data/Gframe/north/a.txt b _ o r gframe ftp 0 * c";
//        System.out.println(Arrays.toString(s.split(" ")));
//        System.out.println(sxs.split(" ").length);
//        System.out.println(Arrays.toString(ss.split(" ")));
        File file = new File("/Users/liangxuning/lll");
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
//            br.mark(1);
            int i = br.read();
//            br.reset();
            int j = br.read();
            System.out.println(i);
            System.out.println(j);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

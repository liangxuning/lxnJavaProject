import java.io.*;

public class FileMarkTest {
    public static void main(String[] args) {
        File file = new File("/Users/liangxuning/Private/error.txt");
        FileInputStream fiss = null;
        try {
            fiss = new FileInputStream(file);
            System.out.println(fiss.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fiss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)){
            String value = null;
            br.skip(1500);
            int count = 0;
            while ((value = br.readLine()) != null) {
                //多行并一行
                System.out.println(value);
                count++ ;

            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

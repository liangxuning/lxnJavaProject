package Randomabcdfg;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Random;

public class ABCDEFG {
    public static void main(String[] args) {
//        readABCDEFG("a");
        Random b = new Random();
        StringBuffer sb = new StringBuffer();
        int j = 0;
        while(j<50) {
            int i = b.nextInt(7);
            switch (i) {
                case 0:
                    readABCDEFG("a");
                    sb.append("a");break;
                case 1:
                    readABCDEFG("b");
                    sb.append("b");break;
                case 2:
                    readABCDEFG("c");
                    sb.append("c");break;
                case 3:
                    readABCDEFG("d");
                    sb.append("d");break;
                case 4:
                    readABCDEFG("e");
                    sb.append("e");break;
                case 5:
                    readABCDEFG("f");
                    sb.append("f");break;
                case 6:
                    readABCDEFG("g");
                    sb.append("g");break;
            }
            j++;
        }
        System.out.println(sb.toString());

    }

    public static void readABCDEFG(String s) {
        URL url = ABCDEFG.class.getResource("/music/" + s + ".mp3");
        System.out.println(url.toString());
        File file = new File(url.getFile());
        try (FileInputStream fis=new FileInputStream(file);
             BufferedInputStream stream=new BufferedInputStream(fis)){
            System.out.println("开始播放" + s);
            Player player=new Player(stream);
            player.play();
            player.close();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("播放mp3失败");
        }
    }
}

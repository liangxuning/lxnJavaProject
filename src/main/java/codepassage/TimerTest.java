package codepassage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer类如果开始时间是过去的某个时间，那么执行时会一直执行，开始时间会首次执行
 */
public class TimerTest {
    public static void main(String[] args) throws ParseException, InterruptedException {

        Timer timer = new Timer();
        String startTime = "2018-10-14 20:45:00";
        Long cycle = 24*60*60*1000l;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(startTime);

        Date newDate = new Date(System.currentTimeMillis());

        double cycyleTime = date.getTime();
        if (newDate.getTime() > date.getTime()) {
            Long xTime = newDate.getTime() - date.getTime();
            Long zhouqi = xTime/cycle + 1;
            System.out.println(zhouqi);
            Long newTime = newDate.getTime() % cycle;
            double upTime = (date.getTime() + Math.floor(zhouqi)) % cycle;
            if (newTime < upTime) {
                cycyleTime = date.getTime() + Math.floor(zhouqi) * cycle;
            } else {
                cycyleTime = date.getTime() + Math.floor(zhouqi) * cycle;
            }
        }
        String resultTime = sdf.format(cycyleTime);
        System.out.println(resultTime);



//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("执行");
//            }
//        }, date, 60000);
//
//        Thread.sleep(180 * 1000l);
//        timer.cancel();
    }
}

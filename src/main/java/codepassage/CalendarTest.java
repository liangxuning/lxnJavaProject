package codepassage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarTest {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(week);

        Long timesl =  1545791513516l;
        String time = "2018-12-25 12:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(timesl);
//        calendar2.setTime(date);
        int week2 = calendar2.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(week2);
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(3);
        l.add(2);
        l.add(4);
        System.out.println(l.toArray());
    }
}

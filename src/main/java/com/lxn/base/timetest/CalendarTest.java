package com.lxn.base.timetest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(week);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(new Date().getTime());
        int week2 = calendar2.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(week2);
    }
}

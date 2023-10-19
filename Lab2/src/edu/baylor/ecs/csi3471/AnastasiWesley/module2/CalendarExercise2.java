package edu.baylor.ecs.csi3471.AnastasiWesley.module2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarExercise2 {
    public static void main (String[] args) throws ParseException {
        CalendarExercise2 ce2 = new CalendarExercise2();
        ce2.testMethod1(args[0]);
        ce2.testMethod2(args[0]);
    }
    public String processDate(String input) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-M-d H:m:s");
        simpleDateFormat.parseObject(input);
        Calendar cal = Calendar.getInstance();
        cal = simpleDateFormat.getCalendar();
        cal.add(Calendar.HOUR, 2);
        cal.add(Calendar.MINUTE, 2);
        return simpleDateFormat.format(cal.getTime());
    }
    public void testMethod1(String input) throws ParseException {
        for (int i = 0; i < 100; i++)
        {
            input = processDate(input);
        }
        System.out.println(input);
    }
    public void testMethod2(String input) throws ParseException {
        for (int i = 0; i < 24; i++)
        {
            input = processDate(input);
        }
        System.out.println(input);
    }
}

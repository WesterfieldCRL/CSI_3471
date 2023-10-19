package edu.baylor.ecs.csi3471.AnastasiWesley.module2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarExercise1 {
    public static void main(String[] args)
    {
        CalendarExercise1 ce1 = new CalendarExercise1();
        Calendar cal = Calendar.getInstance();
        ce1.testMethod1(cal);
    }
    public void printDate(Calendar input)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-M-d H:m:s");
        simpleDateFormat.setTimeZone(input.getTimeZone());
        System.out.println(simpleDateFormat.format((input.getTime())));
    }

    public void testMethod1(Calendar input)
    {
        input.add(Calendar.MONTH, -2);
        printDate(input);
        input.add(Calendar.MONTH,2);
        printDate(input);
    }
}

package com.example.hive;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;

public class CalendarUtil
{
    public static LocalDate selectedDate;

    public static String formattedDate(LocalDate date)
    {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(f);
    }

    public static String formattedTime(LocalTime time)
    {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(f);
    }

    public static String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(f);
    }

    public static ArrayList<LocalDate> daysInMonthArr(LocalDate date)
    {
        ArrayList<LocalDate> daysInMArr = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInM = yearMonth.lengthOfMonth();

        LocalDate firstOfM = CalendarUtil.selectedDate.withDayOfMonth(1);
        int dayOfW = firstOfM.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfW || i > daysInM + dayOfW)
            {
                daysInMArr.add(null);
            }
            else
                daysInMArr.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfW));
        }
        return  daysInMArr;
    }

    public static ArrayList<LocalDate> daysInWeekArr(LocalDate selectedDate)
    {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate cur = sundayForDate(selectedDate);
        LocalDate endD = cur.plusWeeks(1);

        while (cur.isBefore(endD))
        {
            days.add(cur);
            cur = cur.plusDays(1);
        }
        return days;
    }

    private static LocalDate sundayForDate(LocalDate cur)
    {
        LocalDate oneWeekAgo = cur.minusWeeks(1);

        while (cur.isAfter(oneWeekAgo))
        {
            if(cur.getDayOfWeek() == DayOfWeek.SUNDAY)
                return cur;

            cur = cur.minusDays(1);
        }
        return null;
    }

}

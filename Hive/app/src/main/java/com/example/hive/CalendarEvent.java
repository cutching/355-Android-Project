package com.example.hive;

import java.time.*;
import java.util.ArrayList;

public class CalendarEvent {
    public static ArrayList<CalendarEvent> eventsList = new ArrayList<>();
    private String Name;
    private LocalDate Date;
    private LocalTime Time;

    public static ArrayList<CalendarEvent> datesEvents(LocalDate date)
    {
        ArrayList<CalendarEvent> events = new ArrayList<>();

        for(CalendarEvent e : eventsList)
        {
            if(e.getDate().equals(date))
                events.add(e);
        }

        return events;
    }

    public CalendarEvent(String n, LocalDate d, LocalTime t)
    {
        this.Name = n;
        this.Date = d;
        this.Time = t;
    }

    public String getName()
    {
        return Name;
    }

    public LocalDate getDate()
    {
        return Date;
    }

    public LocalTime getTime()
    {
        return Time;
    }

    public void setName(String n)
    {
        this.Name = n;
    }

    public void setDate(LocalDate d)
    {
        this.Date = d;
    }

    public void setTime(LocalTime t)
    {
        this.Time = t;
    }
}

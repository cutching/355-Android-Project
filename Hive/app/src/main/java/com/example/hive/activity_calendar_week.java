package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.hive.CalendarUtil;

public class activity_calendar_week extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monYrTextV;
    private RecyclerView calendarRV;
    private ListView eventListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_week);

        calendarRV = findViewById(R.id.calendarRecyclerView);
        monYrTextV = findViewById(R.id.monthYearTV);
        eventListV = findViewById(R.id.eventListView);

        weekView();
    }

    private void setEventAdapter()
    {
        ArrayList<CalendarEvent> dayEvents = CalendarEvent.datesEvents(CalendarUtil.selectedDate);
        CalendarEventAdapter eventAdap = new CalendarEventAdapter(getApplicationContext(), dayEvents);
        eventListV.setAdapter(eventAdap);
    }


    private void weekView()
    {
        monYrTextV.setText(CalendarUtil.monthYearFromDate(CalendarUtil.selectedDate));
        ArrayList<LocalDate> daysInWeek = CalendarUtil.daysInWeekArr(CalendarUtil.selectedDate);

        CalendarAdapter calAdap = new CalendarAdapter(daysInWeek, this);

        RecyclerView.LayoutManager layoutMan = new GridLayoutManager(getApplicationContext(), 7);
        calendarRV.setLayoutManager(layoutMan);
        calendarRV.setAdapter(calAdap);

        setEventAdapter();

    }

    public void previousWeekAction(View view) {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusWeeks(1);
        weekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusWeeks(1);
        weekView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, activity_calendar_event.class));
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtil.selectedDate = date;
        weekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

}
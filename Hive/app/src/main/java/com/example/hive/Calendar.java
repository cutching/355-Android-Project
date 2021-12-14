package com.example.hive;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import android.content.Intent;

import static com.example.hive.CalendarUtil.daysInMonthArr;
import static com.example.hive.CalendarUtil.monthYearFromDate;
public class Calendar extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    TextView monthYearText;
    RecyclerView calendarRecyclerView;
   // LocalDate selectedDate; //should maybe be private



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);

        CalendarUtil.selectedDate = LocalDate.now();
        monthView();
    }

    private void monthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArr(CalendarUtil.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutMan = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutMan);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

   /* private ArrayList<String> daysInMonthArr(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CalendarUtil.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }*/

   /* private String dateToMonthYear(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }*/

    public void previousMonthAction(View view)
    {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
        monthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
        monthView();
    }

    public void weeklyAction(View view)
    {
        startActivity(new Intent(this, activity_calendar_week.class));
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtil.selectedDate = date;
            monthView();
        }
    }
}
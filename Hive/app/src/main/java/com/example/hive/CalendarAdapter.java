package com.example.hive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import android.graphics.Color;

class CalendarAdapter extends RecyclerView.Adapter<CalendarVH>
{
    private final ArrayList<LocalDate> daysOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> daysOfM, OnItemListener onItemL)
    {
        this.daysOfMonth = daysOfM;
        this.onItemListener = onItemL;
    }

    @NonNull
    @Override
    public CalendarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewT)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_dummy, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(daysOfMonth.size() > 15)
        {
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        }
        else
        {
            layoutParams.height = (int) parent.getHeight();
        }
        return new CalendarVH(view, onItemListener, daysOfMonth);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarVH calVH, int position)
    {
        final LocalDate date = daysOfMonth.get(position);
        if(date == null)
            calVH.dayOfM.setText("");
        else
        {
            calVH.dayOfM.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUtil.selectedDate))
            {
                calVH.parentV.setBackgroundColor(Color.GRAY);
            }
        }
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, LocalDate date);
    }
}

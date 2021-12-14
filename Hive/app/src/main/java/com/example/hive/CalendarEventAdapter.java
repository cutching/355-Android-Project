package com.example.hive;

import android.*;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class CalendarEventAdapter extends ArrayAdapter<CalendarEvent> {

    public CalendarEventAdapter(@NonNull Context context, List<CalendarEvent> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup p)
    {
        TextView eventDummyTextV;
        String eventTitle;
        CalendarEvent e = getItem(position);

        if(v == null)
        {
            v = LayoutInflater.from(getContext()).inflate(R.layout.calendar_event_dummy, p, false);
        }
        eventDummyTextV = v.findViewById(R.id.eventCellTV);
        eventTitle = e.getName(); //+ " " + CalendarUtil.formattedTime(e.getTime());
        eventDummyTextV.setText(eventTitle);
        return v;
    }
}

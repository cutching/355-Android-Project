package com.example.hive;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.time.*;
import java.util.ArrayList;

public class CalendarVH extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfM;
    private final CalendarAdapter.OnItemListener onItemListener;
    private final ArrayList<LocalDate> days;
    public final View parentV;


    public CalendarVH(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days)
    {
        super(itemView);
        parentV = itemView.findViewById(R.id.parentView);
        dayOfM = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
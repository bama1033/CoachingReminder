package com.example.martin.coachingreminder;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Martin on 12.12.2015.
 */
public class MyAdapter extends CursorAdapter {
    private final LayoutInflater mInflater;


    public MyAdapter(Context context, Cursor cur) {
        super(context, cur, false);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cur, ViewGroup parent) {
        return mInflater.inflate(R.layout.listview_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cur) {
        String event_Title = cur.getString(cur.getColumnIndex(CalendarContract.Events.TITLE));
        Date event_Start = new Date(cur.getLong(cur.getColumnIndex(CalendarContract.Events.DTSTART)));

        SimpleDateFormat formatter5 = new SimpleDateFormat("HH:mm dd.MMM yyyy");

        String formats1 = formatter5.format(event_Start);
        ((TextView) view.findViewById(R.id.textTitle)).setText(event_Title);
        ((TextView) view.findViewById(R.id.textDate)).setText(formats1);

    }
}

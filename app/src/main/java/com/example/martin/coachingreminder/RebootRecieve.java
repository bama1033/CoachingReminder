package com.example.martin.coachingreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.GregorianCalendar;

public class RebootRecieve extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        scheduleAlarms(context.getApplicationContext());
    }

    private void scheduleAlarms(Context context) {
        final SharedPreferences s1 = context.getSharedPreferences("Titel", Context.MODE_PRIVATE);
        final SharedPreferences s2 = context.getSharedPreferences("Date", Context.MODE_PRIVATE);
        final SharedPreferences s3 = context.getSharedPreferences("realDate", Context.MODE_PRIVATE);
        final SharedPreferences s4 = context.getSharedPreferences("Iteration", Context.MODE_PRIVATE);
        final SharedPreferences s5 = context.getSharedPreferences("Question", Context.MODE_PRIVATE);
       /*
        if(s1.getString("Titel", null).equals(null)) {
            return;
        }
        else {*/
            String vtitle = s1.getString("Titel", null);
            String vstart = s2.getString("Date", null);
            Long notitime = s3.getLong("realDate", 0);
            String iteration = s4.getString("Iteration", null);
            String question = s5.getString("Question", null);

            Intent intentAlarm = new Intent(context.getApplicationContext(), AlarmReciever.class);
            intentAlarm.putExtra("Titel", vtitle);
            intentAlarm.putExtra("Date", vstart);
            intentAlarm.putExtra("realDate", notitime);
            intentAlarm.putExtra("Iteration", iteration);
            intentAlarm.putExtra("Question", question);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, notitime, PendingIntent.getBroadcast(context.getApplicationContext(), 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
       // }
    }
}
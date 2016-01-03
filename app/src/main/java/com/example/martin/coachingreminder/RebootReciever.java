package com.example.martin.coachingreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class RebootReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")||intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")) {
            scheduleAlarms(context.getApplicationContext());
        }
    }

    private void scheduleAlarms(Context context) {
        final SharedPreferences s1 = context.getSharedPreferences("Titel", Context.MODE_PRIVATE);
        final SharedPreferences s2 = context.getSharedPreferences("Date", Context.MODE_PRIVATE);
        final SharedPreferences s3 = context.getSharedPreferences("realDate", Context.MODE_PRIVATE);
        final SharedPreferences s4 = context.getSharedPreferences("Iteration", Context.MODE_PRIVATE);
        final SharedPreferences s5 = context.getSharedPreferences("Question", Context.MODE_PRIVATE);

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

        new HelperClass().setAlarm(context, intentAlarm);
    }
}
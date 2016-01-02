package com.example.martin.coachingreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Martin on 02.01.2016.
 */
public class AlarmCreater {
        public void setAlarm (Context context, Intent intentAlarm ){
            Bundle bundle = intentAlarm.getExtras();
            Long notitime = bundle.getLong("realDate");
             AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
             alarmManager.set(AlarmManager.RTC_WAKEUP, notitime, PendingIntent.getBroadcast(context.getApplicationContext(), 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        }
}

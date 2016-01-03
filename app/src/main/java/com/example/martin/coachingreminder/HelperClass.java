package com.example.martin.coachingreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by Martin on 02.01.2016.
 */
public class HelperClass {
        public void setAlarm (Context context, Intent intentAlarm ){
            Bundle bundle = intentAlarm.getExtras();
            Long notitime = bundle.getLong("realDate");
             AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
             alarmManager.set(AlarmManager.RTC_WAKEUP, notitime, PendingIntent.getBroadcast(context.getApplicationContext(), 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        }
    public void enableReceiver(Context context){
        ComponentName alarm = new ComponentName(context, AlarmReciever.class);
        ComponentName reboot = new ComponentName(context, RebootReciever.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(alarm,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(reboot,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
    public void disableReceiver(Context context){
        ComponentName alarm = new ComponentName(context, AlarmReciever.class);
        ComponentName reboot = new ComponentName(context, RebootReciever.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(alarm,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(reboot,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}

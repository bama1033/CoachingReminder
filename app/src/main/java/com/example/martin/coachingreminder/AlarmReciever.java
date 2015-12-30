package com.example.martin.coachingreminder;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Martin on 26.11.2015.
 */
public class AlarmReciever extends BroadcastReceiver {


    long[] pattern = {
            50,   //Off before vibration
            100, 100,  //on-off
    };

        @Override
        public void onReceive (Context context, Intent intent){
        try {



            Bundle bundle = intent.getExtras();
            String vtitle = bundle.getString("Titel");
            String vstart = bundle.getString("Date");
            String question =  bundle.getString("Question");
            Long timestamp = bundle.getLong("realDate");
            String iteration =  bundle.getString("Iteration");

            if  (iteration.equals("2") || iteration.equals("3")|| iteration.equals("6")) {
                String[] replyChoices = context.getResources().getStringArray(R.array.reply_choices2);

                Intent resultIntent = new Intent(context, QuestionSelectorActivity.class);
                resultIntent.putExtra("Question", question);
                resultIntent.putExtra("realDate", timestamp);
                resultIntent.putExtra("Iteration", iteration);
                resultIntent.putExtra("Titel", vtitle);
                resultIntent.putExtra("Date", vstart);

                RemoteInput remoteInput = new RemoteInput.Builder(MobileMainActivity.EXTRA_VOICE_REPLY)
                        .setLabel(question)
                        .setChoices(replyChoices)
                        .setAllowFreeFormInput(false)
                        .build();

                PendingIntent PendingIntenta =
                        PendingIntent.getActivity(context, (int) System.currentTimeMillis(), resultIntent, 0);

                NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_reply,
                        "Reply",
                        PendingIntenta)
                        .addRemoteInput(remoteInput)
                        .build();

                Notification notification =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launchernew2)
                                .setContentTitle(vtitle + " " + vstart)
                                .setContentText(question)
                                .extend(new NotificationCompat.WearableExtender().addAction(action))
                                .setContentIntent(PendingIntenta)
                                .setVibrate(pattern)
                                .setOngoing(true)
                                .setAutoCancel(true)
                                .build();

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(context);

                // notificationId allows you to update the notification later on.
                int notificationId = 1;
                notificationManager.notify(notificationId, notification);
            }

            else {
                String[] replyChoices = context.getResources().getStringArray(R.array.reply_choices1);

                Intent resultIntent = new Intent(context, QuestionSelectorActivity.class);
                resultIntent.putExtra("Question", question);
                resultIntent.putExtra("realDate", timestamp);
                resultIntent.putExtra("Iteration", iteration);
                resultIntent.putExtra("Titel", vtitle);
                resultIntent.putExtra("Date", vstart);

                RemoteInput remoteInput = new RemoteInput.Builder(MobileMainActivity.EXTRA_VOICE_REPLY)
                        .setLabel(question)
                        .setChoices(replyChoices)
                                //um speak und Emojis als Antwort zu disablen .setAllowFreeFormInput(false)
                        .build();

                PendingIntent PendingIntenta =
                        PendingIntent.getActivity(context, (int) System.currentTimeMillis(), resultIntent, 0);

                NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_reply,
                        "Reply",
                        PendingIntenta)
                        .addRemoteInput(remoteInput)
                        .build();

                Notification notification =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launchernew2)
                                .setContentTitle(vtitle + " " + vstart)
                                .setContentText(question)
                                .extend(new NotificationCompat.WearableExtender().addAction(action))
                                .setContentIntent(PendingIntenta)
                                .setVibrate(pattern)
                                .setOngoing(true)
                                .setAutoCancel(true)
                                .build();

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(context);

                // notificationId allows you to update the notification later on.
                int notificationId = 1;
                notificationManager.notify(notificationId, notification);
            }


        } catch (Exception e) {
            Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }
}
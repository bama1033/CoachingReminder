package com.example.martin.coachingreminder;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MobileMainActivity extends AppCompatActivity {
    public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";

    //sd
    SharedPreferences q1;
    SharedPreferences q2 ;
    SharedPreferences q3 ;
    SharedPreferences q4 ;
    SharedPreferences q5;
    SharedPreferences q6;

    SharedPreferences s1;
    SharedPreferences s2 ;
    SharedPreferences s3 ;
    SharedPreferences s4 ;
    SharedPreferences s5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_main);
        q1 = getSharedPreferences("Q1", MODE_PRIVATE);
        q2 = getSharedPreferences("Q2", MODE_PRIVATE);
        q3 = getSharedPreferences("Q3", MODE_PRIVATE);
        q4 = getSharedPreferences("Q4", MODE_PRIVATE);
        q5 = getSharedPreferences("Q5", MODE_PRIVATE);
        q6 = getSharedPreferences("Q6", MODE_PRIVATE);

        final SharedPreferences s1 = getSharedPreferences("Titel", MODE_PRIVATE);
        final SharedPreferences s2 = getSharedPreferences("Date", MODE_PRIVATE);
        final SharedPreferences s3 = getSharedPreferences("realDate", MODE_PRIVATE);
        final SharedPreferences s4 = getSharedPreferences("Iteration", MODE_PRIVATE);
        final SharedPreferences s5 = getSharedPreferences("Question", MODE_PRIVATE);


        //buttons beschroften
        final Button mButtonResults = (Button) findViewById(R.id.button);
        final Button mButtonSearch = (Button) findViewById(R.id.button6);
        final Button mButtonResend = (Button) findViewById(R.id.button4);

        final EditText SearchText = (EditText) findViewById(R.id.textView1);
        final TextView NextText = (TextView) findViewById(R.id.textView19);
        final ListView lv = (ListView) findViewById(R.id.listView);
        String next = s1.getString("Titel", "-");
        NextText.setText(next);


        mButtonResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(intent);
            }
        });


        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x =SearchText.getText().toString();
                populatelistview(x);
            }
        });

        mButtonResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vtitle = s1.getString("Titel", null);
                String vstart = s2.getString("Date", null);
                Long notitime = s3.getLong("realDate", 0);
                String iteration = s4.getString("Iteration", null);
                String question = s5.getString("Question", null);

                Intent intentAlarm = new Intent(getApplicationContext(), AlarmReciever.class);
                intentAlarm.putExtra("Titel", vtitle);
                intentAlarm.putExtra("Date", vstart);
                intentAlarm.putExtra("realDate", notitime);
                intentAlarm.putExtra("Iteration", iteration);
                intentAlarm.putExtra("Question", question);


                if (vtitle == null) {
                    Toast.makeText(getApplicationContext(), "First search for an Event", Toast.LENGTH_SHORT).show();
                }

                else {
                    new AlarmCreater().setAlarm(getApplicationContext(), intentAlarm);
                }
            }
        });
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //alertMessage();
                final String vtitle = ((TextView) view.findViewById(R.id.textTitle)).getText().toString();
                final String vstart = ((TextView) view.findViewById(R.id.textDate)).getText().toString();

                String question = "How do u feel now after the meeting?";
                String iteration = "1";

                SimpleDateFormat sdfr = new SimpleDateFormat("HH:mm dd.MMM yyyy");
                Date date = null;

                try {
                    date = sdfr.parse(vstart);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long timestamp = 0;
                if (date != null) {
                    timestamp = date.getTime();
                }

                //   time = event_Start.getTime()+ 1000*24*60*60*1000;
                Long notitime = new GregorianCalendar().getTimeInMillis();
                //TODO auf 1h runterstellen
                notitime = timestamp+ 3*60*60*1000;

                Intent intentAlarm = new Intent(getApplicationContext(), AlarmReciever.class);
                intentAlarm.putExtra("Titel", vtitle);
                intentAlarm.putExtra("Date", vstart);
                intentAlarm.putExtra("realDate", notitime);
                intentAlarm.putExtra("Iteration", iteration);
                intentAlarm.putExtra("Question", question);

                s1.edit().putString("Titel", vtitle).apply();
                s2.edit().putString("Date", vstart).apply();
                s3.edit().putLong("realDate", notitime).apply();
                s4.edit().putString("Iteration", iteration).apply();
                s5.edit().putString("Question", question).apply();

                new AlarmCreater().setAlarm(getApplicationContext(),intentAlarm);

                Toast.makeText(getApplicationContext(), "Notification for the " + vstart + " has been scheduled", Toast.LENGTH_LONG).show();
                //a.setEnabled(true);
                clearSD();
                // kann man hinzufügen
                NextText.setText(vtitle);
                lv.setAdapter(null);
            }
        });
    }

    private void clearSD() {
        q1.edit().remove("Q1").commit();
        q2.edit().remove("Q2").commit();
        q3.edit().remove("Q3").commit();
        q4.edit().remove("Q4").commit();
        q5.edit().remove("Q5").commit();
        q6.edit().remove("Q6").commit();
       // String clear="";
      /*  q1.edit().putString("Q1", clear).apply();
        q2.edit().putString("Q2", clear).apply();
        q3.edit().putString("Q3", clear).apply();
        q4.edit().putString("Q4", clear).apply();
        q5.edit().putString("Q5", clear).apply();
        q6.edit().putString("Q6", clear).apply();*/
    }
    private void clearSDs(){
        String clear="";
        s1.edit().putString("Titel", clear).apply();
        s2.edit().putString("Date", clear).apply();
        s3.edit().putString("realDate", clear).apply();
        s4.edit().putString("Iteration", clear).apply();
        s5.edit().putString("Question", clear).apply();
    }

    private void populatelistview(String input) {

        final Cursor cur;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Events.CONTENT_URI;

        final ListView lv = (ListView) findViewById(R.id.listView);
        //  String selection ;
        String selection = "(" + CalendarContract.Events.TITLE + " LIKE  ?)";
        String[] selectionArgs = new String[]{"%"+input+"%" };
        try {
            // Submit the query and get a Cursor object back.
            cur = cr.query(uri, null, selection, selectionArgs, CalendarContract.Events.DTSTART+ " DESC");

            lv.setAdapter(new MyAdapter(getApplicationContext(), cur));
            int y =lv.getCount();
            if (y == 0){
                Toast.makeText(getApplicationContext(), "No events found", Toast.LENGTH_SHORT).show();
            }

        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "Permisson for Calendar not given?", Toast.LENGTH_LONG).show();
            Log.d("CHECK", "Permisson for Calendar not given?");
        }
    }


    public void onBackPressed() {

    }
}


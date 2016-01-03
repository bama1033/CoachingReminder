package com.example.martin.coachingreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionSelectorActivity extends AppCompatActivity {
    SharedPreferences s1;
    SharedPreferences s2 ;
    SharedPreferences s3 ;
    SharedPreferences s4 ;
    SharedPreferences s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        final SharedPreferences s1 = getSharedPreferences("Titel", MODE_PRIVATE);
        final SharedPreferences s2 = getSharedPreferences("Date", MODE_PRIVATE);
        final SharedPreferences s3 = getSharedPreferences("realDate", MODE_PRIVATE);
        final SharedPreferences s4 = getSharedPreferences("Iteration", MODE_PRIVATE);
        final SharedPreferences s5 = getSharedPreferences("Question", MODE_PRIVATE);

        String question = getIntent().getStringExtra("Question");
        final Long realdate = getIntent().getLongExtra("realDate", 1);
        String iteration = getIntent().getStringExtra("Iteration");
        String reply = getIntent().getStringExtra("EXTRA_VOICE_REPLY");
        final String vtitle = getIntent().getStringExtra("Titel");
        final String vstart =getIntent().getStringExtra("Date");


        final Button save =(Button) findViewById(R.id.button7);

        final RadioButton ryes = (RadioButton) findViewById(R.id.radioButtonx);
        final RadioButton rno = (RadioButton) findViewById(R.id.radioButtonx2);
        final TextView Questionfield = (TextView) findViewById(R.id.textView);
        final TextView answerfield = (TextView) findViewById(R.id.textView16);
        final TextView text = (TextView) findViewById(R.id.textView15);
        final EditText etext = (EditText) findViewById(R.id.editText);


        Questionfield.setText(question);


        switch (iteration) {
            case "1": {
                final String sd = "Q1";
                setprogress(iteration);
                iteration = "2";

                final SharedPreferences q1 = getSharedPreferences(sd, MODE_PRIVATE);

                ryes.setText(R.string.Good);
                rno.setText(R.string.Bad);

                final long realdatenew = realdate + 24 * 60 * 60 * 1000;

                question = "Could you implement the planned steps so far?";

                final Intent intentAlarm = new Intent(getApplicationContext(), AlarmReciever.class);
                intentAlarm.putExtra("Titel", vtitle);
                intentAlarm.putExtra("Date", vstart);
                intentAlarm.putExtra("realDate", realdatenew);
                intentAlarm.putExtra("Iteration", iteration);
                intentAlarm.putExtra("Question", question);


                ryes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            rno.setChecked(false);
                        }
                    }
                });

                rno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rno.isChecked()) {
                            ryes.setChecked(false);
                        }
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            q1.edit().putString(sd, ryes.getText().toString()).apply();
                            allmethods(intentAlarm, realdate);
                        }
                        if (rno.isChecked()) {
                            q1.edit().putString(sd, rno.getText().toString()).apply();
                            allmethods(intentAlarm, realdate);
                        }
                    }
                });

                if (reply == null) {
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                    if (remoteInput != null) {
                        reply = remoteInput.getCharSequence(MobileMainActivity.EXTRA_VOICE_REPLY).toString();
                        q1.edit().putString(sd, reply).apply(); //oder commit();=====??? commit sendet result boolen zurück, ist aber unnötig
                        text.setText("Watch answer: ");
                        answerfield.setText(reply);
                        allmethods(intentAlarm, realdate);
                    }
                }
                break;
            }
            case "2": {
                final String sd = "Q2";
                setprogress(iteration);
                final String yiteration = "3";
                final String niteration = "3.5";
                final SharedPreferences q2 = getSharedPreferences(sd, MODE_PRIVATE);

                ryes.setText("Yes");
                rno.setText("No");

                final String question1 = "Did the steps work out as planned?";
                final String question2 = "What did go wrong?";
                final Intent intentAlarm = new Intent(getApplicationContext(), AlarmReciever.class);
                intentAlarm.putExtra("Titel", vtitle);
                intentAlarm.putExtra("Date", vstart);

                ryes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            rno.setChecked(false);
                        }
                    }
                });

                rno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rno.isChecked()) {
                            ryes.setChecked(false);
                        }
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            q2.edit().putString(sd, ryes.getText().toString()).apply();
                            //TODO aufein Tag reduziert q2
                            final long realdatenew = realdate + 24 * 60 * 60 * 1000;
                            intentAlarm.putExtra("realDate", realdatenew);
                            intentAlarm.putExtra("Iteration", yiteration);
                            intentAlarm.putExtra("Question", question1);
                            allmethods(intentAlarm, realdate);
                        }
                        if (rno.isChecked()) {
                            q2.edit().putString(sd, rno.getText().toString()).apply();
                            //TODO aufein Tag reduziert q2
                            final long realdatenew = realdate + 24 * 60 * 60 * 1000;
                            intentAlarm.putExtra("realDate", realdatenew);
                            intentAlarm.putExtra("Iteration", yiteration);
                            intentAlarm.putExtra("Question", question1);
                            allmethods(intentAlarm, realdate);
                        }
                    }
                });

                if (reply == null) {
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                    if (remoteInput != null) {
                        reply = remoteInput.getCharSequence(MobileMainActivity.EXTRA_VOICE_REPLY).toString();
                        q2.edit().putString(sd, reply).apply(); //oder commit();=====??? commit sendet result boolen zurück, ist aber unnötig
                        save.setEnabled(false);
                        text.setText(R.string.WatchAnswer);
                        answerfield.setText(reply);
                        // if (reply.equals("yes") || reply.equals("Yes") || reply.equals("ja") || reply.equals("Ja")){
                        //TODO wird ja sofort gesendet, von daher nixhts ändern?  q2
                        final long realdatenew = realdate + 24 * 60 * 60 * 1000;
                        intentAlarm.putExtra("realDate", realdatenew);
                        intentAlarm.putExtra("Iteration", yiteration);
                        intentAlarm.putExtra("Question", question1);
                        managealarm(realdate, intentAlarm);
                        sintent(getApplicationContext());
                        saved();
                        //  }
                  /*  if (reply.equals("no") || reply.equals("No") || reply.equals("Nein") || reply.equals("nein")){
                        final long realdatenew = realdate+ 3*60*60*1000;
                        intentAlarm.putExtra("realDate", realdate);
                        intentAlarm.putExtra("Iteration", niteration);
                        intentAlarm.putExtra("Question", question2);
                        managealarm(realdate, intentAlarm);
                    }`*/
                    }
                }
                break;
            }
            case "3": {
                final String sd = "Q3";
                setprogress(iteration);
                final String yiteration = "4";
                final String niteration = "5";
                final SharedPreferences q3 = getSharedPreferences(sd, MODE_PRIVATE);

                ryes.setText("Yes");
                rno.setText("No");

                final String question1 = "How good did the steps work so far? How is ur current situation?";
                final String question2 = "Why did the steps not work";
                final Intent intentAlarm = new Intent(getApplicationContext(), AlarmReciever.class);
                intentAlarm.putExtra("Titel", vtitle);
                intentAlarm.putExtra("Date", vstart);

                ryes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            rno.setChecked(false);
                        }
                    }
                });

                rno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rno.isChecked()) {
                            ryes.setChecked(false);
                        }
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            q3.edit().putString(sd, ryes.getText().toString()).apply();
                            //TODO wird ja sofort gesendet, von daher nixhts ändern? Falsch komtm auf Antwort an q3
                            final long realdatenew = realdate + 24 * 60 * 60 * 1000;
                            intentAlarm.putExtra("realDate", realdatenew);
                            intentAlarm.putExtra("Iteration", yiteration);
                            intentAlarm.putExtra("Question", question1);
                            allmethods(intentAlarm, realdate);
                        }
                        if (rno.isChecked()) {
                            q3.edit().putString(sd, rno.getText().toString()).apply();
                            //TODO wird ja sofort gesendet, von daher nixhts ändern? Falsch komtm auf Antwort an q3
                            final long realdatenew = realdate + 24 * 60 * 60 * 1000;
                            intentAlarm.putExtra("realDate", realdatenew);
                            intentAlarm.putExtra("Iteration", niteration);
                            intentAlarm.putExtra("Question", question2);
                            allmethods(intentAlarm, realdate);
                        }
                    }
                });

                if (reply == null) {
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                    if (remoteInput != null) {
                        reply = remoteInput.getCharSequence(MobileMainActivity.EXTRA_VOICE_REPLY).toString();
                        q3.edit().putString(sd, reply).apply(); //oder commit();=====??? commit sendet result boolen zurück, ist aber unnötig
                        save.setEnabled(false);
                        text.setText("Watch answer: ");
                        answerfield.setText(reply);

                        if (reply.equals("yes") || reply.equals("Yes") || reply.equals("ja") || reply.equals("Ja")) {
                            //TODO realdate mit new austauschen q3
                            final long realdatenew = realdate + 24 * 60 * 60 * 1000;
                            intentAlarm.putExtra("realDate", realdatenew);
                            intentAlarm.putExtra("Iteration", yiteration);
                            intentAlarm.putExtra("Question", question1);
                            allmethods(intentAlarm, realdate);
                        }
                        if (reply.equals("no") || reply.equals("No") || reply.equals("Nein") || reply.equals("nein")) {
                            //TODO realdate mit new austauschen 3
                            final long realdatenew = realdate + 10 * 1000;
                            intentAlarm.putExtra("realDate", realdatenew);
                            intentAlarm.putExtra("Iteration", niteration);
                            intentAlarm.putExtra("Question", question2);
                            allmethods(intentAlarm, realdate);
                        } else {
                            Toast.makeText(getApplicationContext(), "Please answer with yes or no", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            }
            case "4": {
                final String sd = "Q4";
                setprogress(iteration);
                final SharedPreferences q4 = getSharedPreferences(sd, MODE_PRIVATE);

                ryes.setVisibility(View.INVISIBLE);
                rno.setVisibility(View.INVISIBLE);
                etext.setVisibility(View.VISIBLE);


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etext.equals("")) {
                            Toast.makeText(getApplicationContext(), R.string.NoNotes, Toast.LENGTH_SHORT).show();
                        } else {
                            q4.edit().putString(sd, etext.getText().toString()).apply();
                            Toast.makeText(getApplicationContext(), R.string.ThankYou, Toast.LENGTH_SHORT).show();
                            clearsds();
                            sintent(getApplicationContext());
                        }
                    }
                });

                if (reply == null) {
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                    if (remoteInput != null) {
                        reply = remoteInput.getCharSequence(MobileMainActivity.EXTRA_VOICE_REPLY).toString();
                        q4.edit().putString(sd, reply).apply(); //oder commit();=====??? commit sendet result boolen zurück, ist aber unnötig
                        save.setEnabled(false);
                        text.setText(R.string.WatchAnswer);
                        answerfield.setText(reply);
                        sintent(getApplicationContext());
                        Toast.makeText(getApplicationContext(), R.string.ThankYou, Toast.LENGTH_SHORT).show();
                        clearsds();
                    }
                }

                break;
            }
            case "5": {
                final String sd = "Q5";
                setprogress(iteration);
                iteration = "6";

                final SharedPreferences q5 = getSharedPreferences(sd, MODE_PRIVATE);

                //TODO 1 Tage einf+ügen und mit realdate wechslen
                final long realdatenew = realdate + 24 * 60 * 60 * 1000;

                question = "Would be another Peer-Coaching-session helpful?";

                final Intent intentAlarm = new Intent(getApplicationContext(), AlarmReciever.class);
                intentAlarm.putExtra("Titel", vtitle);
                intentAlarm.putExtra("Date", vstart);
                intentAlarm.putExtra("realDate", realdatenew);
                intentAlarm.putExtra("Iteration", iteration);
                intentAlarm.putExtra("Question", question);

                ryes.setVisibility(View.INVISIBLE);
                rno.setVisibility(View.INVISIBLE);
                etext.setVisibility(View.VISIBLE);


                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        q5.edit().putString(sd, etext.getText().toString()).apply();
                        managealarm(realdate, intentAlarm);
                        sintent(getApplicationContext());
                        saved();
                    }
                });


                if (reply == null) {
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                    if (remoteInput != null) {
                        reply = remoteInput.getCharSequence(MobileMainActivity.EXTRA_VOICE_REPLY).toString();
                        q5.edit().putString(sd, reply).apply(); //oder commit();=====??? commit sendet result boolen zurück, ist aber unnötig
                        save.setEnabled(false);
                        text.setText("Watch answer: ");
                        answerfield.setText(reply);
                        managealarm(realdate, intentAlarm);
                        sintent(getApplicationContext());
                        saved();
                    }
                }
                break;
            }
            case "6": {
                final String sd = "Q6";
                setprogress(iteration);

                final SharedPreferences q6 = getSharedPreferences(sd, MODE_PRIVATE);

                ryes.setText(" Yes\uD83D\uDE00");
                rno.setText(" No \uD83D\uDE26");

                ryes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            rno.setChecked(false);
                        }
                    }
                });

                rno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rno.isChecked()) {
                            ryes.setChecked(false);
                        }
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ryes.isChecked()) {
                            q6.edit().putString(sd, ryes.getText().toString()).apply();
                            Toast.makeText(getApplicationContext(), R.string.ThankYou, Toast.LENGTH_SHORT).show();
                            clearsds();
                            sintent(getApplicationContext());
                        }
                        if (rno.isChecked()) {
                            q6.edit().putString(sd, rno.getText().toString()).apply();
                            Toast.makeText(getApplicationContext(), R.string.ThankYou, Toast.LENGTH_SHORT).show();
                            clearsds();
                            sintent(getApplicationContext());
                        }
                    }
                });

                if (reply == null) {
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
                    if (remoteInput != null) {
                        reply = remoteInput.getCharSequence(MobileMainActivity.EXTRA_VOICE_REPLY).toString();
                        q6.edit().putString(sd, reply).apply(); //oder commit();=====??? commit sendet result boolen zurück, ist aber unnötig
                        save.setEnabled(false);
                        text.setText(R.string.WatchAnswer);
                        answerfield.setText(reply);
                        Toast.makeText(getApplicationContext(), R.string.ThankYou, Toast.LENGTH_SHORT).show();
                        clearsds();
                        sintent(getApplicationContext());
                    }
                }
                break;
            }
        }
    }
    private  void setprogress(String iteration){
        final TextView progress =(TextView) findViewById(R.id.textView20);
        progress.setText("Reminder "+iteration+" of 5");
    }
    private void sintent(Context c) {
        Intent intent = new Intent(c, MobileMainActivity.class);
        startActivity(intent);
    }

    private void  saved(){
    Toast.makeText(getApplicationContext(), "Answer saved in results!", Toast.LENGTH_SHORT).show();
    }

    private void managealarm(Long realdate, Intent intentAlarm){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, realdate, PendingIntent.getBroadcast(getApplicationContext(), 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    private void  savesds(Intent intentAlarm){

        Bundle bundle = intentAlarm.getExtras();
        String vtitle = bundle.getString("Titel");
        String vstart = bundle.getString("Date");
        String question =  bundle.getString("Question");
        Long timestamp = bundle.getLong("realDate");
        String iteration =  bundle.getString("Iteration");

        final SharedPreferences s1 = getSharedPreferences("Titel", MODE_PRIVATE);
        final SharedPreferences s2 = getSharedPreferences("Date", MODE_PRIVATE);
        final SharedPreferences s3 = getSharedPreferences("realDate", MODE_PRIVATE);
        final SharedPreferences s4 = getSharedPreferences("Iteration", MODE_PRIVATE);
        final SharedPreferences s5 = getSharedPreferences("Question", MODE_PRIVATE);

        s1.edit().putString("Titel", vtitle).apply();
        s2.edit().putString("Date", vstart).apply();
        s3.edit().putLong("realDate", timestamp).apply();
        s4.edit().putString("Iteration", iteration).apply();
        s5.edit().putString("Question", question).apply();
    }

    private void allmethods(Intent intentAlarm, Long realdate){
        savesds(intentAlarm);
        managealarm(realdate, intentAlarm);
        saved();
        sintent(getApplicationContext());
    }
    //clears lastnoti sds and disables receiver
    private  void clearsds(){
        final SharedPreferences s1 = getSharedPreferences("Titel", MODE_PRIVATE);
        final SharedPreferences s2 = getSharedPreferences("Date", MODE_PRIVATE);
        final SharedPreferences s3 = getSharedPreferences("realDate", MODE_PRIVATE);
        final SharedPreferences s4 = getSharedPreferences("Iteration", MODE_PRIVATE);
        final SharedPreferences s5 = getSharedPreferences("Question", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = s1.edit();
        editor1.clear().commit();
        SharedPreferences.Editor editor2 = s2.edit();
        editor2.clear().commit();
        SharedPreferences.Editor editor3 = s3.edit();
        editor3.clear().commit();
        SharedPreferences.Editor editor4 = s4.edit();
        editor4.clear().commit();
        SharedPreferences.Editor editor5 = s5.edit();
        editor5.clear().commit();

        new HelperClass().disableReceiver(getApplicationContext());

    }
}


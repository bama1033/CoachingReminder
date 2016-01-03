package com.example.martin.coachingreminder;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Martin on 12.12.2015.
 */
public class ResultsActivity  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        SharedPreferences q1 = getSharedPreferences("Q1", MODE_PRIVATE);
        SharedPreferences q2 = getSharedPreferences("Q2", MODE_PRIVATE);
        SharedPreferences q3 = getSharedPreferences("Q3", MODE_PRIVATE);
        SharedPreferences q4 = getSharedPreferences("Q4", MODE_PRIVATE);
        SharedPreferences q5 = getSharedPreferences("Q5", MODE_PRIVATE);
        SharedPreferences q6 = getSharedPreferences("Q6", MODE_PRIVATE);
        SharedPreferences f1 = getSharedPreferences("F1", MODE_PRIVATE);

        Button mButtonBack = (Button) findViewById(R.id.button8);
        Button mButtonSave = (Button) findViewById(R.id.button2);

        TextView r1 =(TextView) findViewById(R.id.textView3);
        TextView r2 =(TextView) findViewById(R.id.textView4);
        TextView r3 =(TextView) findViewById(R.id.textView5);
        TextView r4 =(TextView) findViewById(R.id.textView17);
        TextView r5 =(TextView) findViewById(R.id.textView6);
        TextView r6 =(TextView) findViewById(R.id.textView7);
        EditText a4 =(EditText) findViewById(R.id.editText2);


        String ret1 = q1.getString("Q1",null);
        String ret2 = q2.getString("Q2",null);
        String ret3 = q3.getString("Q3",null);
        String ret4 = q4.getString("Q4",null);
        String ret5 = q5.getString("Q5",null);
        String ret6 = q6.getString("Q6",null);
        String ret7 = f1.getString("F1",null);


        r1.setText(ret1);
        r2.setText(ret2);
        r3.setText(ret3);
        r4.setText(ret4);
        r5.setText(ret5);
        r6.setText(ret6);
        a4.setText(ret7);

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MobileMainActivity.class);
                startActivity(intent);
            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences f1 = getSharedPreferences("F1", MODE_PRIVATE);
                EditText a4 =(EditText) findViewById(R.id.editText2);
                f1.edit().putString("F1", a4.getText().toString()).apply();
                Toast.makeText(getApplicationContext(), "Freetext saved", Toast.LENGTH_LONG).show();
            }
        });
    }
}
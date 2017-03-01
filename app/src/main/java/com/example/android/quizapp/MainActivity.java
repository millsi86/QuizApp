package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void t75ImageClick(View view) {
        RadioButton radioT75 = (RadioButton) findViewById(R.id.Radio_T75);
        radioT75.setChecked(true);
        setT75(view);
    }

    public void well96ImageClick(View view) {
        RadioButton radio96well = (RadioButton) findViewById(R.id.Radio_96well);
        radio96well.setChecked(true);
        set96w(view);
    }

    public void plate90mmImageClick(View view) {
        RadioButton radio90mm = (RadioButton) findViewById(R.id.Radio_90mm);
        radio90mm.setChecked(true);
        set90mm(view);
    }

    public void setT75(View view) {
        TextView Text = (TextView) findViewById(R.id.WorkingCapacityText);
        Text.setText(R.string.CapacityTextFlasks);
    }

    public void set96w(View view) {
        TextView Text = (TextView) findViewById(R.id.WorkingCapacityText);
        Text.setText(R.string.CapacityTextWells);
    }

    public void set90mm(View view) {
        TextView Text = (TextView) findViewById(R.id.WorkingCapacityText);
        Text.setText(R.string.CapacityTextPlates);
    }


}



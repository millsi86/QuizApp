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
        T75CapacityText(view);
        T75CapacityHint(view);
        T75IntBB(view);
        T75IntBBPlus(view);
        T75IntNuvoSml(view);
        T75IntNuvoLrg(view);
        T75IntNuvoDual(view);
    }

    public void set96w(View view) {
        WellCapacityText(view);
        WellCapacityHint(view);
        WellIntBB(view);
        WellIntBBPlus(view);
        WellIntNuvoSml(view);
        WellIntNuvoLge(view);
        WellIntNuvoDual(view);
    }

    public void set90mm(View view) {
        PlateCapacityText(view);
        PlateCapacityHint(view);
        PlateIntBB(view);
        PlateIntBBPlus(view);
        PlateIntNuvoSml(view);
        PlateIntNuvoLge(view);
        PlateIntNuvoDual(view);
    }

    public void T75CapacityText (View view){
        TextView Text = (TextView) findViewById(R.id.WorkingCapacityText);
        Text.setText(R.string.CapacityTextFlasks);
    }

    public void WellCapacityText (View view){
        TextView Text = (TextView) findViewById(R.id.WorkingCapacityText);
        Text.setText(R.string.CapacityTextWells);
    }

    public void PlateCapacityText (View view){
        TextView Text = (TextView) findViewById(R.id.WorkingCapacityText);
        Text.setText(R.string.CapacityTextPlates);
    }

    public void T75CapacityHint (View view) {
        TextView Text = (TextView) findViewById(R.id.WorkingCapacity);
        Text.setHint(R.string.CapacityHintFlasks);
    }

    public void WellCapacityHint (View view) {
        TextView Text = (TextView) findViewById(R.id.WorkingCapacity);
        Text.setHint(R.string.CapacityHintWells);
    }

    public void PlateCapacityHint (View view) {
        TextView Text = (TextView) findViewById(R.id.WorkingCapacity);
        Text.setHint(R.string.CapacityHintPlates);
    }

    public void T75IntBB (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize1);
        Text.setText(R.string.IntBBFlask);
    }

    public void T75IntBBPlus (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize2);
        Text.setText(R.string.IntBBplusFlask);
    }

    public void T75IntNuvoLrg (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize3);
        Text.setText(R.string.IntNuvoSmlFlask);
    }

    public void T75IntNuvoSml (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize4);
        Text.setText(R.string.IntNuvoLgeFlask);
    }

    public void T75IntNuvoDual (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize5);
        Text.setText(R.string.IntNuvoDualFlask);
    }

    public void WellIntBB (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize1);
        Text.setText(R.string.IntBBWell);
    }

    public void WellIntBBPlus (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize2);
        Text.setText(R.string.IntBBplusWell);
    }

    public void WellIntNuvoSml (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize3);
        Text.setText(R.string.IntNuvoSmlWell);
    }

    public void WellIntNuvoLge (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize4);
        Text.setText(R.string.IntNuvoLgeWell);
    }

    public void WellIntNuvoDual (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize5);
        Text.setText(R.string.IntNuvoDualWell);
    }

    public void PlateIntBB (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize1);
        Text.setText(R.string.IntBBPlate);
    }

    public void PlateIntBBPlus (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize2);
        Text.setText(R.string.IntBBplusPlate);
    }

    public void PlateIntNuvoSml (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize3);
        Text.setText(R.string.IntNuvoSmlPlate);
    }

    public void PlateIntNuvoLge (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize4);
        Text.setText(R.string.IntNuvoLgePlate);
    }

    public void PlateIntNuvoDual (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize5);
        Text.setText(R.string.IntNuvoDualPlate);
    }

}


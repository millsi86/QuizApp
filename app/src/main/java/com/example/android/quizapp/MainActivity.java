package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.quizapp.R.id.Radio_T75;
import static com.example.android.quizapp.R.id.WorkingCapacity;
import static com.example.android.quizapp.R.id.WorkingCapacityText;
import static com.example.android.quizapp.R.id.radioGroup1;


public class MainActivity extends AppCompatActivity {

    private RadioGroup Group1;
    private RadioButton Flask, Well, Plate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Group1 = (RadioGroup) findViewById(radioGroup1);
    }

    public void submit (View view){
        String CapType = getRadio1ID(view);
        EditText CapacityId = (EditText) findViewById(WorkingCapacity);
        Integer Volume = Integer.parseInt(CapacityId.getText().toString());
        Integer WorkstationSize;

        if(CapType == "Flask"){
            WorkstationSize = FlaskWorkstationSize(Volume);
        }

        else if(CapType == "Well"){
            WorkstationSize = WellWorkstationSize(Volume);
        }

        else {WorkstationSize = DishWorkstationSize(Volume);}

    }

    private Integer FlaskWorkstationSize(Integer MainVolume){
        Integer Volume1 = 77;
        Integer Volume2 = 115;
        Integer Volume3 = 292;
        Integer Volume4 = 310;
        // Integer Volume5 = 565;  --> Size of SCi-tive Dual
        Integer workstationSize;

        return workstationSize = WorkstationVolumeCalc(MainVolume, Volume1, Volume2, Volume3, Volume4);
    }

    private Integer WellWorkstationSize(Integer MainVolume){
        Integer Volume1 = 199;
        Integer Volume2 = 399;
        Integer Volume3 = 753;
        Integer Volume4 = 798;
        // Integer Volume5 = 1507;  --> Size of SCi-tive Dual
        Integer workstationSize;

        return workstationSize = WorkstationVolumeCalc(MainVolume, Volume1, Volume2, Volume3, Volume4);
    }

    private Integer DishWorkstationSize(Integer MainVolume){
        Integer Volume1 = 180;
        Integer Volume2 = 360;
        Integer Volume3 = 680;
        Integer Volume4 = 720;
        // Integer Volume5 = 1360;  --> Size of SCi-tive Dual
        Integer workstationSize;

        return workstationSize = WorkstationVolumeCalc(MainVolume, Volume1, Volume2, Volume3, Volume4);
    }

    private Integer WorkstationVolumeCalc(Integer Volume, Integer Size1, Integer Size2, Integer Size3, Integer Size4){
        Integer workstationSize;
        if(Volume <= Size1){ workstationSize = 1; }
        else if(Volume <= Size2){ workstationSize = 2; }
        else if(Volume <= Size3){ workstationSize = 3; }
        else if(Volume <= Size4){ workstationSize = 4; }
        else{workstationSize = 5;}
        Toast.makeText(this, workstationSize.toString(), Toast.LENGTH_SHORT).show();
        return workstationSize;
    }

    private String getRadio1ID (View view){
        String CapacityType;
        Flask = (RadioButton) findViewById(Radio_T75);
        Well = (RadioButton) findViewById(R.id.Radio_96well);
        Plate = (RadioButton) findViewById(R.id.Radio_90mm);

        int selectedId = Group1.getCheckedRadioButtonId();

        if(selectedId == Flask.getId()){
            CapacityType = "Flask";
        }
        else if(selectedId == Well.getId()){
            CapacityType = "Well";
        }
        else {
            CapacityType = "Plate";
        }

        //Toast.makeText(this, CapacityType, Toast.LENGTH_SHORT).show();  /Line used for testing Group1 Radio Button section

        return CapacityType;
    }


    // Declarations to get radio buttons changing when the corresponting image is selected:

    public void t75ImageClick(View view) {
        RadioButton radioT75 = (RadioButton) findViewById(Radio_T75);
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

    // Declarations to get text changing with the Media Type radio Buttons:

    public void setT75(View view) {
        T75CapacityText(view);
        T75CapacityHint(view);
        T75IntCapacityText(view);
        T75IntBB(view);
        T75IntBBPlus(view);
        T75IntNuvoSml(view);
        T75IntNuvoLrg(view);
        T75IntNuvoDual(view);
        T75IntSci(view);
    }

    public void set96w(View view) {
        WellCapacityText(view);
        WellCapacityHint(view);
        WellIntCapacityText(view);
        WellIntBB(view);
        WellIntBBPlus(view);
        WellIntNuvoSml(view);
        WellIntNuvoLge(view);
        WellIntNuvoDual(view);
        WellIntSci(view);
    }

    public void set90mm(View view) {
        PlateCapacityText(view);
        PlateCapacityHint(view);
        PlateIntCapacityText(view);
        PlateIntBB(view);
        PlateIntBBPlus(view);
        PlateIntNuvoSml(view);
        PlateIntNuvoLge(view);
        PlateIntNuvoDual(view);
        PlateIntSci(view);
    }

    public void T75CapacityText (View view){
        TextView Text = (TextView) findViewById(WorkingCapacityText);
        Text.setText(R.string.CapacityTextFlasks);
    }

    public void WellCapacityText (View view){
        TextView Text = (TextView) findViewById(WorkingCapacityText);
        Text.setText(R.string.CapacityTextWells);
    }

    public void PlateCapacityText (View view){
        TextView Text = (TextView) findViewById(WorkingCapacityText);
        Text.setText(R.string.CapacityTextPlates);
    }

    public void T75CapacityHint (View view) {
        TextView Text = (TextView) findViewById(WorkingCapacity);
        Text.setHint(R.string.CapacityHintFlasks);
    }

    public void WellCapacityHint (View view) {
        TextView Text = (TextView) findViewById(WorkingCapacity);
        Text.setHint(R.string.CapacityHintWells);
    }

    public void PlateCapacityHint (View view) {
        TextView Text = (TextView) findViewById(WorkingCapacity);
        Text.setHint(R.string.CapacityHintPlates);
    }

    public void T75IntCapacityText (View view){
        TextView Text = (TextView) findViewById(R.id.InterlockCapacityText);
        Text.setText(R.string.CapacityInterlockFlasks);
    }

    public void WellIntCapacityText (View view){
        TextView Text = (TextView) findViewById(R.id.InterlockCapacityText);
        Text.setText(R.string.CapacityInterlockWells);
    }

    public void PlateIntCapacityText (View view){
        TextView Text = (TextView) findViewById(R.id.InterlockCapacityText);
        Text.setText(R.string.CapacityInterlockPlates);
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

    public void T75IntSci (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize6);
        Text.setText(R.string.IntSciFlask);
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

    public void WellIntSci (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize6);
        Text.setText(R.string.IntSciWell);
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

    public void PlateIntSci (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize6);
        Text.setText(R.string.IntSciPlate);
    }
}


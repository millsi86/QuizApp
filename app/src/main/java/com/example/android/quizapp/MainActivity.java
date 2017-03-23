package com.example.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.quizapp.R.id.Check_Anaerobic;
import static com.example.android.quizapp.R.id.Check_Hyperoxic;
import static com.example.android.quizapp.R.id.Check_Hypoxic;
import static com.example.android.quizapp.R.id.IntSize1;
import static com.example.android.quizapp.R.id.IntSize2;
import static com.example.android.quizapp.R.id.IntSize3;
import static com.example.android.quizapp.R.id.IntSize4;
import static com.example.android.quizapp.R.id.IntSize5;
import static com.example.android.quizapp.R.id.Radio_T75;
import static com.example.android.quizapp.R.id.WorkingCapacity;
import static com.example.android.quizapp.R.id.WorkingCapacityText;
import static com.example.android.quizapp.R.id.radioGroup1;
import static com.example.android.quizapp.R.id.radioGroup2;


public class MainActivity extends AppCompatActivity {

    private RadioGroup Group1;
    private RadioGroup Group2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Group1 = (RadioGroup) findViewById(radioGroup1);
        Group2 = (RadioGroup) findViewById(radioGroup2);
    }

    public void submit (View view){
        String CapType = getRadio1ID();
        EditText CapacityId = (EditText) findViewById(WorkingCapacity);
        boolean sCapacityId = TextUtils.isEmpty(CapacityId.getText());

        if(sCapacityId){
            Toast.makeText(getApplicationContext(), "Please enter the amount of Media Storage required", Toast.LENGTH_LONG).show();
            return;
        } // Error checking for Working Capacity entered
        
        Integer Volume = Integer.parseInt(CapacityId.getText().toString());
        Integer InternalSize;  // Numerical value for workstation size
        Integer InterlockSize;  // Numerical value for interlock size
        String WorkstationSize;
        String EmailMessage;
        String WorkstationChoice = "";
        boolean HasAnoxicOption = false;
        boolean HasHyperoxicOption = false;
        boolean HyperError = false;

        if(CapType.equals("Flask")){InternalSize = FlaskWorkstationSize(Volume);}
        else if(CapType.equals("Well")){InternalSize = WellWorkstationSize(Volume);}
        else {InternalSize = DishWorkstationSize(Volume);}
        InterlockSize = InterlockSizeSub();
        WorkstationSize = CalculateWorkstationSize(InternalSize, InterlockSize);

        CheckBox Anaerobic = (CheckBox) findViewById(Check_Anaerobic);
        boolean isAnaerobic = Anaerobic.isChecked();
        CheckBox Hypoxic = (CheckBox) findViewById(Check_Hypoxic);
        boolean isHypoxic = Hypoxic.isChecked();
        CheckBox Hyperoxic = (CheckBox) findViewById(Check_Hyperoxic);
        boolean isHyperoxic = Hyperoxic.isChecked();

        if(!isAnaerobic && !isHypoxic && !isHyperoxic){
            Toast.makeText(getApplicationContext(), "Please enter the type(s) of operating environment required", Toast.LENGTH_LONG).show();
            return;
        } // Error checking for Operation Conditions selected

        if(isAnaerobic && !isHypoxic) {
            if (WorkstationSize.equals("BB")) {WorkstationChoice = "BugBox";}
            else if (WorkstationSize.equals("BB+")) {WorkstationChoice = "BugBox Plus";}
            else if (WorkstationSize.equals("N400")) {WorkstationChoice = "Concept 400";}
            else if (WorkstationSize.equals("N500")) {WorkstationChoice = "Concept 500";}
            else if (WorkstationSize.equals("N1000")) {WorkstationChoice = "Concept 1000";}
            else if (WorkstationSize.equals("SCI Sgl")) {WorkstationChoice = "SCI-tive";}
            else {WorkstationChoice = "SCI-tive Dual";}
        }

        if(!isAnaerobic && isHypoxic){
            if (WorkstationSize.equals("BB")) {WorkstationChoice = "BugBox-M";}
            else if (WorkstationSize.equals("BB+")) {WorkstationChoice = "Invivo 300";}
            else if (WorkstationSize.equals("N400")) {WorkstationChoice = "Invivo 400";}
            else if (WorkstationSize.equals("N500")) {WorkstationChoice = "Invivo 500";}
            else if (WorkstationSize.equals("N1000")) {WorkstationChoice = "Invivo 1000";}
            else if (WorkstationSize.equals("SCI Sgl")) {WorkstationChoice = "SCI-tive";}
            else {WorkstationChoice = "SCI-tive Dual";}
        }

        if(isAnaerobic && isHypoxic){
            if (WorkstationSize.equals("BB")) {WorkstationChoice = "BugBox-M"; HasAnoxicOption = true;}
            else if (WorkstationSize.equals("BB+")) {WorkstationChoice = "BugBox-M Plus"; HasAnoxicOption = true;}
            else if (WorkstationSize.equals("N400")) {WorkstationChoice = "Concept-M 400";}
            else if (WorkstationSize.equals("N500")) {WorkstationChoice = "Concept-M 500";}
            else if (WorkstationSize.equals("N1000")) {WorkstationChoice = "Concept-M 1000";}
            else if (WorkstationSize.equals("SCI Sgl")) {WorkstationChoice = "SCI-tive"; HasAnoxicOption = true;}
            else {WorkstationChoice = "SCI-tive Dual"; HasAnoxicOption = true;}
        }

        if(isHyperoxic){
            if(WorkstationSize.equals("N400")){HasHyperoxicOption = true; HyperError = false;}
            else if(WorkstationSize.equals("N500")){HasHyperoxicOption = true; HyperError = false;}
            else if(WorkstationSize.equals("N1000")){HasHyperoxicOption = true; HyperError = false;}
            else{HyperError = true;}
        }

        EmailMessage = "Enquiry for a " + WorkstationChoice + " Workstation";
        EmailMessage += "\nOptions Required are:";
        if(HasAnoxicOption){EmailMessage += "\nAnoxic Operating Mode";}
        if(HasHyperoxicOption){EmailMessage += "\nHyperoxic Operating Mode";}
        if(HyperError){EmailMessage += "\nHyperoxic Mode requested but not available";}
        if(!HasAnoxicOption && !isHyperoxic && !HyperError){EmailMessage += "\nNone";}
        sendEmail(EmailMessage, WorkstationChoice);

    } // Main working code

    private void sendEmail(String message, String workstationChoice){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        String Title = workstationChoice + " Enquiry";
        String[] SalesEmail = {"Sales@Ruskinn.com"};
        intent.putExtra(Intent.EXTRA_SUBJECT, Title);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_EMAIL,SalesEmail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        startActivity(Intent.createChooser(intent, "Send Email")); // lets the user choose which e-mail app to use to send the order
    } // Sub for creating e-mail

    private String CalculateWorkstationSize (Integer InternalSize, Integer InterlockSize){
        String WorkstationSize;
        if(InternalSize == 1){
            if(InterlockSize == 1){WorkstationSize = "BB";}
            else if(InterlockSize == 2){WorkstationSize = "BB+";}
            else if(InterlockSize == 3){WorkstationSize = "N400";}
            else if(InterlockSize == 4){WorkstationSize = "N500";}
            else if(InterlockSize == 5){WorkstationSize = "N1000";}
            else {WorkstationSize = "SCI Sgl";}
        }
        else if(InternalSize == 2) {
            if(InterlockSize <= 3) {WorkstationSize = "N400";}
            else if(InterlockSize == 4) {WorkstationSize = "N500";}
            else if(InterlockSize == 5) {WorkstationSize = "N1000";}
            else {WorkstationSize = "SCI Sgl";}
        }
        else if(InternalSize == 3){
            if(InterlockSize <= 5){WorkstationSize = "N500";}
            else{WorkstationSize = "SCI Sgl";}
        }
        else if(InternalSize == 4) {
            if (InterlockSize <= 5) {WorkstationSize = "N500";}
            else{WorkstationSize = "SCI Dual";}
        }
        else{WorkstationSize = "SCI Dual";}

        // Toast.makeText(this, WorkstationSize, Toast.LENGTH_SHORT).show();

        return WorkstationSize;
    } // Calculates the Workstation size based on Internal and Interlock Volumes

    private Integer InterlockSizeSub (){
        Integer IntSize;
        RadioButton Button1 = (RadioButton) findViewById(IntSize1);
        RadioButton Button2 = (RadioButton) findViewById(IntSize2);
        RadioButton Button3 = (RadioButton) findViewById(IntSize3);
        RadioButton Button4 = (RadioButton) findViewById(IntSize4);
        RadioButton Button5 = (RadioButton) findViewById(IntSize5);

        int selectedId = Group2.getCheckedRadioButtonId();

        if(selectedId == Button1.getId()){IntSize = 1;}
        else if(selectedId == Button2.getId()){IntSize = 2;}
        else if(selectedId == Button3.getId()){IntSize = 3;}
        else if(selectedId == Button4.getId()){IntSize = 4;}
        else if(selectedId == Button5.getId()){IntSize = 5;}
        else {IntSize = 6;}

        return IntSize;
    } // Gets Interlock Size from RadioGroup2

    private Integer FlaskWorkstationSize(Integer MainVolume){
        Integer Volume1 = 77; // BB & BB+
        Integer Volume2 = 115; // N400
        Integer Volume3 = 292; // N500 & Sgl SCI
        Integer Volume4 = 310; // N500 Dual SCI
        // Integer Volume5 = 565;  --> Size of SCi-tive Dual

        return WorkstationVolumeCalc(MainVolume, Volume1, Volume2, Volume3, Volume4);
    } // Specifies the Internal Volumes available for Workstations for T75 Flasks

    private Integer WellWorkstationSize(Integer MainVolume){
        Integer Volume1 = 199;
        Integer Volume2 = 399;
        Integer Volume3 = 753;
        Integer Volume4 = 798;
        // Integer Volume5 = 1507;  --> Size of SCi-tive Dual

        return WorkstationVolumeCalc(MainVolume, Volume1, Volume2, Volume3, Volume4);
    } // Specifies the Internal Volumes available for Workstations for 96 Well Plates

    private Integer DishWorkstationSize(Integer MainVolume){
        Integer Volume1 = 180;
        Integer Volume2 = 360;
        Integer Volume3 = 680;
        Integer Volume4 = 720;
        // Integer Volume5 = 1360;  --> Size of SCi-tive Dual

        return WorkstationVolumeCalc(MainVolume, Volume1, Volume2, Volume3, Volume4);
    } // Specifies the Internal Volumes available for Workstations for 90mm Petri-Dishes

    private Integer WorkstationVolumeCalc(Integer Volume, Integer Size1, Integer Size2, Integer Size3, Integer Size4){
        Integer workstationSize;
        if(Volume <= Size1){ workstationSize = 1; }
        else if(Volume <= Size2){ workstationSize = 2; }
        else if(Volume <= Size3){ workstationSize = 3; }
        else if(Volume <= Size4){ workstationSize = 4; }
        else{workstationSize = 5;}
        // Toast.makeText(this, workstationSize.toString(), Toast.LENGTH_SHORT).show();
        return workstationSize;
    } // Calculates the required workstation volume size for all Media types. Returns an Integer relating to the size

    private String getRadio1ID (){
        String CapacityType;
        RadioButton Flask, Well; // ,Plate
        Flask = (RadioButton) findViewById(Radio_T75);
        Well = (RadioButton) findViewById(R.id.Radio_96well);
        // Plate = (RadioButton) findViewById(R.id.Radio_90mm);

        int selectedId = Group1.getCheckedRadioButtonId();

        if(selectedId == Flask.getId()){CapacityType = "Flask";}
        else if(selectedId == Well.getId()){CapacityType = "Well";}
        else {CapacityType = "Plate";}

        //Toast.makeText(this, CapacityType, Toast.LENGTH_SHORT).show();  /Line used for testing Group1 Radio Button section

        return CapacityType;
    } // Checks RadioGrou1 for the media type selected and returns the type as a string


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
        TextView Text = (TextView) findViewById(IntSize1);
        Text.setText(R.string.IntBBFlask);
    }

    public void T75IntBBPlus (View view){
        TextView Text = (TextView) findViewById(IntSize2);
        Text.setText(R.string.IntBBplusFlask);
    }

    public void T75IntNuvoLrg (View view){
        TextView Text = (TextView) findViewById(IntSize3);
        Text.setText(R.string.IntNuvoSmlFlask);
    }

    public void T75IntNuvoSml (View view){
        TextView Text = (TextView) findViewById(IntSize4);
        Text.setText(R.string.IntNuvoLgeFlask);
    }

    public void T75IntNuvoDual (View view){
        TextView Text = (TextView) findViewById(IntSize5);
        Text.setText(R.string.IntNuvoDualFlask);
    }

    public void T75IntSci (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize6);
        Text.setText(R.string.IntSciFlask);
    }

    public void WellIntBB (View view){
        TextView Text = (TextView) findViewById(IntSize1);
        Text.setText(R.string.IntBBWell);
    }

    public void WellIntBBPlus (View view){
        TextView Text = (TextView) findViewById(IntSize2);
        Text.setText(R.string.IntBBplusWell);
    }

    public void WellIntNuvoSml (View view){
        TextView Text = (TextView) findViewById(IntSize3);
        Text.setText(R.string.IntNuvoSmlWell);
    }

    public void WellIntNuvoLge (View view){
        TextView Text = (TextView) findViewById(IntSize4);
        Text.setText(R.string.IntNuvoLgeWell);
    }

    public void WellIntNuvoDual (View view){
        TextView Text = (TextView) findViewById(IntSize5);
        Text.setText(R.string.IntNuvoDualWell);
    }

    public void WellIntSci (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize6);
        Text.setText(R.string.IntSciWell);
    }

    public void PlateIntBB (View view){
        TextView Text = (TextView) findViewById(IntSize1);
        Text.setText(R.string.IntBBPlate);
    }

    public void PlateIntBBPlus (View view){
        TextView Text = (TextView) findViewById(IntSize2);
        Text.setText(R.string.IntBBplusPlate);
    }

    public void PlateIntNuvoSml (View view){
        TextView Text = (TextView) findViewById(IntSize3);
        Text.setText(R.string.IntNuvoSmlPlate);
    }

    public void PlateIntNuvoLge (View view){
        TextView Text = (TextView) findViewById(IntSize4);
        Text.setText(R.string.IntNuvoLgePlate);
    }

    public void PlateIntNuvoDual (View view){
        TextView Text = (TextView) findViewById(IntSize5);
        Text.setText(R.string.IntNuvoDualPlate);
    }

    public void PlateIntSci (View view){
        TextView Text = (TextView) findViewById(R.id.IntSize6);
        Text.setText(R.string.IntSciPlate);
    }
}


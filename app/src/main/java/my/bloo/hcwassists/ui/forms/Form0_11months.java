package my.bloo.hcwassists.ui.forms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import my.bloo.hcwassists.R;

public class Form0_11months extends AppCompatActivity {

    TextInputEditText spo2_input, bloodpressue_input, heartRate_input, respi_input;
    RadioGroup tempRange_radioGroup, consciousLevel_radioGroup, oxygenDelivery_radioGroup, capilary_radioGroup;

    int intRb01, intRb02, intRb03, intRb04, intRb05;

    int intSpo2Score, intBloodPressureScore, intHearRateScore, intRespiratoryScore, intTempRangeScore, intConsciousScore, intOxygenScore, intCapilaryScore;

    Button calc_button;

    int checkedRadioGroupOxygen, intOxygenOptionSelected;
    int checkedRadioGroupCapilary, intCapilaryOptionSelected;
    int checkedRadioGroupConsciusLevel, intConsciousOptionSelected;
    int checkedRadioGroupTemperature, intTemperatureOptionSelected;


    String id, result, age, dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form011months);

        calc_button = findViewById(R.id.btnCalc_01);

        calc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog2();
            }
        });

    }

    void calculateSpo2() {

        spo2_input = findViewById(R.id.text_input_spo2_01);

        int intSpo2Val = Integer.valueOf(spo2_input.getText().toString().trim());

        if (intSpo2Val >= 94) {
            intSpo2Score = 0;
        } else if ((intSpo2Val >= 92 && intSpo2Val <= 93)) {
            intSpo2Score = 1;
        } else if ((intSpo2Val < 92)) {
            intSpo2Score = 3;
        } else {
            intSpo2Score = 0;
        }
    }


    void calculateBloodPressure() {
        bloodpressue_input = findViewById(R.id.text_input_bp_01);

        int intBPVal = Integer.valueOf(bloodpressue_input.getText().toString().trim());

        if (intBPVal >= 70 && intBPVal <= 100) {
            intBloodPressureScore = 0;
        } else if ((intBPVal > 100 && intBPVal <= 110)) {
            intBloodPressureScore = 1;
        } else if ((intBPVal > 110)) {
            intBloodPressureScore = 3;
        } else if ((intBPVal < 60)) {
            intBloodPressureScore = 3;
        }
    }

    void calculateHeartRate() {
        heartRate_input = findViewById(R.id.text_input_hr_01);

        int intHRVal = Integer.valueOf(heartRate_input.getText().toString().trim());

        if (intHRVal >= 110 && intHRVal < 160) {
            intHearRateScore = 0;
        } else if (intHRVal >= 160 && intHRVal < 170) {
            intHearRateScore = 1;
        } else if (intHRVal >= 170) {
            intHearRateScore = 3;
        } else if (intHRVal >= 100 && intHRVal < 110) {
            intHearRateScore = 1;
        } else if (intHRVal < 100) {
            intHearRateScore = 3;
        }

    }


    void calculateRespiratoryRate() {
        respi_input = findViewById(R.id.text_input_resp_01);

        int intRespiVal = Integer.valueOf(respi_input.getText().toString().trim());

        if (intRespiVal > 70) {
            intRespiratoryScore = 3;
        } else if (intRespiVal > 50 && intRespiVal <= 70) {
            intRespiratoryScore = 1;
        } else if (intRespiVal > 30 && intRespiVal <= 50) {
            intRespiratoryScore = 0;
        } else if (intRespiVal > 20 && intRespiVal <= 30) {
            intRespiratoryScore = 1;
        } else if (intRespiVal < 20) {
            intRespiratoryScore = 3;
        }
    }


    void calculateTemperature() {
        tempRange_radioGroup = (RadioGroup) findViewById(R.id.radioGroupTempRange_01);
        checkedRadioGroupTemperature = tempRange_radioGroup.getCheckedRadioButtonId();
        intRb01 = findViewById(R.id.rBtn01_tR_01_Less365).getId();
        intRb02 = findViewById(R.id.rBtn01_tR_02_36_38).getId();
        intRb03 = findViewById(R.id.rBtn01_tR_03_375_379).getId();
        intRb04 = findViewById(R.id.rBtn01_tR_04_38_39).getId();
        intRb05 = findViewById(R.id.rBtn01_tR_05_40_above).getId();

        int selected = -1;
        if (checkedRadioGroupTemperature == intRb01) {
            selected = 1;
        } else if (checkedRadioGroupTemperature == intRb02) {
            selected = 2;
        } else if (checkedRadioGroupTemperature == intRb03) {
            selected = 3;
        } else if (checkedRadioGroupTemperature == intRb04) {
            selected = 4;
        } else if (checkedRadioGroupTemperature == intRb05) {
            selected = 5;
        }

        intTemperatureOptionSelected = selected;

        if (intTemperatureOptionSelected == 1) {
            intTempRangeScore = 3;
        } else if (intTemperatureOptionSelected == 2) {
            intTempRangeScore = 0;
        } else if (intTemperatureOptionSelected == 3) {
            intTempRangeScore = 1;
        } else if (intTemperatureOptionSelected == 4) {
            intTempRangeScore = 1;
        } else if (intTemperatureOptionSelected == 5) {
            intTempRangeScore = 3;
        }

    }

    void calculateConsciousLevel() {
        consciousLevel_radioGroup = (RadioGroup) findViewById(R.id.radioGroupConscious_01);
        checkedRadioGroupConsciusLevel = consciousLevel_radioGroup.getCheckedRadioButtonId();
        intRb01 = findViewById(R.id.rBtn01_cL_01_alert).getId();
        intRb02 = findViewById(R.id.rBtn01_cL_02_verbal).getId();
        intRb03 = findViewById(R.id.rBtn01_cL_03_pain).getId();
        intRb04 = findViewById(R.id.rBtn01_cL_04_unconscious).getId();

        int selected = -1;
        if (checkedRadioGroupConsciusLevel == intRb01) {
            selected = 1;
        } else if (checkedRadioGroupConsciusLevel == intRb02) {
            selected = 2;
        } else if (checkedRadioGroupConsciusLevel == intRb03) {
            selected = 3;
        } else if (checkedRadioGroupConsciusLevel == intRb04) {
            selected = 4;
        } else if (checkedRadioGroupConsciusLevel == intRb05) {
            selected = 5;
        }

        intConsciousOptionSelected = selected;

        if (intConsciousOptionSelected == 1) {
            intConsciousScore = 0;
        } else if (intConsciousOptionSelected == 2) {
            intConsciousScore = 0;
        } else if (intConsciousOptionSelected == 3) {
            intConsciousScore = 3;
        } else if (intConsciousOptionSelected == 4) {
            intConsciousScore = 3;
        }


    }


    void calculateOxygen() {
        oxygenDelivery_radioGroup = (RadioGroup) findViewById(R.id.radioGroupOxygen_01);
        checkedRadioGroupOxygen = oxygenDelivery_radioGroup.getCheckedRadioButtonId();
        intRb01 = findViewById(R.id.rBtn01_oD_01_room).getId();
        intRb02 = findViewById(R.id.rBtn01_oD_02_nasal).getId();
        intRb03 = findViewById(R.id.rBtn01_oD_03_cpap).getId();
        intRb04 = findViewById(R.id.rBtn01_cL_04_nippv).getId();
        intRb05 = findViewById(R.id.rBtn01_cL_05_ventilated).getId();

        int selected = -1;
        if (checkedRadioGroupOxygen == intRb01) {
            selected = 1;
        } else if (checkedRadioGroupOxygen == intRb02) {
            selected = 2;
        } else if (checkedRadioGroupOxygen == intRb03) {
            selected = 3;
        } else if (checkedRadioGroupOxygen == intRb04) {
            selected = 4;
        } else if (checkedRadioGroupOxygen == intRb05) {
            selected = 5;
        }

        intOxygenOptionSelected = selected;

        if (intOxygenOptionSelected == 1) {
            intOxygenScore = 0;
        } else if (intOxygenOptionSelected == 2) {
            intOxygenScore = 1;
        } else if (intOxygenOptionSelected == 3) {
            intOxygenScore = 3;
        } else if (intOxygenOptionSelected == 4) {
            intOxygenScore = 3;
        } else if (intOxygenOptionSelected == 5) {
            intOxygenScore = 3;
        }

    }


    void calculateCapilary() {

        capilary_radioGroup = (RadioGroup) findViewById(R.id.radioGroupCapilary_01);

        checkedRadioGroupCapilary = capilary_radioGroup.getCheckedRadioButtonId();
        intRb01 = findViewById(R.id.rBtn01_capR_01_less2).getId();
        intRb02 = findViewById(R.id.rBtn01_capR_02_3_4sec).getId();
        intRb03 = findViewById(R.id.rBtn01_capR_03_5sec).getId();

        int selected = -1;
        if (checkedRadioGroupCapilary == intRb01) {
            selected = 1;
        } else if (checkedRadioGroupCapilary == intRb02) {
            selected = 2;
        } else if (checkedRadioGroupCapilary == intRb03) {
            selected = 3;
        } else if (checkedRadioGroupCapilary == intRb04) {
            selected = 4;
        } else if (checkedRadioGroupCapilary == intRb05) {
            selected = 5;
        }
        intCapilaryOptionSelected = selected;

        if (intCapilaryOptionSelected == 1) {
            intCapilaryScore = 0;
        } else if (intCapilaryOptionSelected == 2) {
            intCapilaryScore = 1;
        } else if (intCapilaryOptionSelected == 3) {
            intCapilaryScore = 3;
        }

    }

    void confirmDialog2() {
        calculateOxygen();
        calculateCapilary();
        calculateConsciousLevel();
        calculateTemperature();
        calculateSpo2();
        calculateBloodPressure();
        calculateHeartRate();
        calculateRespiratoryRate();

        String strRecommendation = "";

        int totalScore = intSpo2Score + intBloodPressureScore + intHearRateScore + intRespiratoryScore + intTempRangeScore + intConsciousScore + intOxygenScore + intCapilaryScore;

        if (totalScore >= 1 && totalScore <= 2) {
            strRecommendation = "observe 4-6 hours, alert the nurse in charge";
        } else if (totalScore >= 3 && totalScore <= 5) {
            strRecommendation = "observe every 2-4 hours, alert junior doctor/senior nurse";
        } else if (totalScore >= 6 && totalScore <= 7) {
            strRecommendation = "observe every 1-2 hours, alert senior doctor/specialist";
        } else if (totalScore >= 8) {
            strRecommendation = "Observe every 30 minutes- 1-hour, alert specialist/consultant";
        }


        if (totalScore >= 1 && totalScore <= 2) {
            new MaterialAlertDialogBuilder(this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog_Green)
                    .setTitle("Assesment:")
                    .setMessage("Score: " + totalScore + " " + strRecommendation)
                    .setPositiveButton("Ok", /* listener = */ null)
                    .show();
        } else if (totalScore >= 3 && totalScore <= 5) {
            new MaterialAlertDialogBuilder(this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog_Yellow)
                    .setTitle("Assesment:")
                    .setMessage("Score: " + totalScore + " " + strRecommendation)
                    .setPositiveButton("Ok", /* listener = */ null)
                    .show();
        } else if (totalScore >= 6 && totalScore <= 7) {
            new MaterialAlertDialogBuilder(this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog_Amber)
                    .setTitle("Assesment:")
                    .setMessage("Score: " + totalScore + " " + strRecommendation)
                    .setPositiveButton("Ok", /* listener = */ null)
                    .show();
        } else if (totalScore >= 8) {
            new MaterialAlertDialogBuilder(this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog_Red)
                    .setTitle("Assesment:")
                    .setMessage("Score: " + totalScore + " " + strRecommendation)
                    .setPositiveButton("Ok", /* listener = */ null)
                    .show();
        }


    }
}
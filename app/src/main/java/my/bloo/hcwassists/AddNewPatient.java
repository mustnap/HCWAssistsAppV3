package my.bloo.hcwassists;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNewPatient extends AppCompatActivity {

    private Button mPickDateButton;
    EditText name_input, age_input, dob_input;
    Button add_button;

    private TextInputEditText txtEditPatientDoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        // now register the text view and the button with
        // their appropriate IDs
        mPickDateButton = findViewById(R.id.pick_date_button);
        txtEditPatientDoB = findViewById(R.id.text_input_dob);

        // now create instance of the material date picker
        // builder make sure to add the "datePicker" which
        // is normal material date picker which is the first
        // type of the date picker in material design date
        // picker
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        // now define the properties of the
        // materialDateBuilder that is title text as SELECT A DATE
        materialDateBuilder.setTitleText("SELECT A DATE");

        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();

//....define min and max for example with LocalDateTime and ZonedDateTime or Calendar
        Calendar rightNow = Calendar.getInstance();
        Calendar beforeCutoff = Calendar.getInstance();
        beforeCutoff.add(Calendar.YEAR, -17);

        CalendarConstraints.DateValidator dateValidatorMin = DateValidatorPointForward.from(beforeCutoff.getTimeInMillis());
        CalendarConstraints.DateValidator dateValidatorMax = DateValidatorPointBackward.before(rightNow.getTimeInMillis());

        ArrayList<CalendarConstraints.DateValidator> listValidators =
                new ArrayList<CalendarConstraints.DateValidator>();
        listValidators.add(dateValidatorMin);
        listValidators.add(dateValidatorMax);
        CalendarConstraints.DateValidator validators = CompositeDateValidator.allOf(listValidators);
        constraintsBuilderRange.setValidator(validators);

        // now create the instance of the material date
        // picker

        materialDateBuilder.setCalendarConstraints(constraintsBuilderRange.build());
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        // handle select date button which opens the
        // material design date picker
        mPickDateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // getSupportFragmentManager() to
                        // interact with the fragments
                        // associated with the material design
                        // date picker tag is to get any error
                        // in logcat
                        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                    }
                });

        // now handle the positive button click from the
        // material design date picker
        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        // if the user clicks on the positive
                        // button that is ok button update the
                        // selected date
                        String dateString = DateFormat.format("dd-MMM-yyyy", new Date((long) selection)).toString();
                        txtEditPatientDoB.setText(dateString);
                        // in the above statement, getHeaderText
                        // is the selected date preview from the
                        // dialog

                    }
                });

        name_input = findViewById(R.id.text_input_patient_name);
        dob_input = findViewById(R.id.text_input_dob);

        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddNewPatient.this);
                myDB.addPatient(name_input.getText().toString().trim(),
                        dob_input.getText().toString());

            }
        });
    }
}
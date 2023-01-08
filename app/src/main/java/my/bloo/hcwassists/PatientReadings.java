package my.bloo.hcwassists;

import static android.content.ContentValues.TAG;
import static java.lang.Math.abs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientReadings extends AppCompatActivity {

    RecyclerView recyclerView3;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> patient_id, patient_name, patient_age, patient_dob;
    ArrayList<String> reading_datetime, reading_score;
    SinglePatientAdapter singlePatientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_readings);

        recyclerView3 = findViewById(R.id.recyclerView3);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        myDB = new MyDatabaseHelper(PatientReadings.this);
        patient_id = new ArrayList<>();
        reading_datetime = new ArrayList<>();
        reading_score = new ArrayList<>();

        if (getIntent().hasExtra("patient_name")) {
            //Getting Data from Intent
            getSupportActionBar().setTitle("Historical reading for: " + getIntent().getStringExtra("patient_name"));
        };

        storeReadingInArraysSinglePatient(getIntent().getStringExtra("patient_id").toString());

        singlePatientAdapter = new SinglePatientAdapter(PatientReadings.this, this, patient_id, reading_datetime, reading_score);
        recyclerView3.setAdapter(singlePatientAdapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(PatientReadings.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeReadingInArraysSinglePatient(String patientId) {

        Cursor cursor = myDB.readPatientReadings(Integer.valueOf(patientId));

        Log.d(TAG, " storeReadingInArraysSinglePatient ");

        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                Log.d(TAG, " while (cursor.moveToNext())  ");
                Log.d(TAG, " cursor.getString(0))  " + cursor.getString(0) );
                Log.d(TAG, " cursor.getString(1))  " + cursor.getString(1) );
                Log.d(TAG, " cursor.getString(2))  " + cursor.getString(2) );

                patient_id.add(cursor.getString(0));
                reading_datetime.add(cursor.getString(1));
                reading_score.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


}
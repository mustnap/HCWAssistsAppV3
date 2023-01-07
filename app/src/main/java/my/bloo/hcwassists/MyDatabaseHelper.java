package my.bloo.hcwassists;

import static java.lang.Math.abs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Patients.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "patient_list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_DOB = "dob";


    private static final String TABLE_NAME2 = "patient_reading";
    private static final String COLUMN_ID2 = "_id";
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_READING_DATETIME = "reading_dttm";
    private static final String COLUMN_SPO2_READING = "spo2_reading";
    private static final String COLUMN_SPO2_SCORE = "spo2_score";
    private static final String COLUMN_BP_READING = "bp_reading";
    private static final String COLUMN_BP_SCORE = "bp_score";
    private static final String COLUMN_HR_READING = "hr_reading";
    private static final String COLUMN_HR_SCORE = "hr_score";
    private static final String COLUMN_RESP_READING = "resp_reading";
    private static final String COLUMN_RESP_SCORE = "resp_score";
    private static final String COLUMN_TEMP_READING = "temp_reading";
    private static final String COLUMN_TEMP_SCORE = "temp_score";
    private static final String COLUMN_CONCLVL_READING = "conclvl_reading";
    private static final String COLUMN_CONCLVL_SCORE = "conclvl_score";
    private static final String COLUMN_O2DELIVERY_READING = "o2delivery_reading";
    private static final String COLUMN_O2DELIVERY_SCORE = "o2delivery_score";
    private static final String COLUMN_CAP_REFILL_READING = "cap_refill_reading";
    private static final String COLUMN_CAP_REFILL_SCORE = "cap_refill_score";
    private static final String COLUMN_TOTAL_SCORE = "total_score";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, " +
                COLUMN_DOB + " TEXT);";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PATIENT_ID + " INTEGER, " +
                COLUMN_READING_DATETIME + " TEXT, " +
                COLUMN_SPO2_READING + " TEXT, " +
                COLUMN_SPO2_SCORE + " INTEGER, " +
                COLUMN_BP_READING + " TEXT, " +
                COLUMN_BP_SCORE + " INTEGER, " +
                COLUMN_HR_READING + " TEXT, " +
                COLUMN_HR_SCORE + " INTEGER, " +
                COLUMN_RESP_READING + " TEXT, " +
                COLUMN_RESP_SCORE + " INTEGER, " +
                COLUMN_TEMP_READING + " TEXT, " +
                COLUMN_TEMP_SCORE + " INTEGER, " +
                COLUMN_CONCLVL_READING + " TEXT, " +
                COLUMN_CONCLVL_SCORE + " INTEGER, " +
                COLUMN_O2DELIVERY_READING + " TEXT, " +
                COLUMN_O2DELIVERY_SCORE + " INTEGER, " +
                COLUMN_CAP_REFILL_READING + " TEXT, " +
                COLUMN_CAP_REFILL_SCORE + " INTEGER, " +
                COLUMN_TOTAL_SCORE  + " INTEGER);";
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    void addPatient(String name, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, getAgeInMonths(dob));
        cv.put(COLUMN_DOB, dob);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addReadings(String patient_id, String spo2_reading, String spo2_score, String bp_reading, String bp_score, String hr_reading, String hr_score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));

        cv.put(COLUMN_PATIENT_ID, patient_id);
        cv.put(COLUMN_SPO2_READING, spo2_reading);
        cv.put(COLUMN_SPO2_SCORE, spo2_score);
        cv.put(COLUMN_READING_DATETIME, now.toString());
        long result = db.insert(TABLE_NAME2,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public Cursor readPatientReadings(Integer patientID){
        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE PATIENT patient_id = '" + patientID + "' ORDER BY " + COLUMN_READING_DATETIME + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, int age, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_AGE, getAgeInMonths(dob));

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    int getAgeInMonths(String dob) {
        int ageInMonths = 0;
        Date dateOfBirth;
        try {
            dateOfBirth=new SimpleDateFormat("dd-MMM-yyyy").parse(String.valueOf(dob));
        } catch (ParseException e) {
            e.printStackTrace();
            dateOfBirth = new Date();
        }
        Date currentDate = new Date();
        LocalDate ldCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ldDateOfBirth = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(ldCurrentDate),
                YearMonth.from(ldDateOfBirth)
        );

        ageInMonths = (int) monthsBetween;

        return abs(ageInMonths);
    }

}
package my.bloo.hcwassists;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList patient_id, patient_name, patient_age, patient_dob;

    public CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList patient_name, ArrayList patient_age,
                         ArrayList patient_dob){
        this.activity = activity;
        this.context = context;
        this.patient_id = book_id;
        this.patient_name = patient_name;
        this.patient_age = patient_age;
        this.patient_dob = patient_dob;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.patient_id_txt.setText(String.valueOf(patient_id.get(position)));
        holder.patient_name_txt.setText(String.valueOf(patient_name.get(position)));
        holder.patient_age_txt.setText(String.valueOf(patient_age.get(position)));
        holder.patient_dob_txt.setText(String.valueOf(patient_dob.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                int thisPatientAge = Integer.valueOf(  getAgeInMonths(String.valueOf(patient_dob.get(position))) );
                if(  thisPatientAge >= 0 && thisPatientAge < 12 ) {
                    intent = new Intent(context, Form0_11months.class);
                } else if ( thisPatientAge >= 12 && thisPatientAge < 23  ) {
                    intent = new Intent(context, Form12_23months.class);
                } else if ( thisPatientAge >= 24 && thisPatientAge < 60  ) {
                    intent = new Intent(context, Form24_59months.class);
                } else if ( thisPatientAge >= 60 && thisPatientAge < 144  ) {
                    intent = new Intent(context, Form60_143months.class);
                } else {
                    intent = new Intent(context, Form144above_months.class);
                }


                intent.putExtra("patient_id", String.valueOf(patient_id.get(position)));
                intent.putExtra("patient_name", String.valueOf(patient_name.get(position)));
                intent.putExtra("age", String.valueOf(patient_age.get(position)));
                intent.putExtra("dob", String.valueOf(patient_dob.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return patient_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patient_id_txt, patient_name_txt, patient_age_txt, patient_dob_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_id_txt = itemView.findViewById(R.id.patient_id_txt);
            patient_name_txt = itemView.findViewById(R.id.patient_name_txt);
            patient_age_txt = itemView.findViewById(R.id.patient_age_txt);
            patient_dob_txt = itemView.findViewById(R.id.patient_dob_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

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

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

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList patient_id, patient_name, patient_age, patient_dob;

    public ReadingAdapter(Activity activity, Context context, ArrayList book_id, ArrayList patient_name, ArrayList patient_age,
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

                intent = new Intent(context, PatientReadings.class);

                intent.putExtra("patient_id", String.valueOf(patient_id.get(position)));
                intent.putExtra("name", String.valueOf(patient_name.get(position)));
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

}

package my.bloo.hcwassists;

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

public class SinglePatientAdapter extends RecyclerView.Adapter<SinglePatientAdapter.MyViewHolder> {


    private Context context;
    private Activity activity;
    private ArrayList patient_id, total_score, datetime;

    public SinglePatientAdapter(Activity activity, Context context, ArrayList patient_id,  ArrayList total_score,
                                ArrayList datetime){
        this.activity = activity;
        this.context = context;
        this.patient_id = patient_id;
        this.total_score = total_score;
        this.datetime = datetime;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_patient_reading_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.patient_id_txt.setText(String.valueOf(patient_id.get(position)));
        holder.total_score_txt.setText(String.valueOf(total_score.get(position)));
        holder.datetime_txt.setText(String.valueOf(datetime.get(position)));

    }

    @Override
    public int getItemCount() {
        return patient_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView patient_id_txt, total_score_txt, datetime_txt;
        LinearLayout singlePatientReading;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_id_txt = itemView.findViewById(R.id.patient_id_txt);
            total_score_txt = itemView.findViewById(R.id.patient_score_val);
            datetime_txt = itemView.findViewById(R.id.patient_reading_dttm);
            singlePatientReading = itemView.findViewById(R.id.singlePatientReading);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            singlePatientReading.setAnimation(translate_anim);
        }

    }

}

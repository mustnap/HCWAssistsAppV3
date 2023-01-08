package my.bloo.hcwassists.ui.dashboard;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import my.bloo.hcwassists.CustomAdapter;
import my.bloo.hcwassists.MyDatabaseHelper;
import my.bloo.hcwassists.R;
import my.bloo.hcwassists.ReadingAdapter;
import my.bloo.hcwassists.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> patient_id, patient_name, patient_age, patient_dob;
    ReadingAdapter readingAdapter;

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView2);

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//

        myDB = new MyDatabaseHelper(getContext());
        patient_id = new ArrayList<>();
        patient_name = new ArrayList<>();
        patient_age = new ArrayList<>();
        patient_dob = new ArrayList<>();

        storeDataInArrays();

        readingAdapter = new ReadingAdapter(getActivity(), getContext(), patient_id, patient_name, patient_age,
                patient_dob);
        recyclerView.setAdapter(readingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {

        } else {
            while (cursor.moveToNext()) {
                patient_id.add(cursor.getString(0));
                patient_name.add(cursor.getString(1));
                patient_age.add(cursor.getString(2));
                patient_dob.add(cursor.getString(3));
            }

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
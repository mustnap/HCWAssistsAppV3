package my.bloo.hcwassists.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import my.bloo.hcwassists.AddNewPatient;
import my.bloo.hcwassists.CustomAdapter;
import my.bloo.hcwassists.MyDatabaseHelper;
import my.bloo.hcwassists.R;
import my.bloo.hcwassists.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> patient_id, patient_name, patient_age, patient_dob;
    CustomAdapter customAdapter;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);
        add_button = root.findViewById(R.id.add_button);

       add_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                     Intent intent = new Intent(getActivity(), AddNewPatient.class);
                     startActivity(intent);

             }
         });

        myDB = new MyDatabaseHelper(getContext());
        patient_id = new ArrayList<>();
        patient_name = new ArrayList<>();
        patient_age = new ArrayList<>();
        patient_dob = new ArrayList<>();

        storeDataInArrays();


        customAdapter = new CustomAdapter(getActivity(), getContext(), patient_id, patient_name, patient_age,
                patient_dob);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
//            empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                patient_id.add(cursor.getString(0));
                patient_name.add(cursor.getString(1));
                patient_age.add(cursor.getString(2));
                patient_dob.add(cursor.getString(3));
            }
//            empty_imageview.setVisibility(View.GONE);
//            no_data.setVisibility(View.GONE);
        }
    }
}
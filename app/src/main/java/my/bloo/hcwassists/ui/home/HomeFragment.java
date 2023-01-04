package my.bloo.hcwassists.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import my.bloo.hcwassists.AddActivity;
import my.bloo.hcwassists.AddNewPatient;
import my.bloo.hcwassists.MainActivity;
import my.bloo.hcwassists.R;
import my.bloo.hcwassists.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

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


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
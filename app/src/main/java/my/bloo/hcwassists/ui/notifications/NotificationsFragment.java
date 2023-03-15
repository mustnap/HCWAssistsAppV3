package my.bloo.hcwassists.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import my.bloo.hcwassists.AddNewPatient;
import my.bloo.hcwassists.Confidentiality;
import my.bloo.hcwassists.Creators;
import my.bloo.hcwassists.IntroToPews;
import my.bloo.hcwassists.R;
import my.bloo.hcwassists.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    Button confidentiality_button, creators_button, intro_button;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;

        confidentiality_button = root.findViewById(R.id.conf_agreement_button);
        confidentiality_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Confidentiality.class);
                startActivity(intent);
            }
        });

        creators_button = root.findViewById(R.id.creators_page_button);
        creators_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Creators.class);
                startActivity(intent);
            }
        });

        intro_button = root.findViewById(R.id.intro_button);
        intro_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IntroToPews.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
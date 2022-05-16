package io.github.KasperSJensen.beFreed.ui.Profile.Settings;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.github.KasperSJensen.beFreed.R;


public class settingsFragment extends Fragment {


    public settingsFragment() {
        // Required empty public constructor
    }

    SharedPreferences sharedPreferences = null;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        SwitchCompat themeSwitch = view.findViewById(R.id.themeSwitch);
        Button signOutButton = view.findViewById(R.id.signOutButton);

        if (user != null) {
            signOutButton.setText(R.string.Logout);

        } else {
            signOutButton.setText(R.string.Login);
        }


        signOutButton.setOnClickListener(view1 -> {
            if (user != null) {
                signOutButton.setText(R.string.Logout);
                AuthUI.getInstance().signOut(getApplicationContext()).addOnCompleteListener((Activity) getContext(), task -> reset());

            } else {
                signOutButton.setText(R.string.Login);
                reset();
            }
        });

        themeSwitch.setText("Off");

        sharedPreferences = this.getActivity().getSharedPreferences("night_mode", 0);
        boolean booleanValue = sharedPreferences.getBoolean("night_mode", false);
        if (booleanValue) {
            themeSwitch.setChecked(true);
            themeSwitch.setText("On");
        }

        themeSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                themeSwitch.setChecked(true);
                themeSwitch.setText("On");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode", true);
                editor.apply();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                themeSwitch.setChecked(false);
                themeSwitch.setText("Off");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode", false);
                editor.apply();
            }
            reset();

        });
        return view;
    }

    private void reset() {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }
}
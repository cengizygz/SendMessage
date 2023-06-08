package com.cengizhanyagiz.hammalmobile.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.Utils.ChangeFragment;


public class SettingsFragment extends Fragment {
    View view;
   RelativeLayout bildirimbtn,ArkadaslarimBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_settings, container, false);
        tanimla();
        return view;
    }
    public void tanimla(){
        bildirimbtn=(RelativeLayout) view.findViewById(R.id.bildirimbtn);
        ArkadaslarimBtn=(RelativeLayout) view.findViewById(R.id.ArkadaslarimBtn);

        bildirimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment = new ChangeFragment(getContext());
                changeFragment.change(new NotificationFragment());
            }
        });
        ArkadaslarimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment = new ChangeFragment(getContext());
                changeFragment.change(new ArkadaslarFragment());
            }
        });
    }
}
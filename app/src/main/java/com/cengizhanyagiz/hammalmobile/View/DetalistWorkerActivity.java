package com.cengizhanyagiz.hammalmobile.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.databinding.ActivityDetalistWorkerBinding;

public class DetalistWorkerActivity extends AppCompatActivity {

    private ActivityDetalistWorkerBinding binding;
    TextView detay_isci_nitelik,detay_isci_sirketadi,detay_isci_adres,detay_isci_aciklama,detay_isci_calismasekli,detay_isci_adresdetay;
    ConstraintLayout detay_isci_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalistWorkerBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);


        detay_isci_nitelik = findViewById(R.id.detay_isci_nitelik);
        detay_isci_sirketadi = findViewById(R.id.detay_isci_sirketadi);
        detay_isci_adres= findViewById(R.id.detay_isci_adres);
        detay_isci_aciklama= findViewById(R.id.detay_isci_aciklama);
        detay_isci_calismasekli= findViewById(R.id.detay_isci_calismasekli);
        detay_isci_adresdetay= findViewById(R.id.detay_isci_adresdetay);


        detay_isci_btn= findViewById(R.id.detay_isci_btn);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            detay_isci_nitelik.setText(bundle.getString("nitelik"));
            detay_isci_sirketadi.setText(bundle.getString("sirketAdi"));
            detay_isci_adres.setText(bundle.getString("adres"));
            detay_isci_aciklama.setText(bundle.getString("aciklama"));
            detay_isci_calismasekli.setText(bundle.getString("calismasekli"));
            detay_isci_adresdetay.setText(bundle.getString("adres"));
        }
    }
}
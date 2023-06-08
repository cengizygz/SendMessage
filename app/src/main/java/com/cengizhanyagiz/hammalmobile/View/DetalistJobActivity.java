package com.cengizhanyagiz.hammalmobile.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cengizhanyagiz.hammalmobile.R;
import com.cengizhanyagiz.hammalmobile.databinding.ActivityDetalistJobBinding;
import com.squareup.picasso.Picasso;

public class DetalistJobActivity extends AppCompatActivity {
    private ActivityDetalistJobBinding binding;
    TextView textViewisim,textViewyas,textViewmeslek;
    TextView textViewtanit,textViewdeneyim,textViewiletisim;
    TextView textViewogretim,textViewadres,textViewcalismasaat;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalistJobBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        textViewcalismasaat = findViewById(R.id.textViewzaman);
        textViewogretim = findViewById(R.id.textViewogretim);
        textViewtanit = findViewById(R.id.textViewtanit);
        textViewdeneyim = findViewById(R.id.textViewdeneyim);
        textViewiletisim = findViewById(R.id.textViewiletisim);
        textViewadres = findViewById(R.id.textViewsehir);
        textViewisim = findViewById(R.id.textViewisim);
        textViewyas = findViewById(R.id.textViewyas);
        textViewmeslek= findViewById(R.id.textViewmeslek);
        imageView1 = findViewById(R.id.imageView13);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            textViewisim.setText(bundle.getString("isim"));
            textViewyas.setText(bundle.getString("yas"));
            textViewmeslek.setText(bundle.getString("meslek"));
            textViewadres.setText(bundle.getString("adres"));
            textViewtanit.setText(bundle.getString("tanit"));
            textViewdeneyim.setText(bundle.getString("deneyim"));
            textViewiletisim.setText(bundle.getString("iletisim"));
            textViewogretim.setText(bundle.getString("ogretim"));
            textViewcalismasaat.setText(bundle.getString("çalışma saat"));

            Picasso.get().load(bundle.getString("image")).into(imageView1);
        }



    }


}
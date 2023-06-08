package com.cengizhanyagiz.hammalmobile.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cengizhanyagiz.hammalmobile.Language.Versions;
import com.cengizhanyagiz.hammalmobile.Language.VersionsAdapter;
import com.cengizhanyagiz.hammalmobile.R;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        recyclerView=findViewById(R.id.recyclerView);

        initData();
        setRecylerView();
    }
    private void setRecylerView() {
        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {

        versionsList=new ArrayList<>();
        versionsList.add(new Versions("Hammal Nedir?","Hammal bir iş ve işçi bulma uygulamasıdır"));
        versionsList.add(new Versions("Hammal Ne işe Yarar?","İş veya işçi bulmanıza yarar."));
        versionsList.add(new Versions("Hammal'ı Neden Tercih Etmeliyim?","Çünkü güzel."));
        versionsList.add(new Versions("Hammal'da hangi İşleri Bulabilirim? ","Her Türlüsünü Bulursun."));
        versionsList.add(new Versions("Hammal Güvenilir mi?","Neden olmasın ki."));
    }
    public void back_btn (View view){
        Intent Intent = new Intent(AboutActivity.this, BottomNavigationActivity.class);
        startActivity(Intent);
        finish();
    }
}
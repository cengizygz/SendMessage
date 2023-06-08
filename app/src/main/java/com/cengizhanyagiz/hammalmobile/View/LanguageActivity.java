package com.cengizhanyagiz.hammalmobile.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cengizhanyagiz.hammalmobile.About.Versions;
import com.cengizhanyagiz.hammalmobile.About.VersionsAdapter;
import com.cengizhanyagiz.hammalmobile.R;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {

    ArrayList<Versions> arrayList;
    RecyclerView recyclerView;
    VersionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        recyclerView=findViewById(R.id.recycler_view);
        arrayList=new ArrayList<>();

        arrayList.add(new Versions("Türkçe","Yalnızca Türkçe dil desteğimiz mevcuttur."));
        //   arrayList.add(new Versions("hammal1","naber"));
        //  arrayList.add(new Versions("hammal2","naber"));
        //   arrayList.add(new Versions("hammal3","naber"));
        //  arrayList.add(new Versions("hammal4","naber"));

        adapter=new VersionsAdapter(arrayList, LanguageActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void back_btn (View view){
        Intent Intent = new Intent(LanguageActivity.this, BottomNavigationActivity.class);
        startActivity(Intent);
        finish();
    }
}
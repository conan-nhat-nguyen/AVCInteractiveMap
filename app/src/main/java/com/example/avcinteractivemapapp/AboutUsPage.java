package com.example.avcinteractivemapapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import java.util.ArrayList;

public class AboutUsPage extends AppCompatActivity {
    ArrayList<Question> aboutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_page);

        aboutItems = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(AppCompatResources.getDrawable(AboutUsPage.this, R.drawable.icon_back));

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
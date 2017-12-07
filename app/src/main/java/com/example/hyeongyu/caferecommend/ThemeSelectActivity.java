package com.example.hyeongyu.caferecommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThemeSelectActivity extends AppCompatActivity {
    Button imgBtn01, imgBtn02, imgBtn03, imgBtn04, imgBtn05, imgBtn06, imgBtn07, imgBtn08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);

        Intent  intent = getIntent();
        String location = intent.getStringExtra("location");

        imgBtn01 = (Button) findViewById(R.id.imgBtn01);
        imgBtn02 = (Button) findViewById(R.id.imgBtn02);
        imgBtn03 = (Button) findViewById(R.id.imgBtn03);
        imgBtn04 = (Button) findViewById(R.id.imgBtn04);
        imgBtn05 = (Button) findViewById(R.id.imgBtn05);
        imgBtn06 = (Button) findViewById(R.id.imgBtn06);
        imgBtn07 = (Button) findViewById(R.id.imgBtn07);
        imgBtn08 = (Button) findViewById(R.id.imgBtn08);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", view.getId());
                startActivity(intent);
            }
        };

        imgBtn01.setOnClickListener(clickListener);
        imgBtn02.setOnClickListener(clickListener);
        imgBtn03.setOnClickListener(clickListener);
        imgBtn04.setOnClickListener(clickListener);
        imgBtn05.setOnClickListener(clickListener);
        imgBtn06.setOnClickListener(clickListener);
        imgBtn07.setOnClickListener(clickListener);
        imgBtn08.setOnClickListener(clickListener);

    }
}

package com.example.hyeongyu.caferecommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ThemeSelectActivity extends AppCompatActivity {
    Button imgBtn01, imgBtn02, imgBtn03, imgBtn04, imgBtn05, imgBtn06, imgBtn07, imgBtn08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);

        Intent  intent = getIntent();
        final String location = intent.getStringExtra("location");

        imgBtn01 = (Button) findViewById(R.id.imgBtn1);
        imgBtn02 = (Button) findViewById(R.id.imgBtn2);
        imgBtn03 = (Button) findViewById(R.id.imgBtn3);
        imgBtn04 = (Button) findViewById(R.id.imgBtn4);
        imgBtn05 = (Button) findViewById(R.id.imgBtn5);
        imgBtn06 = (Button) findViewById(R.id.imgBtn6);
        imgBtn07 = (Button) findViewById(R.id.imgBtn7);
        imgBtn08 = (Button) findViewById(R.id.imgBtn8);

//        View.OnClickListener clickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
//                intent.putExtra("theme", view.getId());
//                intent.putExtra("location", location);
//                startActivity(intent);
//            }
//        };

        imgBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "1");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });`

        imgBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "2");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        imgBtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "3");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        imgBtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "4");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
        imgBtn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "5");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        imgBtn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "6");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        imgBtn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "7");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        imgBtn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(ThemeSelectActivity.this, CafeListActivity.class);
                intent.putExtra("theme", "8");
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

    }
}

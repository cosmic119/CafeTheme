package com.example.hyeongyu.caferecommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {
    ImageView youseong, daeduck, junggu, seogu, donggu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        youseong = (ImageView) findViewById(R.id.image1);
        junggu = (ImageView) findViewById(R.id.image3);
        daeduck = (ImageView) findViewById(R.id.image5);
        seogu = (ImageView) findViewById(R.id.image2);
        donggu = (ImageView) findViewById(R.id.image4);


        youseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MapActivity.this, ThemeSelectActivity.class);
                intent.putExtra("location", "1");
                startActivity(intent);
            }
        });

        seogu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MapActivity.this, ThemeSelectActivity.class);
                intent.putExtra("location", "2");
                startActivity(intent);
            }
        });

        junggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MapActivity.this, ThemeSelectActivity.class);
                intent.putExtra("location", "3");
                startActivity(intent);
            }
        });

        donggu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MapActivity.this, ThemeSelectActivity.class);
                intent.putExtra("location", "4");
                startActivity(intent);
            }
        });

        daeduck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MapActivity.this, ThemeSelectActivity.class);
                intent.putExtra("location", "5");
                startActivity(intent);
            }
        });
    }
}

package com.example.hyeongyu.caferecommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


        View.OnClickListener v1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MapActivity.this, ThemeSelectActivity.class);
                intent.putExtra("location", view.getId());
                startActivity(intent);
            }
        };

        youseong.setOnClickListener(v1);
        junggu.setOnClickListener(v1);
        daeduck.setOnClickListener(v1);
        seogu.setOnClickListener(v1);
        donggu.setOnClickListener(v1);

    }
}

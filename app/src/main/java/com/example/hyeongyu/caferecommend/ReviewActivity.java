package com.example.hyeongyu.caferecommend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {

    TextView textView;
    TextView text1, text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent = getIntent();
        String result = intent.getExtras().getString("cafedata");
        String cafe_name = intent.getExtras().getString("title2");
        String cafe_description = intent.getExtras().getString("description2");
        String cafe_link = intent.getExtras().getString("link2");
        String copy = result;
        String name[] = copy.split("<b>");
        String real[] = name[1].split("</b>");

        textView = (TextView) findViewById(R.id.reviewText);
        text1 = (TextView) findViewById(R.id.name2);
        text2 = (TextView) findViewById(R.id.description2);
        text3 = (TextView) findViewById(R.id.link2);


        text1.setText(cafe_name.substring(9));
        text2.setText(cafe_description.substring(15));
        text3.setText(cafe_link.substring(9));

        //textView.setText(result);

    }
}

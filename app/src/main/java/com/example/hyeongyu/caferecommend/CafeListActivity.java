package com.example.hyeongyu.caferecommend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CafeListActivity extends AppCompatActivity {

    final int ITEM_SIZE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Item> items = new ArrayList<>();
        Item[] item = new Item[ITEM_SIZE];
//        item[0] = new Item(R.drawable. , "프롬꼬모");
//        item[1] = new Item(R.drawable.lodeocomic, "로데오코믹스");
//        item[2] = new Item(R.drawable.kairos, "카이로스");
//        item[3] = new Item(R.drawable.krok, "악어식탁");
//        item[4] = new Item(R.drawable.maeul, "마을");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
    }
}

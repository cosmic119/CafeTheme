package com.example.hyeongyu.caferecommend;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CafeListActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    final int ITEM_SIZE = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_list);
        //  intent 가져오기
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        String theme = intent.getStringExtra("theme");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Item> items = new ArrayList<>();
        Item[] item = new Item[ITEM_SIZE];
        int j=0;

        //  디비 사용
        helper = new MySQLiteOpenHelper(getApplicationContext(), "cafeDB2.db", null, 1);

        db = helper.getWritableDatabase();
        helper.onCreate(db);
        Cursor c = db.rawQuery("select * from cafe where Theme ="+theme+" and location = "+location+"", null);
        c.moveToFirst();
        //String x = String.valueOf(c.getString(3));



        String[] imageName = new String[ITEM_SIZE];
        int[] imgID = new int[ITEM_SIZE];
        for(int k=0 ; k<5; k++){
            imageName[k] = "img"+location+"_"+theme+"_"+(k+1);
            imgID[k] = getResources().getIdentifier(imageName[k] , "drawable", getPackageName());
        }
        while(!c.isLast()){
            String str = String.valueOf(c.getString(3));
            item[j] = new Item(imgID[j],str);
            j++;
            c.moveToNext();
        }



        //Item[] item = new Item[ITEM_SIZE];
//        item[0] = new Item(R.drawable. , "프롬꼬모");
//        item[1] = new Item(R.drawable.lodeocomic, "로데오코믹스");
//        item[2] = new Item(R.drawable.kairos, "카이로스");
//        item[3] = new Item(R.drawable.krok, "악어식탁");
//        item[4] = new Item(R.drawable.maeul, "마을");

        //items.add(item[0]);
        for (int i = 0; i < j; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_cafe_list));
    }
}
package com.example.hyeongyu.caferecommend;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eunjeong on 2017-12-07.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    static String NAME = "cafe2.sqlite";
    static SQLiteDatabase.CursorFactory FACTORY = null;
    static String PACKEGE = "com.example.hyeongyu.caferecommend";
    static String DB = "cafeDB2.db";
    static int VERSION  = 1;

    // ?덈뱶濡쒖씠?쒖뿉??SQLite ?곗씠??踰좎씠?ㅻ? ?쎄쾶 ?ъ슜?????덈룄濡??꾩?二쇰뒗 ?대옒??
    public MySQLiteOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
        copyDB(context);
    }
    public MySQLiteOpenHelper(Context context) {
        super(context, NAME, FACTORY, VERSION);
// TODO Auto-generated constructor stub
        try {
            boolean bResult = isCheckDB(context);  // DB媛 ?덈뒗吏?
            Log.i("MiniApp", "DB Check="+bResult);
            if(!bResult){   // DB媛 ?놁쑝硫?蹂듭궗
                copyDB(context);
            }else{

            }
        } catch (Exception e) {

        }
    }
    // DB媛 ?덈굹 泥댄겕?섍린
    public boolean isCheckDB(Context mContext){
        String filePath = "/data/data/" + PACKEGE + "/databases/" + DB;
        File file = new File(filePath);

        if (file.exists()) {
            Log.d("MiniApp", "DB gg");
            return true;
        }

        return false;

    }
    // DB瑜?蹂듭궗?섍린
    // assets??/db/xxxx.db ?뚯씪???ㅼ튂???꾨줈洹몃옩???대? DB怨듦컙?쇰줈 蹂듭궗?섍린
    public void copyDB(Context mContext){
        Log.d("MiniApp", "DB gggg");
        AssetManager manager = mContext.getAssets();
        String folderPath = "/data/data/" + PACKEGE + "/databases";
        String filePath = "/data/data/" + PACKEGE + "/databases/" +DB;
        File folder = new File(folderPath);
        File file = new File(filePath);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            InputStream is = manager.open("db/"+DB);
            BufferedInputStream bis;
            bis = new BufferedInputStream(is);

            if (folder.exists()) {
                Log.e("hello", "dbdb");
            }else{
                folder.mkdirs();
            }


            //if (file.exists()) {
            //Log.e("hello", "dbdb");
            //file.delete();
            file.createNewFile();
            //}

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }

            bos.flush();

            bos.close();
            fos.close();
            bis.close();
            is.close();

        } catch (IOException e) {
            Log.e("ErrorMessage : ", e.getMessage());
        }
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(SQLiteDatabase db) {
//	String QUERY = "CREATE TABLE word (_id INTEGER PRIMARY KEY autoincrement, word_e TEXT , word_k TEXT)";
//	db.execSQL(QUERY);
//        Log.e("ehsk", "eee");
//
//        String QUERY1 = "INSERT INTO word (word_e, word_k ) VALUES(apple , ?ш낵)";
//        db.execSQL(QUERY1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        String QUERY = "DROP TABLE IF EXISTS word";
        db.execSQL(QUERY);
        onCreate(db);


    }
}

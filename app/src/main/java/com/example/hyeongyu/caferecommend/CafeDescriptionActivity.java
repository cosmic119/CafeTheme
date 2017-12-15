package com.example.hyeongyu.caferecommend;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class CafeDescriptionActivity extends NMapActivity {
    private static final String CLIENT_ID = "ljtmKl_aKyVyH_R3q7j4";
    private final String  TAG = "CafeDescriptionActivity";
    private LinearLayout mapLayout;

    private NMapController mMapController;
    private NMapView mMapView;

    private NMapResourceProvider nMapResourceProvider;
    private NMapOverlayManager mapOverlayManager;

    double x,y;


    TextView cafeAddr, cafeTitle, cafeTel;
    TextView textView;
    Button button2;
    String url;
    Intent intent;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_description);



        String text="";
        /*은정*/
        Intent intent = getIntent();
        name = intent.getStringExtra("cafeinfo");    //  카페 이름 받아오기
        try {
            text = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url = "https://openapi.naver.com/v1/search/local?query="+text+"&display=1";

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();

        init();

        nMapResourceProvider = new NMapViewerResourceProvider(this);
        mapOverlayManager = new NMapOverlayManager(this, mMapView, nMapResourceProvider);

        /*  버튼에 후기 url 넣어서 넘기기 */
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AsyncTask를 통해 HttpURLConnection 수행.
                //NetworkTask networkTask = new NetworkTask(url, null);
                //networkTask.execute();
                Intent newIntent = new Intent(CafeDescriptionActivity.this, ReviewActivity.class);
                newIntent.putExtra("keyword", name);
                startActivity(newIntent);
            }
        });
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                //JSONParser jsonParser = new JSONParser();
                JSONObject obj = new JSONObject(s);
                JSONArray arr = obj.getJSONArray("items");
                //String str = obj.getString("items");

                String addr = arr.getJSONObject(0).getString("address");
                String title = arr.getJSONObject(0).getString("title");
                String tel = arr.getJSONObject(0).getString("telephone");

                title = title.substring(3, title.length() - 4);
                //JSONArray itemArray = new JSONArray(str);
                cafeAddr = (TextView) findViewById(R.id.addressText);
                cafeTitle = (TextView) findViewById(R.id.nameText);
                cafeTel= (TextView) findViewById(R.id.telephoneText);
                cafeAddr.setText(addr);
                cafeTitle.setText(title);
                cafeTel.setText(tel);



            } catch (JSONException e) {
                e.printStackTrace();
            }


            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
//            String splitArray[] = s.split("\\{");
//            int x = splitArray.length;
//            String[][] dataArray;
//            String app="";
//            String temp[];
//            dataArray = new String[x][];
//            for(int i=0; i<splitArray.length; i++){
//                //temp = splitArray[i].split(",");
//                dataArray[i] = splitArray[i].split(",");
//                if(i>=2){
//                    intent.putExtra("title"+i, dataArray[i][0]);
//                    intent.putExtra("link"+i, dataArray[i][1]);
//                    intent.putExtra("description"+i, dataArray[i][2]);
//
//                }
//            }
//
//
//            for(int i=0; i<5; i++){
//                app += dataArray[2][i]+"\n";
//            }

//            for(int i=0; i<splitArray.length; i++){
//                app += splitArray[i]+"\n";
//                //textView.setText(s);
//
//            }

//            intent.putExtra("cafedata", app);
//            startActivity(intent);

            //textView.setText(app);
        }
    }


    private void init(){


        mapLayout = findViewById(R.id.fragmentHere);

        mMapView = new NMapView(this);
        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.setScalingFactor(1.7f);        //이것을 안해주면 줌시켜서 축소 시켜도 작게보
        mMapView.requestFocus();

        mMapView.setOnMapStateChangeListener(changeListener);
        mMapView.setOnMapViewTouchEventListener(mapListener);
        mapLayout.addView(mMapView);

        mMapController = mMapView.getMapController();
        mMapController.setMapCenter(new NGeoPoint(127.3495018, 36.3627695), 11);     //Default Data

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setMarker();
            }
        }, 5000);
    }

    private void setMarker(){

        int markerId = NMapPOIflagType.PIN;

        // set POI data
        NMapPOIdata poiData = new NMapPOIdata(1, nMapResourceProvider);
        poiData.beginPOIdata(1);
        poiData.addPOIitem(127.3498739, 36.3628764, "말풍선 클릭시 뿅", markerId, 0);
//        poiData.addPOIitem(133, 27, "네이버맵 입니다", markerId, 0);
        poiData.endPOIdata();

        // create POI data overlay
        NMapPOIdataOverlay poiDataOverlay = mapOverlayManager.createPOIdataOverlay(poiData, null);
        poiDataOverlay.showAllPOIdata(0);
        poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);  //좌표 클릭시 말풍선 리스
    }

    private NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {
        @Override
        public void onFocusChanged(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {

        }

        @Override
        public void onCalloutClick(NMapPOIdataOverlay nMapPOIdataOverlay, NMapPOIitem nMapPOIitem) {
            if (nMapPOIitem != null) {
                Log.e(TAG, "onFocusChanged: " + nMapPOIitem.toString());
            } else {
                Log.e(TAG, "onFocusChanged: ");
            }
        }
    };



    private NMapView.OnMapStateChangeListener changeListener = new NMapView.OnMapStateChangeListener() {
        @Override
        public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
            Log.e(TAG, "OnMapStateChangeListener onMapInitHandler : ");
        }

        @Override
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {
            Log.e(TAG, "OnMapStateChangeListener onMapCenterChange : " + nGeoPoint.getLatitude() + " ㅡ  " + nGeoPoint.getLongitude());
        }

        @Override
        public void onMapCenterChangeFine(NMapView nMapView) {
            Log.e(TAG, "OnMapStateChangeListener onMapCenterChangeFine : ");
        }

        @Override
        public void onZoomLevelChange(NMapView nMapView, int i) {
            Log.e(TAG, "OnMapStateChangeListener onZoomLevelChange : " + i);
        }

        @Override
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {
            Log.e(TAG, "OnMapStateChangeListener onAnimationStateChange : ");
        }
    };

    private NMapView.OnMapViewTouchEventListener mapListener = new NMapView.OnMapViewTouchEventListener() {
        @Override
        public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onLongPress : ");
        }

        @Override
        public void onLongPressCanceled(NMapView nMapView) {
            Log.e(TAG, "OnMapViewTouchEventListener onLongPressCanceled : ");
        }

        @Override
        public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onTouchDown : ");
        }

        @Override
        public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onTouchUp : ");
        }

        @Override
        public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {
            Log.e(TAG, "OnMapViewTouchEventListener onScroll : ");
        }

        @Override
        public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {
            Log.e(TAG, "OnMapViewTouchEventListener onSingleTapUp : ");
        }
    };
}
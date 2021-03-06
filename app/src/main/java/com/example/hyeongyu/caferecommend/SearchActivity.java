package com.example.hyeongyu.caferecommend;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
// 네이버 검색 API 예제 - blog 검색


public class SearchActivity extends AppCompatActivity {
    Button button;
    String url;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        button = (Button) findViewById(R.id.reviewButton);

        url = "https://openapi.naver.com/v1/search/blog?query=";
        intent = new Intent(SearchActivity.this, ReviewActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AsyncTask를 통해 HttpURLConnection 수행.
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();

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

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            String splitArray[] = s.split("\\{");
            int x = splitArray.length;
            String[][] dataArray;
            String app="";
            String temp[];
            dataArray = new String[x][];
            for(int i=0; i<splitArray.length; i++){
                //temp = splitArray[i].split(",");
                dataArray[i] = splitArray[i].split(",");
                if(i>=2){
                    intent.putExtra("title"+i, dataArray[i][0]);
                    intent.putExtra("link"+i, dataArray[i][1]);
                    intent.putExtra("description"+i, dataArray[i][2]);

                }
            }


            for(int i=0; i<5; i++){
                app += dataArray[2][i]+"\n";
            }

//            for(int i=0; i<splitArray.length; i++){
//                app += splitArray[i]+"\n";
//                //textView.setText(s);
//
//            }

            intent.putExtra("cafedata", app);
            startActivity(intent);

            //textView.setText(app);
        }
    }
}

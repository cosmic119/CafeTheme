package com.example.hyeongyu.caferecommend;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ReviewActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    String text;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        Intent intent = getIntent();
        String name = intent.getStringExtra("keyword") + " 카페";

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        try {
            text = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query=" + text;
        webView.loadUrl(url);





    }
}
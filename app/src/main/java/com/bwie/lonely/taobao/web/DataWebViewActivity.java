package com.bwie.lonely.taobao.web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwie.lonely.taobao.R;

public class DataWebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_web_view);

        webView = (WebView) findViewById(R.id.MainwebView);

        // 接收传来的url
        final String url1 = getIntent().getStringExtra("url");
        webView.loadUrl(url1);

        /*设置webview在本布局加载*/
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}

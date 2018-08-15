package com.example.autocount.vehicleparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoToWebpage extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_webpage);

        Bundle extras = getIntent().getExtras();
        String web_address;
        if(extras != null) {
            web_address = extras.getString("Web Address");
            this.webView = (WebView) findViewById(R.id.webview);
            webView.loadUrl(web_address);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
        }

    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        }
        else
            finish();
    }
}

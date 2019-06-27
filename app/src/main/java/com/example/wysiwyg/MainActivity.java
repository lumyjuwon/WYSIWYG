package com.example.wysiwyg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WysiwygEditor editor;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/editor.html");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WysiwygWebView());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);


    }

}

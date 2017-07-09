package com.sslyxhz.androidserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String ROOT_DOCUMENT_PATH = "/html5up-dimension";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview_container);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        startHttpServer();

        final String url = "http://localhost:8080/";
        mWebView.loadUrl(url);
    }

    private void startHttpServer() {
        SimpleHttpServer server = new SimpleHttpServer(8080, ROOT_DOCUMENT_PATH, this);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

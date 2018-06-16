package com.sellustrate.sellustrate;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class EbayListingActivity extends AppCompatActivity{

    String URL = "file:///android_asset/listing.html";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        WebView webview = new WebView(this);

        super.onCreate(savedInstanceState);

        setContentView(webview);

        webview.loadUrl(URL);

    }
}

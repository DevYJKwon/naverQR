package com.devYJ.naverqr;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private ImageButton refreshButton;
    WebView broswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        refreshButton = (ImageButton) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broswer.reload();
            }
        });

        broswer = (WebView)findViewById(R.id.webView);
        broswer.setWebViewClient(new myWebViewClient());
        WebSettings websettings = broswer.getSettings();
        websettings.setLoadsImagesAutomatically(true);
        websettings.setJavaScriptEnabled(true);
        websettings.setDomStorageEnabled(true);
        websettings.setUseWideViewPort(true);
        websettings.setLoadWithOverviewMode(true);
        websettings.setBuiltInZoomControls(true);   // 줌 아이콘 사용
        websettings.setSupportZoom(true);
        broswer.loadUrl("https://nid.naver.com/login/privacyQR");
    }

    private class myWebViewClient extends WebViewClient {
       public boolean shouldOverride(WebView view){
            return true;
        }
    }

}
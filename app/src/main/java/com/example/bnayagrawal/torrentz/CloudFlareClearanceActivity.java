package com.example.bnayagrawal.torrentz;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.bnayagrawal.torrentz.net.Url;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CloudFlareClearanceActivity extends AppCompatActivity{

    @BindView(R.id.web_view_cloud_flare_clearance)
    WebView wbCloudFlareClearance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_flare_clearance);
        ButterKnife.bind(this);

        wbCloudFlareClearance.getSettings().setJavaScriptEnabled(true);
        wbCloudFlareClearance.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                waitForClearance();
                try {Thread.sleep(5000);}catch (InterruptedException e) {e.printStackTrace();}

            }
        });
        wbCloudFlareClearance.loadUrl(Url.BASE_URL);
    }

    private void waitForClearance() {
        //Toast.makeText(CloudFlareClearanceActivity.this,"Waiting for cloud flare clearance!\nPlease wait 5 seconds",Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {Thread.sleep(5000);}catch (InterruptedException e) {e.printStackTrace();}
                finish();
            }
        }).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(CloudFlareClearanceActivity.this,"Clearance cookie retrieved :)",Toast.LENGTH_LONG).show();
    }
}

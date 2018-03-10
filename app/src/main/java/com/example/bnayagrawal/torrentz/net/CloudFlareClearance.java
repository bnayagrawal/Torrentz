package com.example.bnayagrawal.torrentz.net;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.DialogFragment;
import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by bnayagrawal on 10/3/18.
 */

public final class CloudFlareClearance {
    public interface CloudFlareClearanceListener {
        void onClearanceCookiesRetrieved();
        void onClearanceCookiesRetrieveError();
    }

    public static String getCookies() {
        CookieManager manager = android.webkit.CookieManager.getInstance();
        return manager.getCookie(Url.BASE_URL);
    }

    public static void fetchClearanceCookies(DialogFragment fragment) {
        final CloudFlareClearanceListener listener;
        try {
            listener = (CloudFlareClearanceListener) fragment;
        } catch (ClassCastException cce) {
            throw new ClassCastException(fragment.toString()
                    + " must implement CloudFlareClearanceListener");
        }

        WebView webView = new WebView(fragment.getActivity());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //TODO: This code runs on UI thread, Fix is required.
                try {Thread.sleep(10000);}catch (InterruptedException e) {e.printStackTrace();}
                listener.onClearanceCookiesRetrieved();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                listener.onClearanceCookiesRetrieveError();
            }

        });
        webView.loadUrl(Url.BASE_URL);
    }
}

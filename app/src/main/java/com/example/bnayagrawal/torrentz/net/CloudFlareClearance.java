package com.example.bnayagrawal.torrentz.net;

import java.net.CookieHandler;

import android.app.DialogFragment;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
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

    private static final String COOKIE_KEY_CFDUID = "__cfduid";
    private static final String COOKIE_KEY_CF_CLEARANCE = "cf_clearance";

    public static boolean isCookieAvailable() {
        CookieManager manager = android.webkit.CookieManager.getInstance();
        String cookies = manager.getCookie(Url.BASE_URL);
        String[] cookiesArray = (cookies != null ) ? cookies.split(";") : null;
        if(null != cookiesArray) {
            for(String cookie: cookiesArray) {

            }
        } else {
            return false;
        }
        return false;
    }

    public static boolean isCookieExpired() {
        return false;
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
                try {Thread.sleep(5000);}catch (InterruptedException e) {e.printStackTrace();}
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

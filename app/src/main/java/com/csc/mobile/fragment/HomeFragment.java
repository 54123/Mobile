package com.csc.mobile.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.csc.mobile.R;
import com.csc.mobile.base.BaseFragment;
import com.csc.mobile.base.Constants;
import com.csc.mobile.entity.BasicDateEntity;
import com.csc.mobile.entity.MessageEntity;

import java.util.List;

/**
 * Created by 随风 on 2018/1/31.
 */

public class HomeFragment  extends BaseFragment{

    private View mView;
    private WebView mWebView;
    private String resultInfo;
    private String ACTION_IP;
    public String type;
    private BasicDateEntity basicDateEntity = BasicDateEntity.getSingle();
    private List<MessageEntity> messagelist;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,container,false);
        mWebView = (WebView) mView.findViewById(R.id.home_webView);
        ACTION_IP = getActivity().getSharedPreferences("IP", getActivity().MODE_PRIVATE).getString("ipaddress", "");
        type = Constants.targetUrl;
        initView();
        return mView;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
       // String flag = basicDateEntity.getConfigureEntity().getApkCache();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.requestFocus();
        mWebView.getSettings().setSupportZoom(true);
        mWebView.addJavascriptInterface(new JsInterface(), "mtNative");

        String ftp = (String) type.subSequence(0, 3);
        String http = (String) type.subSequence(0, 4);
        String url;
        if (ftp.equals("ftp") || http.equals("http")) {
            url = type;
        } else {
            url = ACTION_IP + type;
        }
        CookieSyncManager.createInstance(getActivity());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url, ticket);
        CookieSyncManager.getInstance().sync();
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String[] urls = url.split("h5");
                if(urls.length==2){
                    url = "file:///android_asset/h5"+urls[1];
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mWebView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
            }
        });

        mWebView.setOnKeyListener(new View.OnKeyListener()
        {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == 0)
                {
                    if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
                    {
                        if (mWebView.canGoBack())
                        {
                            mWebView.goBack();
                        }
                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public boolean onBackPressed() {
        return false;
    }
}

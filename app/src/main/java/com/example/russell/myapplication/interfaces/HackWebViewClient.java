package com.example.russell.myapplication.interfaces;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by andrewbriare on 5/13/17.
 */

public class HackWebViewClient extends WebViewClient {

    private final String TAG = "HackWebViewClient";

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.i(TAG, "onPageStarted: " + url);
    }

    @Override
    public void onPageFinished(final WebView view, String url) {
        super.onPageFinished(view, url);

        Log.i(TAG, "onPageFinished: " + view.getUrl());

        if (url.contains("www.calottery.com/sign-in")) {
            handleLogIn(view);
        } else if (url.contains("www.calottery.com/my-account")) {
            handleRedirectToEntryPage(view);
        } else if (url.contains("www.calottery.com/play/second-chance/scratchers-second-chance")) {
            handleCodeEntryPage(view);
        }
    }

    private void handleLogIn(WebView view) {
        StringBuilder sbEmail = new StringBuilder();
        sbEmail.append("document.getElementById('objBody_content_0_leftcolumn_0_txtEmail').value = '");
//        sbEmail.append(email);
        sbEmail.append("';");

        StringBuilder sbPassword = new StringBuilder();
        sbPassword.append("document.getElementById('objBody_content_0_leftcolumn_0_txtPassword').value = '");
//        sbPassword.append(password);
        sbPassword.append("';");

        String loginCommand = "__doPostBack('objBody$content_0$leftcolumn_0$lbSubmit','');";

        view.evaluateJavascript(sbEmail.toString(), null);
        view.evaluateJavascript(sbPassword.toString(), null);
        view.evaluateJavascript(loginCommand, null);
    }

    private void handleRedirectToEntryPage(WebView view) {
        String navigateToCodeEntry = "__doPostBack('objBody$content_0$leftcolumn_0$lbSubmit','');";
        view.loadUrl(navigateToCodeEntry);
    }

    private void handleCodeEntryPage(WebView view) {
        StringBuilder sbCodeSubmission = new StringBuilder();
        sbCodeSubmission.append("https://www.calottery.com/SecondChanceSubmit.ashx?entrycode=");
//        sbCodeSubmission.append(longCode);
        sbCodeSubmission.append("&ticketid=");
//        sbCodeSubmission.append(shortCode);

        view.loadUrl(sbCodeSubmission.toString(), null);
    }
}


package com.beanie.samples.jquery.client;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class SampleChromeClient extends WebChromeClient
{
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result)
    {
        url = "Sample App Alert";
        return super.onJsAlert(view, url, message, result);
    }

}

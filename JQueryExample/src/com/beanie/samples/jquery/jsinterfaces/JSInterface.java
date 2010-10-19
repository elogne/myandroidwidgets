
package com.beanie.samples.jquery.jsinterfaces;

import com.beanie.samples.jquery.OnExitAppListener;

import android.webkit.WebView;

public class JSInterface
{
    private WebView webView;
    private OnExitAppListener listener;

    public JSInterface(WebView webView)
    {
        this.webView = webView;
    }

    // This method is called from Javascript
    /*
    function nativeToggle(){
        // jsinterface is the object of our JSInterface class
        // nativeToggle() method in turn calls the jsToggle method through the webView
        window.jsinterface.nativeToggle();
    }
    */
    public void nativeToggle()
    {
        webView.loadUrl("javascript:jsToggle()");
    }
    
    public void exitApp(){
        listener.onAppExit();
    }
    
    // Listener used to propagate the JS event to the activity
    public void setOnExitAppListener(OnExitAppListener listener){
        this.listener = listener;
    }
}

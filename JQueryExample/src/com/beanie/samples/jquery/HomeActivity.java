package com.beanie.samples.jquery;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.beanie.samples.jquery.client.SampleChromeClient;
import com.beanie.samples.jquery.jsinterfaces.JSInterface;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initializeUIElements();
    }
    
    private void initializeUIElements(){
        WebView webView = (WebView)findViewById(R.id.webView);
        
        // Load the HTML file from the assets directory
        webView.loadUrl("file:///android_asset/sample_page.html");
        
        // Enable Javascript on the webView. By default, it's disabled
        webView.getSettings().setJavaScriptEnabled(true);
        
        // Set the Web Chrome client for stuff like Alerts
        webView.setWebChromeClient(new SampleChromeClient());
        
        // Set the JSInterface that is a connector between your javascrip and Java class
        JSInterface jsInterface = new JSInterface(webView);
        jsInterface.setOnExitAppListener(new OnExitAppListener()
        {
            @Override
            public void onAppExit()
            {
                Toast.makeText(getBaseContext(), "Exiting", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        
        webView.addJavascriptInterface(jsInterface, "jsinterface");
    }
}
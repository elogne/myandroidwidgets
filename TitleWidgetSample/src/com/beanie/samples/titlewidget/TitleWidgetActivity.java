package com.beanie.samples.titlewidget;

import android.app.Activity;
import android.os.Bundle;

public class TitleWidgetActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Initialize title Widget
        TitleWidget titleWidget = (TitleWidget)findViewById(R.id.titleWidget);
        
        // Call the methods to change the underlying widgets
        titleWidget.setTitle("My Custom Title");
        
        titleWidget.setIcon(R.drawable.icon);
        
        titleWidget.showProgressBar();
        
        titleWidget.hideProgressBar();
    }
}
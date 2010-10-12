package com.beanie.samples.titlewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TitleWidget extends LinearLayout
{

    private ImageView image;
    private TextView title;
    private ProgressBar pBar;
    public TitleWidget(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public TitleWidget(Context context)
    {
        super(context);
        init();
    }
    
    // Init method of the TitleWidget which initializes all the widgets
    // after loading the layout file
    private void init(){
        // Inflate the title_widget.xml 
        View view = inflate(getContext(), R.layout.title_widget, null);
        
        image = (ImageView)view.findViewById(R.id.titleIcon);
        
        title = (TextView)view.findViewById(R.id.titleText);
        
        pBar = (ProgressBar)view.findViewById(R.id.titleProgress);
        
        // Add the view to the LinearLayout since TitleWidget extends LinearLayout
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        addView(view, params);
        
    }
    
    public void setTitle(String text){
       title.setText(text);
    }
    
    public void setIcon(int resId){
        image.setImageResource(resId);
    }
    
    public void showProgressBar(){
        pBar.setVisibility(View.VISIBLE);
    }
    
    public void hideProgressBar(){
        pBar.setVisibility(View.INVISIBLE);
    }
}

package com.beanie.sample.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class MyView extends LinearLayout {

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context) {
        super(context);
        init();
    }
    
    private void init(){
        View view = inflate(getContext(), R.layout.box, null);
        addView(view);
    }

}

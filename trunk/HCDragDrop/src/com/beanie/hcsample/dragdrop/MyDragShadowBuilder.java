
package com.beanie.hcsample.dragdrop;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.DragShadowBuilder;

/**
 * This is used to create the floating view and optionally you could add a
 * shadow to the floating view
 * 
 * @author bibek
 */
public class MyDragShadowBuilder extends DragShadowBuilder {
    private Drawable mShadow;

    public MyDragShadowBuilder(View v) {
        super(v);
        mShadow = v.getResources().getDrawable(R.drawable.btn_default_pressed);
        mShadow.setCallback(v);
        mShadow.setBounds(0, 0, v.getWidth(), v.getHeight());
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
        super.onDrawShadow(canvas);
        mShadow.draw(canvas);
        getView().draw(canvas);
    }
}

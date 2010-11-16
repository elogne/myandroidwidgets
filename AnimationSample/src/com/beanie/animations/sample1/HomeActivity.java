
package com.beanie.animations.sample1;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity {
    private ImageView imageView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        imageView = (ImageView) findViewById(R.id.ImageButton01);
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AnimationDrawable animator = (AnimationDrawable) imageView.getBackground();
                imageView.setImageDrawable(null);
                animator.start();
            }
        });

    }
    
    // Call this method to stop the animation
    public void stopAnimation(){
        AnimationDrawable animator = (AnimationDrawable) imageView.getBackground();
        animator.stop();
        imageView.setImageResource(R.drawable.icon);
    }
}


package com.beanie.examples.animation.FlipAnimator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FlipAnimatorExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button buttonAnimate = (Button) findViewById(R.id.buttonAnimate);

        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        final ImageView imageViewFlip = (ImageView) findViewById(R.id.imageView2);

        final ImageView imageViewOriginal = (ImageView) findViewById(R.id.imageView1);

        buttonAnimate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                FlipAnimator animator = new FlipAnimator(imageViewOriginal, imageViewFlip,
                        imageViewFlip.getWidth() / 2, imageViewFlip.getHeight() / 2);
                if (imageViewOriginal.getVisibility() == View.GONE) {
                    animator.reverse();
                }
                layout.startAnimation(animator);
            }
        });
    }
}

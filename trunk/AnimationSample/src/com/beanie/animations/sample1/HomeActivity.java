
package com.beanie.animations.sample1;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ImageView btn = (ImageView) findViewById(R.id.ImageButton01);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AnimationDrawable animator = (AnimationDrawable) btn.getBackground();
                btn.setImageDrawable(null);
                animator.start();
            }
        });

    }
}

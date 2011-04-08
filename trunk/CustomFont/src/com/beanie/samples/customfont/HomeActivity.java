
package com.beanie.samples.customfont;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView bauhs = null;

        setFont(bauhs, "fonts/handsean.ttf", R.id.textView);
    }

    void setFont(TextView name, String path, int res) {
        name = (TextView) findViewById(res);
        Typeface font = Typeface.createFromAsset(getAssets(), path);

        name.setTypeface(font);
    }
}

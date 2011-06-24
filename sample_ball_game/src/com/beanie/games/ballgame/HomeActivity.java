
package com.beanie.games.ballgame;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {

    private GameView gameView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gameView = (GameView) findViewById(R.id.gameView);
        gameView.initializeBallPosition();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.stopThread();
    }
}

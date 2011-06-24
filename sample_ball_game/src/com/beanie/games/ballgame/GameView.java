
package com.beanie.games.ballgame;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class GameView extends SurfaceView implements OnTouchListener {

    private Ball ball;

    private int screenHeight;

    private int screenWidth;

    private float initialBallXPosition;

    private float initialBallYPosition;

    private float ballHeight;

    private float ballWidht;

    private CalculatorThread thread;

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeTouchListener();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeTouchListener();
    }

    public GameView(Context context) {
        super(context);
        initializeTouchListener();
    }

    private void initializeTouchListener() {
        setOnTouchListener(this);
    }

    public void initializeBallPosition() {
        setWillNotDraw(false);

        if (!isInEditMode()) {
            Display display = ((WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            screenHeight = display.getHeight();
            screenWidth = display.getWidth();
        }

        // Initialize the Ball and it's initial position
        ball = new Ball();
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContext().getAssets().open("ball_sample.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ballHeight = bitmap.getHeight();
        
        ballWidht = bitmap.getWidth();

        ball.setBitmap(bitmap);
        initialBallXPosition = (screenWidth - bitmap.getWidth()) / 2;

        initialBallYPosition = screenHeight - 2 * bitmap.getHeight();

        ball.setxPosition(initialBallXPosition);
        ball.setyPosition(initialBallYPosition);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ball.draw(canvas);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchedXPostion = event.getX();
            float touchedYPositon = event.getY();

            thread = new CalculatorThread(initialBallXPosition, initialBallYPosition,
                    touchedXPostion, touchedYPositon, handler);

            thread.setScreenDimensions(screenHeight, screenWidth);
            thread.setBallDimensions(ballWidht, ballHeight);
            thread.start();
        }
        return false;
    }

    private void updateBallPostion(float x, float y) {
        if (ball != null) {
            ball.setxPosition(x);
            ball.setyPosition(y);
            invalidate();
        }
    }

    public void stopThread() {
        thread.setRunning(false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            float x = msg.getData().getFloat("x");
            float y = msg.getData().getFloat("y");
            updateBallPostion(x, y);
        }
    };
}

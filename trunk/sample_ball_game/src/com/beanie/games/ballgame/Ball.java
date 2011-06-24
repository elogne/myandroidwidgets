
package com.beanie.games.ballgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private float xPosition;

    private float yPosition;

    private Bitmap ballBitmap;

    private Paint mPaint;

    public Ball() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public void setBitmap(Bitmap ballBitmap) {
        this.ballBitmap = ballBitmap;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(ballBitmap, xPosition, yPosition, mPaint);
    }
}


package com.beanie.games.ballgame;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CalculatorThread extends Thread {
    private float INCREMENT = 0f;

    private float slope;

    private float constant;

    private Handler handler;

    private float initialBallXPosition;

    private float initialBallYPosition;

    private int screenHeight;

    private int screenWidth;

    private float ballWidth;

    private float ballHeight;

    private boolean isRunning = true;

    public CalculatorThread(float intialXPosition, float intialYPosition, float xPosition,
            float yPosition, Handler handler) {
        this.initialBallXPosition = intialXPosition;
        this.initialBallYPosition = intialYPosition;
        calculateSlopeAndIntercept(xPosition, yPosition);
        this.handler = handler;
    }

    public void setInitialBallPosition(float xPosition, float yPosition) {
        this.initialBallXPosition = xPosition;
        this.initialBallYPosition = yPosition;
    }

    private void calculateSlopeAndIntercept(float xPosition, float yPosition) {
        slope = (yPosition - initialBallYPosition) / (xPosition - initialBallXPosition);
        constant = slope * initialBallXPosition - initialBallYPosition;
    }

    public void setScreenDimensions(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void setBallDimensions(float x, float y) {
        this.ballWidth = x;
        this.ballHeight = y;
    }

    @Override
    public void run() {
        
        // Heart of the problem
        Log.i("Thread", "Slope: " + slope);
        while (isRunning) {
            setupIncrement();
            if (initialBallYPosition > -100) {
                if (initialBallXPosition <= 0) {
                    // Left bounce
                    INCREMENT = -INCREMENT;
                    slope = -slope;
                    System.out.println("Left");
                } else if (initialBallXPosition >= screenWidth - ballWidth) {
                    // Right bounce
                    INCREMENT = -INCREMENT;
                    slope = -slope;
                    constant = slope * initialBallXPosition - initialBallYPosition;
                    System.out.println("Right");
                }

                initialBallXPosition = initialBallXPosition + INCREMENT;
                initialBallYPosition = calculateNextY(initialBallXPosition);

                System.out.println(initialBallXPosition + " : " + initialBallYPosition);

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putFloat("x", initialBallXPosition);
                bundle.putFloat("y", initialBallYPosition);
                message.setData(bundle);

                handler.sendMessage(message);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                isRunning = false;
            }
        }
    }

    private void setupIncrement() {
        if (slope > 0) {
            INCREMENT = -2.0f;
        } else {
            INCREMENT = 2.0f;
        }
    }

    private float calculateNextY(float xPosition) {
        return slope * xPosition - constant;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}

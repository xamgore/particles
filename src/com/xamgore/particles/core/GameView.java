package com.xamgore.particles.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author Goga
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public GameView(Context context, GameScreen state) {
        super(context);
        this.gameState = state;

        setFocusableInTouchMode(true);
        getHolder().addCallback(this);
    }

    /* Class implementation */
    private GameScreen gameState;
    private DrawingThread thread;

    private class DrawingThread extends Thread {
        public boolean keepRunning = true;
        private Canvas canvas;

        @Override
        public void run() {
            SurfaceHolder mSurfaceHolder = getHolder();

            while (keepRunning) {
                Fps.startMeasuringDelay();

                /* Game mechanics */
                gameState.update();

                /* Drawings */
                canvas = null;
                try {
                    canvas = mSurfaceHolder.lockCanvas();
                    if (canvas != null) {
                        // Send request to Game class
                        gameState.draw(canvas);
                    }
                } finally {
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }

                /* FPS measures */
                try {
                    Thread.sleep(Fps.getDelay());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    /* Series of event listeners. */
    public void onGameStateChanged(GameScreen state) {
        this.gameState = state;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stop();
    }

    public void stop() {
        if (thread != null) {
            thread.keepRunning = false;
            boolean retry = true;
            while (retry) {
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }
        ((Activity) getContext()).finish();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        ResourceManager.init(getResources(), width, height);
        if (gameState != null) {
            gameState.onSurfaceChanged(width, height);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean state = false;
        if (gameState != null) {
            state = gameState.onTouchEvent(event);
        }
        return state || super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean state = false;
        if (gameState != null) {
            state = gameState.onKeyDownEvent(keyCode, event);
        }
        return state || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean state = false;
        if (gameState != null) {
            state = gameState.onKeyUpEvent(keyCode, event);
        }
        return state || super.onKeyUp(keyCode, event);
    }
}
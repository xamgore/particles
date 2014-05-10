package com.xamgore.particles.core;

import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Полотно. Умеет ловить нажатия и отрисовывать канвас.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawingThread thread;

    public GameView(Context context) {
        super(context);

        setFocusableInTouchMode(true);
        getHolder().addCallback(this);
    }

    /*
     * Callback for when the surface has been created
     * @param SurfaceHolder The surface holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.keepRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
                thread = null;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Main.game.onSurfaceChanged(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return Main.game.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return Main.game.onKeyDownEvent(keyCode, event) ||
                super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return Main.game.onKeyUpEvent(keyCode, event) ||
                super.onKeyUp(keyCode, event);
    }

    private class DrawingThread extends Thread {
        public boolean keepRunning = true;
        private Canvas canvas;

        @Override
        public void run() {
            SurfaceHolder mSurfaceHolder = getHolder();

            while (keepRunning) {
                Fps.startMeasuringDelay();

                /* Game mechanics */
                Main.game.update();

                /* Drawings */
                canvas = null;
                try {
                    canvas = mSurfaceHolder.lockCanvas();
                    if (canvas != null) {
                        // Send request to Game class
                        Main.game.draw(canvas);
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
}
package com.xamgore.particles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView
        implements SurfaceHolder.Callback {

    // TODO: Move to Graphics
    private static final int BACKGROUND_COLOR = 0xff191919; // #222

    private Paint paint;
    private DrawingThread thread;
    private final ParticleSystem particleSystem;


    public GameSurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);  // may be NULL
        particleSystem = new ParticleSystem();
    }

    /*
     * Callback for when the surface has been created
     * @param SurfaceHolder The surface holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        thread = new DrawingThread();
        thread.keepRunning = true;
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int newW, int newH) {
        particleSystem.makeOutburst(newW, newH);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.keepRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;

                paint = null;
                thread = null;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Fill background
        paint.setColor(BACKGROUND_COLOR);
        canvas.drawPaint(paint);

        // Draw particles
        particleSystem.draw(canvas);

        // Draw FPS
        paint.setColor(Color.WHITE);
        canvas.drawText(Fps.getFpsAsString(), 10, 10, paint);
        canvas.drawText(String.valueOf(particleSystem.count()), 50, 10, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // ACTION_DOWN || ACTION_POINTER_DOWN || ACTION_MOVE
        if (event.getAction() >= 0 && event.getAction() < 3)
            synchronized (particleSystem) {
                boolean multiTouch = event.getPointerCount() > 1;

                // Create flushes under each finger
                for (int i = 0; i < event.getPointerCount(); i++)
                    particleSystem.makeFlush(
                            event.getX(i), event.getY(i), multiTouch
                    );

                return true;
            }

        // XXX: Is it better to return only true?
        return super.onTouchEvent(event);
    }

    public void setHighQuality(boolean state) {
        synchronized (particleSystem) {
            particleSystem.paint.setAntiAlias(state);
        }
    }

    private static boolean FLAG_BACGKROUND_ANIMATION;

    public void showBackgroundAnimation(boolean state) {
        FLAG_BACGKROUND_ANIMATION = state;
    }


    private class DrawingThread extends Thread {
        public boolean keepRunning = true;

        @Override
        public void run() {
            SurfaceHolder mSurfaceHolder = getHolder();
            Canvas c;

            while (keepRunning) {
                Fps.startMeasuringDelay();
                synchronized (particleSystem) {
                    if (FLAG_BACGKROUND_ANIMATION)
                        particleSystem.makeFlush(
                                mSurfaceHolder.getSurfaceFrame().centerX(),
                                mSurfaceHolder.getSurfaceFrame().centerY(),
                                false
                        );

                    particleSystem.update();
                }
                c = null;

                try {
                    c = mSurfaceHolder.lockCanvas();
                    if (c != null) {
                        synchronized (particleSystem) {
                            onDraw(c);
                        }
                    }
                } finally {
                    if (c != null)
                        mSurfaceHolder.unlockCanvasAndPost(c);
                }

                try {
                    Thread.sleep(Fps.getDelay());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
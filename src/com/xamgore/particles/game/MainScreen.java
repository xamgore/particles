package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.xamgore.particles.core.Fps;
import com.xamgore.particles.core.GameScreen;

import java.util.Random;

public class MainScreen extends GameScreen {
    private final ParticleSystem particleSystem;
    private final Paint paint;
    private static final int BACKGROUND_COLOR = 0xff191919;
    private static final Random rnd = new Random();

    private int colorThemeNum;
    private final static int[][] COLOURS = {
            {0xff69D2E7, 0xffA7DBD8, 0xffE0E4CC,
                    0xffF38630, 0xffFA6900, 0xffFF4E50, 0xffF9D423},
            {0xfffbd887, 0xffc6d96d, 0xff2cd7b7, 0xff0f747c},
            {0xfffef5ba, 0xffd99290, 0xff7d9f8f, 0xff70526e, 0xff40385d},
            {0xfff9af56, 0xffee655b, 0xffc93766, 0xff532a28, 0xffffffff},
            {0xffb4cb85, 0xfff16b50, 0xffea2540, 0xff019690},
            {0xfff1ab17, 0xfffe7e11, 0xff99c91b, 0xff69b6dc},
            {0xffb2f1ff, 0xffc2a2ad, 0xff989b90, 0xff645365},
            {0xfffef4b6, 0xffffd6a2, 0xffafd0fd, 0xff5785e3},
    };

    public MainScreen() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        colorThemeNum = rnd.nextInt(COLOURS.length);
        Particle.colours = COLOURS[colorThemeNum];

        particleSystem = new ParticleSystem();
    }

    @Override
    public void onDraw(Canvas canvas) {
        // Fill background
        paint.setColor(BACKGROUND_COLOR);
        canvas.drawPaint(paint);

        // Draw particles
        particleSystem.draw(canvas, paint);

        // Draw FPS
        paint.setColor(Color.WHITE);
        canvas.drawText("FPS: " + Fps.getFpsAsString(), 10, 10, paint);
        canvas.drawText("HQ is " + (paint.isAntiAlias() ? "ON" : "OFF"), 70, 10, paint);
        canvas.drawText(String.valueOf(particleSystem.count()), 145, 10, paint);
    }

    @Override
    public void onUpdate() {
        particleSystem.update();
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        particleSystem.makeOutburst(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // ACTION_DOWN || ACTION_POINTER_DOWN || ACTION_MOVE
        if (event.getAction() >= 0 && event.getAction() < 3) {
            boolean multiTouch = event.getPointerCount() > 1;

            // Create flushes under each finger
            for (int i = 0; i < event.getPointerCount(); i++)
                particleSystem.makeFlush(
                        event.getX(i), event.getY(i), multiTouch
                );

            return true;
        }

        return false;
    }

    @Override
    public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                // Next theme
                colorThemeNum = (colorThemeNum + 1) % COLOURS.length;
                Particle.colours = COLOURS[colorThemeNum];
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                // Prev theme
                colorThemeNum = (COLOURS.length + colorThemeNum - 1) % COLOURS.length;
                Particle.colours = COLOURS[colorThemeNum];
                return true;
            case KeyEvent.KEYCODE_CAMERA:
                paint.setAntiAlias(!paint.isAntiAlias());
                return true;
        }

        return false;
    }
}

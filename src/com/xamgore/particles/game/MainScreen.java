package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.Fps;
import com.xamgore.particles.core.GameScreen;

import java.util.Random;

public class MainScreen extends GameScreen {
    private final ParticleSystem particleSystem = new ParticleSystem();
    private final static boolean FLAG_HQ_OPTION = true;
    private final static int BACKGROUND_COLOR = 0xff191919;

    public MainScreen() {
        particleSystem.pickRandomColor();

        this.keyDownEventListener = new KeyEventListener() {
            @Override
            public boolean onEvent(int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_VOLUME_DOWN:
                        // Next color theme
                        particleSystem.pickNextColorTheme();
                        return true;

                    case KeyEvent.KEYCODE_VOLUME_UP:
                        // Prev color theme
                        particleSystem.pickPrevColorTheme();
                        return true;
                }

                return false;
            }
        };

        this.touchEventListener = new TouchEventListener() {
            @Override
            public boolean onEvent(MotionEvent event) {
                if (event.getAction() == 0 && event.getX() > width - 30 && event.getY() > height - 30) {
                    OptionScreen nextScreen = new OptionScreen();
                    Core.updateGameState(nextScreen);
                }

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
        };

        paint.setStyle(Paint.Style.FILL);

        this.drawEventListener = new DrawEventListener() {
            @Override
            public void onEvent(Canvas canvas) {
                // Fill background
                canvas.drawColor(BACKGROUND_COLOR);

                // Draw particles
                particleSystem.draw(canvas, paint);

                // Draw option button
                paint.setColor(0x77ffffff);
                canvas.drawCircle(width, height, 30, paint);

                // Draw FPS
                paint.setColor(Color.WHITE);
                canvas.drawText("FPS: " + Fps.getFpsAsString(), 10, 10, paint);
                if (FLAG_HQ_OPTION) {
                    canvas.drawText("HQ is " + (paint.isAntiAlias() ? "ON" : "OFF"), 70, 10, paint);
                    canvas.drawText(String.valueOf(particleSystem.count()), 145, 10, paint);
                } else canvas.drawText(String.valueOf(particleSystem.count()), 70, 10, paint);
            }
        };

        this.updateEventListener = new UpdateEventListener() {
            @Override
            public void onEvent() {
                particleSystem.update();
            }
        };

        this.surfaceChangedEventListener = new SurfaceChangedEventListener() {
            @Override
            public void onEvent() {
                particleSystem.makeOutburst(width, height);
            }
        };
    }
}

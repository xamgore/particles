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
    private final static Random rnd = new Random();

    private boolean ANTI_ALIAS_FLAG = false;
    private Paint paint;

    private int colorThemeNum;
    private final static int BACKGROUND_COLOR = 0xff191919;
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

        this.keyDownEventListener = new KeyEventListener() {
            @Override
            public boolean onEvent(int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_VOLUME_DOWN:
                        // Next color theme
                        colorThemeNum = (colorThemeNum + 1) % COLOURS.length;
                        Particle.colours = COLOURS[colorThemeNum];
                        return true;

                    case KeyEvent.KEYCODE_VOLUME_UP:
                        // Prev color theme
                        colorThemeNum = (COLOURS.length + colorThemeNum - 1) % COLOURS.length;
                        Particle.colours = COLOURS[colorThemeNum];
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

                    if (FLAG_HQ_OPTION) {
                        ANTI_ALIAS_FLAG = !ANTI_ALIAS_FLAG;
                        paint.setAntiAlias(ANTI_ALIAS_FLAG);
                        Core.sleep(100);
                        return true;
                    }
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

        this.drawEventListener = new DrawEventListener() {
            @Override
            public void onEvent(Canvas canvas) {
                // Fill background
                canvas.drawColor(BACKGROUND_COLOR);

                // Draw particles
                particleSystem.draw(canvas, paint);

                // Draw option button
                paint.setAntiAlias(true);
                paint.setColor(0x77ffffff);
                canvas.drawCircle(width, height, 30, paint);
                paint.setAntiAlias(ANTI_ALIAS_FLAG);

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

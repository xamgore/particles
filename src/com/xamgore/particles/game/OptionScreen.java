package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.view.KeyEvent;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.GameScreen;

public class OptionScreen extends GameScreen {
    private float x, y, rad;

    public OptionScreen() {
        final GameScreen parent = Core.gameScreen;

        final ParticleSystem particles = new ParticleSystem();
        x = width / 2;
        y = height / 2;

        this.updateEventListener = new UpdateEventListener() {
            @Override
            public void onEvent() {
                particles.makeFlush(x, y, false);
                particles.update();

            }
        };

        this.keyDownEventListener = new KeyEventListener() {
            @Override
            public boolean onEvent(int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_VOLUME_DOWN:
                        // Next color theme
                        particles.pickNextColorTheme();
                        return true;

                    case KeyEvent.KEYCODE_VOLUME_UP:
                        // Prev color theme
                        particles.pickPrevColorTheme();
                        return true;

                    case KeyEvent.KEYCODE_BACK:
                        Core.updateGameState(parent);
                        Core.sleep(500);
                        return true;
                }
                return false;
            }
        };


        paint.setStyle(Paint.Style.FILL);

        this.drawEventListener = new DrawEventListener() {
            @Override
            public void onEvent(Canvas canvas) {
                paint.setAntiAlias(true);
                canvas.drawColor(Color.WHITE);
                particles.draw(canvas, paint);
                paint.setColor(Color.argb((int) (0.9*255), 255, 255, 255));
                canvas.drawCircle(x, y, 70, paint);
            }
        };


        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onEvent(SensorEvent event) {
                x = -10*event.values[0] + width/2;
                y = 20*event.values[1] + height/2;
                rad = 5*event.values[2] + 10;
            }
        };
    }
}

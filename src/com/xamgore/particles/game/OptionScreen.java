package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.GameScreen;

public class OptionScreen extends GameScreen {
    public OptionScreen() {
        final GameScreen parent = Core.gameScreen;

        this.keyDownEventListener = new KeyEventListener() {
            @Override
            public boolean onEvent(int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    try {
                        Core.updateGameState(parent);
                        Thread.sleep(500);
                    } catch(Exception e) {}

                    return true;
                }
                return false;
            }
        };


        final ParticleSystem particles = new ParticleSystem();


        this.updateEventListener = new UpdateEventListener() {
            @Override
            public void onEvent() {
                particles.makeFlush(0, 0, false);
                particles.update();
            }
        };

        paint.setStyle(Paint.Style.FILL);

        this.drawEventListener = new DrawEventListener() {
            @Override
            public void onEvent(Canvas canvas) {
                canvas.drawColor(Color.WHITE);
                particles.draw(canvas, paint);
                paint.setColor(Color.LTGRAY);
                canvas.drawCircle(0, 0, 80, paint);
            }
        };
    }
}

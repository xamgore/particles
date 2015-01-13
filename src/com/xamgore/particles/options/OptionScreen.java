package com.xamgore.particles.options;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.GameScreen;

public class OptionScreen extends GameScreen {
    private final Paint paint;

    public OptionScreen(final GameScreen parent) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);


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

        this.drawEventListener = new DrawEventListener() {
            @Override
            public void onEvent(Canvas canvas) {
                // Background
                canvas.drawColor(0xffffffff);
            }
        };
    }
}

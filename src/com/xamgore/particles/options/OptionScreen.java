package com.xamgore.particles.options;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.GameScreen;

public class OptionScreen extends GameScreen {
    private final Paint paint;
    private final GameScreen parent;

    public OptionScreen(GameScreen parent) {
        this.parent = parent;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        // Background
        canvas.drawColor(0xffffffff);
    }

    @Override
    public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                Core.updateGameState(parent);
                Thread.sleep(500);
            } catch(Exception e) {}

            return true;
        }

        return false;
    }
}

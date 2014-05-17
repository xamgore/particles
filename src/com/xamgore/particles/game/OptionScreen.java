package com.xamgore.particles.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import com.xamgore.particles.core.GameScreen;
import com.xamgore.particles.core.GameView;

public class OptionScreen extends GameScreen {
    private final Paint paint;
    private final GameScreen parent;

    public OptionScreen(GameScreen parent) {
        this.parent = parent;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // Background
        canvas.drawColor(0xffffffff);
    }

    @Override
    public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                Thread.sleep(500);
            } catch(Exception e) {}

            GameView.getInstance().gameState = parent;
        }

        return false;
    }
}

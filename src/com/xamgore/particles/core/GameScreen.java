package com.xamgore.particles.core;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class GameScreen {
    @Deprecated
    public void onCreate() {
    }

    public void onDraw(Canvas canvas) {
    }

    public void onUpdate() {
    }

    // ought to be overwritten
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
        return false;
    }

    public boolean onKeyUpEvent(int keyCode, KeyEvent event) {
        return false;
    }

    public void onSurfaceChanged(int width, int height) {
    }
}
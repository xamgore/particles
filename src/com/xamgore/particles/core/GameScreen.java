package com.xamgore.particles.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class GameScreen {
    protected int width, height;

    public void onDraw(Canvas canvas) {
    }

    public void onUpdate() {
    }

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
        this.width = width;
        this.height = height;
    }
}
package com.xamgore.particles.core;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import static com.xamgore.particles.core.GameState.ANIMATION_MAIN;

public class GameManager {
    public static Canvas canvas;
    private GameState currentState;

    public GameManager() {
        initState(ANIMATION_MAIN);
    }

    public void initState(GameState state) {
        currentState = state;
        currentState.onCreate();
    }

    public void draw(Canvas canvas) {
        this.canvas = canvas;
        currentState.onDraw();
    }

    public void update() {
        currentState.onUpdate();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return currentState.onTouchEvent(event);
    }

    public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
        return currentState.onKeyDownEvent(keyCode, event);
    }

    public boolean onKeyUpEvent(int keyCode, KeyEvent event) {
        return currentState.onKeyUpEvent(keyCode, event);
    }

    public void onSurfaceChanged(int width, int height) {
        currentState.onSurfaceChanged(width, height);
    }
}

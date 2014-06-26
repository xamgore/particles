package com.xamgore.particles.core;

import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * @author Goga
 */
public abstract class GameScreen {
    /**
     * Called when GameView wants to draw something.
     */
    public abstract void draw(Canvas canvas);

    /**
     * Should compute some game mechanics.
     */
    public void update() {
    }

    /**
     * Override this method to handle touch screen motion events.
     *
     * @return True if the event was handled, false otherwise.
     */
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * Called when a key down event has occurred.
     *
     * @return True if the event was handled, false otherwise.
     */
    public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
        return false;
    }

    /**
     * Called when a key up event has occurred.
     *
     * @return True if the event was handled, false otherwise.
     */
    public boolean onKeyUpEvent(int keyCode, KeyEvent event) {
        return false;
    }

    /**
     * This is called immediately after any structural changes (format or
     * size) have been made to the surface.  You should at this point update
     * the imagery in the surface.  This method is always called at least
     * once, after GameView has initialized.
     */
    public void onSurfaceChanged(int width, int height) {
    }
}
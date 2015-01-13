package com.xamgore.particles.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.SensorEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.EventListener;

/**
 * @author Goga
 */
public abstract class GameScreen {

    static public int width;
    static public int height;
    static public Paint paint = new Paint();

    public TouchEventListener touchEventListener = new TouchEventListener();
    public KeyEventListener keyDownEventListener = new KeyEventListener();
    public KeyEventListener keyUpEventListener   = new KeyEventListener();
    public DrawEventListener drawEventListener   = new DrawEventListener();
    public UpdateEventListener updateEventListener = new UpdateEventListener();
    public SensorEventListener sensorEventListener = new SensorEventListener();
    public SurfaceChangedEventListener surfaceChangedEventListener =
            new SurfaceChangedEventListener();

    public class KeyEventListener implements EventListener {
        /**
         * Called when a key event has occurred.
         *
         * @return True if the event was handled, false otherwise.
         */
        public boolean onEvent(int keyCode, KeyEvent event) { return false; }
    }

    public class TouchEventListener implements EventListener {
        /**
         * Override this method to handle touch screen motion events.
         *
         * @return True if the event was handled, false otherwise.
         */
        public boolean onEvent(MotionEvent event) { return false; }
    }

    public class DrawEventListener implements EventListener {
        /**
         * Called when GameView wants to draw something.
         */
        public void onEvent(Canvas canvas) {}
    }

    public class UpdateEventListener implements EventListener {
        /**
         * Should compute some game mechanics.
         */
        public void onEvent() {}
    }

    public class SensorEventListener implements EventListener {
        public void onEvent(SensorEvent event) {}
    }

    public class SurfaceChangedEventListener implements EventListener {
        /**
         * This is called immediately after any structural changes (format or
         * size) have been made to the surface.  You should at this point update
         * the imagery in the surface.  This method is always called at least
         * once, after GameView has initialized.
         */
        public void onEvent() {}
    }
}
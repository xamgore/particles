package com.xamgore.particles.core;

import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;

import static com.xamgore.particles.core.GameManager.canvas;
import static com.xamgore.particles.core.Graphics.BACKGROUND_COLOR;
import static com.xamgore.particles.core.Graphics.paint;

public enum GameState {
    ANIMATION_MAIN {
        @Override
        public void onDraw() {
            // Fill background
            Graphics.paint.setColor(BACKGROUND_COLOR);
            canvas.drawPaint(paint);

            // Draw particles
            particleSystem.draw(canvas);

            // Draw FPS
            paint.setColor(Color.WHITE);
            canvas.drawText("FPS: " + Fps.getFpsAsString(), 10, 10, paint);
            canvas.drawText("HQ is " + (Graphics.isHQ() ? "ON" : "OFF"), 70, 10, paint);
            canvas.drawText(String.valueOf(particleSystem.count()), 145, 10, paint);
        }

        @Override
        public void onUpdate() {
            particleSystem.update();
        }

        @Override
        public void onSurfaceChanged(int width, int height) {
            particleSystem.makeOutburst(width, height);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // ACTION_DOWN || ACTION_POINTER_DOWN || ACTION_MOVE
            if (event.getAction() >= 0 && event.getAction() < 3) {
                boolean multiTouch = event.getPointerCount() > 1;

                // Create flushes under each finger
                for (int i = 0; i < event.getPointerCount(); i++)
                    particleSystem.makeFlush(
                            event.getX(i), event.getY(i), multiTouch
                    );

                return true;
            }

            return false;
        }

        @Override
        public boolean onKeyDownEvent(int keyCode, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    Graphics.chooseNextTheme();
                    return true;
                case KeyEvent.KEYCODE_VOLUME_UP:
                    Graphics.choosePreviousTheme();
                    return true;
                case KeyEvent.KEYCODE_CAMERA:
                    Graphics.setHQ(!Graphics.isHQ());
                    return true;
            }

            return super.onKeyDownEvent(keyCode, event);
        }
    },

    ;

    private static ParticleSystem particleSystem = new ParticleSystem();

    public void onCreate() {
    }

    public void onDraw() {
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
    }
}

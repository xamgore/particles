package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Color;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.GameScreen;
import com.xamgore.particles.game.MainScreen;

public class BlankScreen extends GameScreen {
    public BlankScreen() {
        this.drawEventListener = new DrawEventListener() {
            @Override
            public void onEvent(Canvas canvas) {
                canvas.drawColor(Color.DKGRAY);
            }
        };

        this.surfaceChangedEventListener = new SurfaceChangedEventListener() {
            @Override
            public void onEvent(int screenWidth, int screenHeight) {
                Core.updateGameState(new MainScreen(screenWidth, screenHeight));
            }
        };
    }
}
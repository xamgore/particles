package com.xamgore.particles.options;

import android.graphics.Canvas;
import android.graphics.Color;
import com.xamgore.particles.core.Core;
import com.xamgore.particles.core.GameScreen;
import com.xamgore.particles.game.MainScreen;

public class BlankScreen extends GameScreen {
    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        Core.updateGameState(new MainScreen(width, height));
    }
}
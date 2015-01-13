package com.xamgore.particles.core;

import android.app.Activity;

/**
 * Contains links to static classes, contains the context, etc.
 */
public class Core {
    private static Core _instance = null;

    public static GameView gameView;
    public static GameScreen gameScreen;

    private Core(Activity ctx, GameScreen screen) {
        // load all modules
        gameScreen = screen;
        gameView = new GameView(ctx, screen);
        ctx.setContentView(gameView);
    }

    public static Core connectWith(Activity ctx, GameScreen screen) {
        return _instance = new Core(ctx, screen);
    }

    /* For game */
    public static void updateGameState(GameScreen screen) {
        gameScreen = screen;
        gameView.onGameStateChanged(screen);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

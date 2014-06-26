package com.xamgore.particles.core;

import android.content.Context;

/**
 * Contains links to static classes, contains the context, etc.
 */
public class Core {
    private static Core _instance = null;

    private static GameView gameView;
    private static GameScreen gameState;

    private Core(Context ctx, GameScreen screen) {
        // load all modules
        gameState = screen;
        gameView = new GameView(ctx, screen);
    }

    public static Core connectWith(Context context, GameScreen screen) {
        return _instance = new Core(context, screen);
    }

    /* For game */
    public static void updateGameState(GameScreen screen) {
        gameState = screen;
        gameView.onGameStateChanged(screen);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /* Getters */
    public static GameScreen gameScreen() {
        return gameState;
    }

    public static GameView getView() {
        return gameView;
    }

}

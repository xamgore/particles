package com.xamgore.particles.core;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Contains links to static classes, contains the context, etc.
 */
public class Core {
    private static Core _instance = null;

    public static GameView gameView;
    public static GameScreen gameScreen;
    private static SensorManager sensorManager;
    private static Sensor accelerometer;

    private Core(Activity ctx, GameScreen screen) {
        // load all modules
        gameScreen = screen;
        gameView = new GameView(ctx, screen);
        ctx.setContentView(gameView);


        sensorManager = (SensorManager) ctx.getSystemService(Activity.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        registerAccelerometer();
        //vibrateThreshold = accelerometer.getMaximumRange() / 2;
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

    /* Sensor control */
    public static void registerAccelerometer() {
        if (accelerometer != null)
            sensorManager.registerListener(gameView, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public static void unregisterAccelerometer() {
        sensorManager.unregisterListener(gameView);
    }
}

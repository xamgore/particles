/*
 * Author: Goga
 * Help: aNNiMON, Oak
 * Idea: Justin Windle
 */

package com.xamgore.particles.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class Main extends Activity {
    public static Context context = null;
	public static GameView gameView;
    public static GameManager game;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        game = new GameManager();
        gameView = new GameView(this);
        setContentView(gameView);
        context = getApplicationContext();
	}
}
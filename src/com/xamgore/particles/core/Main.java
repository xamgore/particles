/*
 * Author: Goga
 * Help: aNNiMON, Oak
 * Idea: Justin Windle
 */

package com.xamgore.particles.core;

import android.app.Activity;
import android.os.Bundle;
import com.xamgore.particles.game.MainScreen;

public class Main extends Activity {
	private GameView gameView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        GameScreen gs = new MainScreen();
        gameView = new GameView(this, gs);
        setContentView(gameView);
	}
}
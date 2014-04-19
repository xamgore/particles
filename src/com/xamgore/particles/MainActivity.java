/*
 * Author: XakepPRO
 * Help: aNNiMON, Oak
 * Idea: Justin Windle
 */

package com.xamgore.particles;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private GameSurfaceView gameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Turn on the backlight
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
							 WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		gameView = new GameSurfaceView(this);
		gameView.setFocusable(true);
		setContentView(gameView);
	}
}
/*
 * Author: XakepPRO
 * Help: aNNiMON, Oak
 * Idea: Justin Windle
 */

package com.xamgore.particles;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	private GameSurfaceView gameView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        final int FLAG_NEEDS_MENU_KEY = 0x00000400;
        getWindow().setFlags(FLAG_NEEDS_MENU_KEY, FLAG_NEEDS_MENU_KEY);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		gameView = new GameSurfaceView(this);
		gameView.setFocusable(true);
		setContentView(gameView);
	}

    /* Context menu */
    private final static int MENU_OPTION_HQ = 0;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        gameView.showBackgroundAnimation(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        gameView.showBackgroundAnimation(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.addSubMenu(0, MENU_OPTION_HQ, Menu.NONE, R.string.menu_option_hq);
        menu.setGroupCheckable(0, true, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case MENU_OPTION_HQ:
                final boolean state = !item.isChecked();
                item.setChecked(state);
                gameView.setHighQuality(state);

                // notify user about changes
                String status = "HQ is " + (state ? "on" : "off");
                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                break;
        }

        gameView.showBackgroundAnimation(false);
        return false;
    }
}
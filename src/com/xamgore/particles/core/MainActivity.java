/*
 * Author: Goga
 * Help: aNNiMON, Oak
 * Idea: Justin Windle
 */

package com.xamgore.particles.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import com.xamgore.particles.options.BlankScreen;

/**
 * @author Xamgore
 */
public class MainActivity extends Activity {
    private Core core;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set backlight always on.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        core = Core.connectWith(this, new BlankScreen());
        setContentView(Core.getView());
    }
}
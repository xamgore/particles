/*
 * Author: Goga
 * Help: aNNiMON, Oak
 * Idea: Justin Windle
 */

package com.xamgore.particles.core;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Starter extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("11111111111", "onCreate");

        setContentView(GameView.init(this));
    }
}
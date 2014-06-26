package com.xamgore.particles.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

/**
 * @author Goga
 */
public class ResourceManager {
    private static float densityScale;

    public static void init(Resources res, int width, int height) {
        final DisplayMetrics metrics = res.getDisplayMetrics();
        densityScale = (metrics.densityDpi / 160f);
    }

    public static float convertDpToPixel(float dp) {
        return dp * densityScale;
    }
}
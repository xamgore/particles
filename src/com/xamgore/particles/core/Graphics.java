package com.xamgore.particles.core;

import android.graphics.Paint;

import java.util.Random;


public class Graphics {
    public static final int BACKGROUND_COLOR = 0xff191919; // #222
    private static int colorTheme;
    private final static int[][] COLOURS = {
            { 0xff69D2E7, 0xffA7DBD8, 0xffE0E4CC,
                    0xffF38630, 0xffFA6900, 0xffFF4E50, 0xffF9D423 },
            { 0xfffbd887, 0xffc6d96d, 0xff2cd7b7, 0xff0f747c},
            { 0xfffef5ba, 0xffd99290, 0xff7d9f8f, 0xff70526e, 0xff40385d},
            { 0xfff9af56, 0xffee655b, 0xffc93766, 0xff532a28, 0xffffffff},
            { 0xffb4cb85, 0xfff16b50, 0xffea2540, 0xff019690},
            { 0xfff1ab17, 0xfffe7e11, 0xff99c91b, 0xff69b6dc},
            { 0xffb2f1ff, 0xffc2a2ad, 0xff989b90, 0xff645365},
            { 0xfffef4b6, 0xffffd6a2, 0xffafd0fd, 0xff5785e3},
    };

    public static final Paint paint;

    static {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        chooseRandomTheme();
    }

    public static int[] getColours() {
        return COLOURS[colorTheme];
    }

    public static void chooseRandomTheme() {
        colorTheme = new Random().nextInt(COLOURS.length);
    }

    public static void chooseNextTheme() {
        colorTheme = (colorTheme + 1) % COLOURS.length;
    }

    public static void choosePreviousTheme() {
        colorTheme = (COLOURS.length + colorTheme - 1) % COLOURS.length;
    }

    public static void setHQ(boolean state) {
        paint.setAntiAlias(state);
    }

    public static boolean isHQ() {
        return paint.isAntiAlias();
    }
}

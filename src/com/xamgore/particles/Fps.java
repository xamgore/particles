/* By aNNiMON from GipGameActivity */

package com.xamgore.particles;

public class Fps {

	private static final int MAX_FPS = 60;
	private static final int MAX_DELAY = 1000 / MAX_FPS;

	private static long currentFps;
	private static long counter = 0, startTime = 0;
	private static long startTimeForMeasureDelay = 0;

	public static String getFpsAsString() {
		counter++;
		if (startTime == 0) {
			startTime = System.currentTimeMillis();
		}
		if ((System.currentTimeMillis() - startTime) >= 1000) {
			currentFps = counter;
			counter = 0;
			startTime = System.currentTimeMillis();
		}
		return Long.toString(currentFps);
	}

	public static void startMeasuringDelay() {
		startTimeForMeasureDelay = System.currentTimeMillis();
	}

	public static long getDelay() {
		long delay = System.currentTimeMillis() - startTimeForMeasureDelay;
		return (delay > MAX_DELAY ? 0 : MAX_DELAY - delay);
	}
}

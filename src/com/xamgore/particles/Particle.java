package com.xamgore.particles;

import java.util.Random;

public class Particle {
    public int color;
    private final static int[] COLOURS = { 0xff69D2E7, 0xffA7DBD8, 0xffE0E4CC,
            0xffF38630, 0xffFA6900, 0xffFF4E50, 0xffF9D423 };
    private final static float PI = 2f * (float) FastMath.PI;
	private static final Random rnd = new Random();

    private boolean alive;
    public float x, y, radius;
	private float wander, theta, drag, vx, vy;


	public Particle(float x, float y, float radius) {
		this.radius = radius;
        this.alive = true;

		wander = rnd.nextFloat() * 1.5f + 0.5f;
		theta = rnd.nextFloat() * PI;
		drag = rnd.nextFloat() * 0.09f + 0.9f;
		color = COLOURS[rnd.nextInt(COLOURS.length)];

		this.x = x;
		this.y = y;
		int force = rnd.nextInt(7) + 2;

		vx = (float) (Math.sin(theta) * force);
		vy = (float) (Math.cos(theta) * force);
	}

	public boolean isAlive() {
		return alive;
	}

	public void move() {
		x += vx;
		y += vy;

		vx *= drag;
		vy *= drag;

		theta += (rnd.nextFloat() - 0.5F) * wander;
		vx += Math.sin(theta) * 0.1F;
		vy += Math.cos(theta) * 0.1F;

		radius *= 0.96F;
		alive = (radius > 2);
	}
}
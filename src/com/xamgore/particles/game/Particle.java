package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Particle {
    public final int color;
    private final static float PI = 2f * (float) (105414357.0 / 33554432.0 + 1.984187159361080883e-9);
	private final static Random rnd = new Random();

    public static int[] colours = {0};

    private boolean alive;
    public float x, y, radius;
	private float wander, theta, drag, vx, vy;


	public Particle(float x, float y, float radius) {
		this.radius = radius;
        this.alive = true;

		wander = rnd.nextFloat() * 1.5f + 0.5f;
		theta = rnd.nextFloat() * PI;
		drag = rnd.nextFloat() * 0.09f + 0.9f;
		color = colours[rnd.nextInt(colours.length)];

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

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }
}
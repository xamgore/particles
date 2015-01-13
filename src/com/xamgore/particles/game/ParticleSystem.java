package com.xamgore.particles.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;


public class ParticleSystem {
    private static final Random rnd = new Random();
    private LinkedList<Particle> particles;

    private final static int MAX_PARTICLES_HARDMODE = 380;
    private final static int MAX_PARTICLES = 280;

    private int colorThemeNum;
    private final static int[][] COLOURS = {
            {0xff69D2E7, 0xffA7DBD8, 0xffE0E4CC,
                    0xffF38630, 0xffFA6900, 0xffFF4E50, 0xffF9D423},
            {0xfffbd887, 0xffc6d96d, 0xff2cd7b7, 0xff0f747c},
            {0xfffef5ba, 0xffd99290, 0xff7d9f8f, 0xff70526e, 0xff40385d},
            {0xfff9af56, 0xffee655b, 0xffc93766, 0xff532a28, 0xffffffff},
            {0xffb4cb85, 0xfff16b50, 0xffea2540, 0xff019690},
            {0xfff1ab17, 0xfffe7e11, 0xff99c91b, 0xff69b6dc},
            {0xffb2f1ff, 0xffc2a2ad, 0xff989b90, 0xff645365},
            {0xfffef4b6, 0xffffd6a2, 0xffafd0fd, 0xff5785e3},
    };

    public ParticleSystem() {
        particles = new LinkedList<>();
    }


    public void pickRandomColor() {
        Particle.colours = COLOURS[colorThemeNum = rnd.nextInt(COLOURS.length)];
    }

    public void pickNextColorTheme() {
        colorThemeNum = (colorThemeNum + 1) % COLOURS.length;
        Particle.colours = COLOURS[colorThemeNum];
    }

    public void pickPrevColorTheme() {
        colorThemeNum = (COLOURS.length + colorThemeNum - 1) % COLOURS.length;
        Particle.colours = COLOURS[colorThemeNum];
    }


    /**
     * @return кол-во частиц в системе.
     */
    public int count() {
        return particles.size();
    }

    /**
     * Добавляет новую частицу в систему.
     *
     * @param x        координата оси Ox
     * @param y        координата оси Oy
     * @param hardmode режим увеличенного кол-ва частиц
     */
    private synchronized void addParticle(float x, float y, boolean hardmode) {
        if (particles.size() >= (hardmode ? MAX_PARTICLES_HARDMODE
                : MAX_PARTICLES)) {
            particles.poll();
        }
        particles.addLast(new Particle(x, y, rnd.nextInt(70) + 20));
    }

    /**
     * Создание небольшой вспышки из 1-4 частиц.
     *
     * @param hardmode режим увеличенного кол-ва частиц
     */
    public synchronized void makeFlush(float x, float y, boolean hardmode) {
        final int count = rnd.nextInt(4) + 1;
        for (int i = 0; i < count; i++)
            addParticle(x, y, hardmode);
    }


    /**
     * Создание вспышки частиц в центре прямоугольника размера width * height.
     */
    public synchronized void makeOutburst(int width, int height) {
        // Set off some initial particles
        float x, y;

        for (int i = 0; i < 20; i++) {
            x = (float) ((width / 2) + rnd.nextInt(200) - 100);
            y = (float) ((height / 2) + rnd.nextInt(200) - 100);
            addParticle(x, y, false);
        }
    }

    /**
     * Вывод частиц на canvas.
     */
    public synchronized void draw(Canvas canvas, Paint paint) {
        for (Particle e : particles) {
            e.draw(canvas, paint);
        }
    }


    /**
     * Обновление состояния системы частиц.
     * Старые частицы удаляются, новые изменяют своё положение.
     */
    public synchronized void update() {
        ListIterator<Particle> iter = particles.listIterator();

        while (iter.hasNext()) {
            Particle elem = iter.next();
            if (elem.isAlive())
                elem.move();
            else iter.remove();
        }
    }
}
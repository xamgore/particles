package com.xamgore.particles;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;


public class ParticleSystem {
    private static final Random rnd = new Random();
    private static final Paint paint = new Paint();


    private final static int MAX_PARTICLES_HARDMODE = 380;
    private final static int MAX_PARTICLES = 280;
    private LinkedList<Particle> particles;


    public ParticleSystem() {
        particles = new LinkedList<>();
    }


    /**
     * @return кол-во частиц в системе.
     */
    public int count() {
        return particles.size();
    }


    /**
     * Добавляет новую частицу в систему.
     * @param x координата оси Ox
     * @param y координата оси Oy
     * @param hardmode режим увеличенного кол-ва частиц
     */
    private void addParticle(float x, float y, boolean hardmode) {
        if (particles.size() >= (hardmode ? MAX_PARTICLES_HARDMODE
                : MAX_PARTICLES)) {
            particles.poll();
        }
        particles.addLast(new Particle(x, y, rnd.nextInt(70) + 20));
    }

    /**
     * Создание небольшой вспышки из 1-4 частиц.
     */
    public void makeFlush(float x, float y, boolean hardmode) {
        final int count = rnd.nextInt(4) + 1;
        for (int i = 0; i < count; i++)
            addParticle(x, y, hardmode);
    }


    /**
     * Создание вспышки частиц в центре прямоугольника размера width * height.
     */
    public void makeOutburst(int width, int height) {
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
     * TODO перенести в класс Graphics.
     */
    public void draw(Canvas canvas) {
        for (Particle e : particles) {
            paint.setColor(e.color);
            canvas.drawCircle(e.x, e.y, e.radius, paint);
        }
    }


    /**
     * Обновление состояния системы частиц.
     * Старые частицы удаляются, новые изменяют своё положение.
     */
    public void update() {
        ListIterator<Particle> iter = particles.listIterator();

        while (iter.hasNext()) {
            Particle elem = iter.next();
            if (elem.isAlive())
                elem.move();
            else iter.remove();
        }
    }

}

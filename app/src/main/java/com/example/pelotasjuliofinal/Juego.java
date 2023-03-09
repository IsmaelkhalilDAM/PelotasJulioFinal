package com.example.pelotasjuliofinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Juego implements Runnable, View.OnTouchListener {

    private static final int NUMPELOTAS = 25;

    private final Pelota pelota;
    private SurfaceHolder holder;
    private float width;
    private float height;
    private final Paint paint;
    private volatile boolean fin;
    private Thread gameLoop;
    private float vAngular = 35;
    private float angulo = 0;
    private float px;
    private float py;

    private final ArrayList<Pelota> pelotas = new ArrayList<>();

    public Juego() {
        pelota = new Pelota(150, 150, 50, 300, (float) Math.PI / 4, Color.RED, this);
        paint = new Paint();
    }


    public void iniciar(SurfaceHolder holder, int width, int height) {
        this.holder = holder;
        this.width = width;
        this.height = height;

        px = width / 2f;
        py = height / 2f;

        int xmin = 50;
        int xmax = width - 50;
        int ymin = 50;
        int ymax = height - 50;
        int anchomin = (int) (width * 0.1f);
        int anchomax = (int) (width * 0.5f);
        int altomin = (int) (height * 0.1f);
        int altomax = (int) (height * 0.5f);
        int radiomin = width > height ? (int) (width * .1f) : (int) (height * .1f);
        int radiomax = width > height ? (int) (width * .25f) : (int) (height * .25f);

        for (int i = 0; i < NUMPELOTAS; i++) {
            pelotas.add(new Pelota(150, 150, 50, 300, (float) Math.PI / 4, Color.RED, this));

        }
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    static float FPS = 60;
    static float NPF = 1000000000F / FPS;

    public void run() {
        fin = false;
        long t0 = System.nanoTime(), t1, lapso;
        float nanos = 0;
        while (!fin) {
            t1 = System.nanoTime();
            lapso = t1 - t0;
            t0 = t1;
            nanos += lapso;
            if (nanos >= NPF) {
                nanos -= NPF;
                siguiente(NPF);
                pintar();
            }
        }
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void siguiente(float lapso) {
        pelotas.forEach(p->p.mover(lapso));

//        giro += (lapso * 10) / 1000000000f;
    }

    private void pintar(Canvas canvas) {
//        canvas.drawRect(0, 0, width, height, paint);

        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLACK);
        canvas.save();
        canvas.rotate(angulo, px, py);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        pelotas.forEach(p->p.paint(canvas));
        canvas.restore();
    }

    private void pintar() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            synchronized (holder) {
                pintar(canvas);
            }
        } catch (Exception e) {
        } finally {
            if (canvas != null)
                holder.unlockCanvasAndPost(canvas);
        }
    }

    private float x1;
    private float y1;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = motionEvent.getX();
                y1 = motionEvent.getY();
                Log.i("DOWN", String.format("(%.2f, %.2f)", x1, y1));
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                if (Math.abs(x1 - x2) > 20) {
                    float m = Math.abs((y2 - y1) / (x2 - x1));

                }

        }
        return true;
    }
}

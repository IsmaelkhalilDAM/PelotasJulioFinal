package com.example.pelotasjuliofinal;


import android.graphics.Canvas;
import android.graphics.Paint;

public class Pelota {
    private float x;
    private float y;
    private float radio;
    private float vx;
    private float vy;
    private Juego juego;
    private Paint paint;

    public Pelota(float x, float y , float radio, float v, float dir, int color, Juego juego) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        vx = v * (float) Math.cos(dir);
        vy = v * (float) Math.sin(dir);
        this.juego = juego;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    public void mover(float lapso) {
        x += vx * lapso * 9 / (1000000000f * 2);
        if (x + radio >= juego.getWidth()) {
            x -= (x + radio - juego.getWidth()) * 2;
            vx = -vx;
        } else if (x - radio <= 0) {
            x += (radio - x) * 2;
            vx = -vx;
        }
        y += vy * lapso * 9 / (1000000000f * 2);
        if (y + radio >= juego.getHeight()) {
            y -= (y + radio - juego.getHeight()) * 2;
            vy = -vy;
        } else if (y - radio <= 0) {
            y += (radio - y) * 2;
            vy = -vy;
        }
    }
   /* public void mover(float lapso) {
        switch (Aleatorio.sgte(1, 3)) {
            case 1:
                x += vx * lapso*9 / (1000000000f*2);
                if (x + radio >= juego.getWidth()) {
                    x -= (x + radio - juego.getWidth()) * 2;
                    vx = -vx;
                } else if (x - radio <= 0) {
                    x += (radio - x) * 4;
                    vx = -vx;
                }
                y += vy * lapso*9 / (1000000000f*2);
                if (y + radio >= juego.getHeight()) {
                    y -= (y + radio - juego.getHeight()) * 2;
                    vy = -vy;
                } else if (y - radio <= 0) {
                    y += (radio - y) * 2;
                    vy = -vy;
                }
                break;
            case 2:
                x += vx * lapso*9 / (1000000000f*3);
                if (x + radio >= juego.getWidth()) {
                    x -= (x + radio - juego.getWidth()) * 2;
                    vx = -vx;
                } else if (x - radio <= 0) {
                    x += (radio - x) * 2;
                    vx = -vx;
                }
                y += vy * lapso*9 / (1000000000f*3);
                if (y + radio >= juego.getHeight()) {
                    y -= (y + radio - juego.getHeight()) * 2;
                    vy = -vy;
                } else if (y - radio <= 0) {
                    y += (radio - y) * 2;
                    vy = -vy;
                }
               break;
            case 3:
                x += vx * lapso*9 / (1000000000f*1.5);
                if (x + radio >= juego.getWidth()) {
                    x -= (x + radio - juego.getWidth()) * 2;
                    vx = -vx;
                } else if (x - radio <= 0) {
                    x += (radio - x) * 2;
                    vx = -vx;
                }
                y += vy * lapso*9 / (1000000000f*1.5);
                if (y + radio >= juego.getHeight()) {
                    y -= (y + radio - juego.getHeight()) * 2;
                    vy = -vy;
                } else if (y - radio <= 0) {
                    y += (radio - y) * 2;
                    vy = -vy;
                }
                break;
        }

    }*/

    public void paint(Canvas canvas) {
        canvas.drawCircle(x, y, radio, paint);
    }

}

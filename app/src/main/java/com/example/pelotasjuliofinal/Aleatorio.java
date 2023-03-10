package com.example.pelotasjuliofinal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import java.util.Random;

public class Aleatorio {
    Resources res = getResources();
    private int [] listaColores;
    public static int sgte(int min, int max) {
        int l = max - min + 1;
        return ((int) (Math.random() * l * 1000) % l) + min;
    }

    public static Color colores(Context context){
    Random rng = new Random();
    listacolores = context.getResources().getIntArray(R.array.colores_array);
    int indiceAleatorio = rng.nextInt(listaColores.length);
    return listaColores[indiceAleatorio];

    }
}

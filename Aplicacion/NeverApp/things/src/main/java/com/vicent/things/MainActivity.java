package com.vicent.things;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    String producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Lista de UART disponibles: " + UartActivity.disponibles());

        UartActivity UART0 = new UartActivity("UART0", 9600);


        //Pedir a M5Stack la id
        Log.d(TAG, "Mandado a Arduino: h");

        UART0.escribir("H");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Log.w(TAG, "Error en sleep()", e);
        }

        int s = UART0.leer();
        if(s == 632){ producto = "Huevos 12U"; }
        if(s == 636){ producto = "Ketchup"; }
        if(s == 640){ producto = "Leche Entera"; }
        if(s == 644){ producto = "Zumo"; }
        if(s == 648){ producto = "Danones"; }
        if(s == 226){ producto = "Usuario 1"; }
        if(s == 322){ producto = "Usuario 2"; }



        Log.d(TAG, "------>Identificador: " + producto);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
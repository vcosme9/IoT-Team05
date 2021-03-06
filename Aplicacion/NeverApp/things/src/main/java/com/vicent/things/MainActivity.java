package com.vicent.things;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.PeripheralManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.local.MemoryPersistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;





public class MainActivity extends Activity  {
    private static final String TAG = MainActivity.class.getSimpleName();

    String producto;
    String fecha;

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
        if (s == 632) {
            producto = "Botella de Agua";
        }
        if (s == 636) {
            producto = "Ketchup";
        }
        if (s == 640) {
            producto = "Leche Entera";
        }
        if (s == 644) {
            producto = "Zumo";
        }
        if (s == 648) {
            producto = "Danones";
        }
        if (s == 226) {
            producto = "Usuario 1";
        }
        if (s == 322) {
            producto = "Usuario 2";
        }


        Log.d(TAG, "------>Identificador: " + producto);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        fecha = dateFormat.format(date);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        Map<String, Object> dato = new HashMap<>();
        dato.put("producto", producto);
        dato.put("fecha", fecha);


        db.collection("SENSORES").document("productos").collection("prod").add(dato);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
package com.vicent.neverapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SupermercadosActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageButton a = (ImageButton) findViewById(R.id.imageButton);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.es/maps/search/carrefour/@38.9694117,-0.1932218,15z/data=!3m1!4b1")));
            }
        });

        Button b2= (Button) findViewById(R.id.buscarCarrefour);
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TextView text =(TextView)findViewById(R.id.editTextCarrefour);
                String c= text.getText().toString();

                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.carrefour.es/global/?Dy=1&Nty=1&Ntx=mode+matchallany&Ntt=" + c + "&search=Buscar")));
            }
        });
    }
}

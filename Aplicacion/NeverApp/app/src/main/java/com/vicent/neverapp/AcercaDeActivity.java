package com.vicent.neverapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AcercaDeActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
    }

    public void abrirTwitterApp (View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.twitter.com/neverapp1"));
        startActivity(intent);
    }


}


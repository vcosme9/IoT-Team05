package com.vicent.neverapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_productos, R.id.nav_supermercados, R.id.nav_avisos,
                R.id.nav_camara, R.id.nav_configuracion, R.id.nav_perfil)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
//Prueba

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_salir) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (id == R.id.action_acercade) {
            startActivity(new Intent(this, AcercaDeActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void lanzarEditarPerfil (View view){

        Intent i = new Intent(this, EditarPerfilActivity.class);
        startActivity(i);
    }

    public void imagenCarrefour (View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.carrefour.es/")));
    }

    public void verMapaCarrefour (View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.es/maps/search/carrefour/@38.9694117,-0.1932218,15z/data=!3m1!4b1"));
        startActivity(intent);
    }

    public void buscarCarrefour (View view) {
        TextView texto = findViewById(R.id.editTextCarrefour);
        String c = texto.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.carrefour.es/global/?Dy=1&Nty=1&Ntx=mode+matchallany&Ntt=" + c + "&search=Buscar")));
    }

    public void imagenMercadona (View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.mercadona.es/")));
    }

    public void verMapaMercadona (View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.es/maps/search/mercadona/@38.9649664,-0.2082791,12.23z"));
        startActivity(intent);
    }

    public void buscarMercadona (View view){
        TextView texto = findViewById(R.id.editTextMercadona);
        String c = texto.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://tienda.mercadona.es/search-results?query=" + c)));
    }

       public void imagenMasYMas (View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.supermasymas.com/")));
    }

    public void verMapaMasYMas (View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.es/maps/search/masymas/@38.9724156,-0.1933869,12.94z"));
        startActivity(intent);
    }

    public void  buscarMasYMas (View view){

        TextView texto = findViewById(R.id.editTextMasYMas);
        String c = texto.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.supermasymas.com/tienda/listado.php?buscar=" + c + "&buscar_movil=")));
    }

    public void imagenConsum (View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.consum.es/")));
    }

    public void verMapaConsum (View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.es/maps/search/consum/@38.9554839,-0.19061,12.23z"));
        startActivity(intent);
    }

    public void  buscarConsum (View view){

        TextView texto = findViewById(R.id.editTextConsum);
        String c = texto.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://tienda.consum.es/consum/es/search?q=" + c + "#!Grid")));
    }


    public void llamarSoporte (View view) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:693303532"));
            startActivity(intent);
        } else {
            solicitarPermiso(Manifest.permission.CALL_PHONE, "Sin el permiso"+
                            " no puedo llamar al Soporte Técnico",
                    SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
        }

    }

    public static void solicitarPermiso(final String permiso, String
            justificacion, final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }})
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }

}

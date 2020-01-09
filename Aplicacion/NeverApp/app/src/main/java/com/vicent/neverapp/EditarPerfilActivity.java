package com.vicent.neverapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vicent.neverapp.R;
import com.vicent.neverapp.ui.perfil.PerfilFragment;

import java.util.HashMap;
import java.util.Map;

public class EditarPerfilActivity extends Activity {


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_editarperfil);

        /*FIREBASE*/

        FirebaseUser usuari = FirebaseAuth.getInstance().getCurrentUser();

        EditText etUsername;
        etUsername = findViewById(R.id.nombre_ET);
        etUsername.setHint(usuari.getDisplayName());
        EditText etPassword;
        etPassword = findViewById(R.id.contraseña_ET);
        etPassword.setHint("******");
        final EditText mTemperaturaET;
        mTemperaturaET = findViewById(R.id.temperatura_ET);

        /*OBTENER VALORES BASE DE DATOS*/
        obtenerDatos();



    }

public void confirmarEdicion (View view){
    final EditText editText_nombre = findViewById(R.id.nombre_ET);
    final EditText editText_conrtaseña = findViewById(R.id.contraseña_ET);



            new AlertDialog.Builder(this)
                    .setTitle("Editar Perfil")
                    .setMessage("¿Confirmar cambios?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {


                            FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest perfil = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(editText_nombre.getText().toString())
                                    .build();
                            usuario.updateProfile(perfil);
                            usuario.updatePassword(editText_conrtaseña.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                Log.e("NeverApp", "Acción incorrecta");
                                            }
                                        }
                                    });

                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();



}

public void volver (View view) {

    Intent i = new Intent(this, MainActivity.class);
    startActivity(i);

}

public void constrasenyaEmail (View view) {

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser usuaria = FirebaseAuth.getInstance().getCurrentUser();
        final String email;
        email = usuaria.getEmail();

        new AlertDialog.Builder(this)
                .setTitle("Cambio contraseña por correo")
                .setMessage("¿Está usted seguro de querer cambiar la contraseña mediante correo?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.setLanguageCode("es");
                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(EditarPerfilActivity.this, "Correo enviado", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(EditarPerfilActivity.this, "No se pudo enviar el correo", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();

}

public void cambiarExtra (View view) {


    final FirebaseUser usuarias = FirebaseAuth.getInstance().getCurrentUser();
    final String email;
    email = usuarias.getEmail();
    EditText temp;
    temp = findViewById(R.id.temperatura_ET);
    final String valor;
    valor = temp.getText().toString();
    int numEntero = Integer.parseInt(valor);

    if (numEntero >= 0 && numEntero < 31) {

        new AlertDialog.Builder(this)
                .setTitle("Cambiar Temperatura de Comfort")
                .setMessage("¿Confirmar cambios?" + "\n" + "Asegurese de haber introducido un valor")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        Map<String, Object> datos = new HashMap<>();
                        datos.put("temp", valor);
                        db.collection("Temperaturas").document(email).set(datos);
                        Toast.makeText(EditarPerfilActivity.this, "Valor Cambiado", Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    } else {
        Toast.makeText(EditarPerfilActivity.this, "El valor debe ser entre 0 y 30.", Toast.LENGTH_LONG).show();
    }




}

public void obtenerDatos () {

    final EditText mTemperaturaET;
    mTemperaturaET = findViewById(R.id.temperatura_ET);
    FirebaseUser usuari = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        String email;
        email = usuari.getEmail();
        mFirestore.collection("Temperaturas").document(email).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String titulo = documentSnapshot.getString("temp");
                    mTemperaturaET.setHint(titulo);
                }
            }
        });

}


}

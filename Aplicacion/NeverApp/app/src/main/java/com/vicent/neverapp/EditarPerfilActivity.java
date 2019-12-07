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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.vicent.neverapp.R;
import com.vicent.neverapp.ui.perfil.PerfilFragment;

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
                                                Log.e("MisLugares", "Acción incorrecta");
                                            }
                                        }
                                    });

                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();



}

public void volver (View view) {

    Intent i = new Intent(this, PerfilFragment.class);
    startActivity(i);

}


}

package com.vicent.neverapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login();
    }

    private void login() {
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        if (usuario != null) {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().setAllowNewAccounts(true).build(),
                            /*new AuthUI.IdpConfig.PhoneBuilder().build(),*/
                            new AuthUI.IdpConfig.GoogleBuilder().build())).build(), RC_SIGN_IN);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification();
        if (user.isEmailVerified() == true) {
            if (requestCode == RC_SIGN_IN) {
                if (resultCode == RESULT_OK) {
                    login();
                    finish();
                } else {
                    IdpResponse response = IdpResponse.fromResultIntent(data);
                    if (response == null) {
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
                        return;
                    } else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        Toast.makeText(this, "Sin conexión a Internet",
                                Toast.LENGTH_LONG).show();
                        return;
                    } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        Toast.makeText(this, "Error desconocido",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        } else {
            /*
            Hacer que si no está verificado, se le traslade a un nuevo activity
            que ponga que tiene que entrar al correo y verificarse antes de poder entrar
            en la aplicación. Habrá un boton bajo que ponga "volver al login", que lo
            llevará de vuelta para iniciar sesión.
            */
        }
    }
}
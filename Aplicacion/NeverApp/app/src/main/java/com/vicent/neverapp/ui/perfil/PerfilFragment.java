package com.vicent.neverapp.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vicent.neverapp.R;

public class PerfilFragment extends Fragment {

    private perfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(perfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Conectamos con el  FireBase.
        final FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

        //aqui se declaran las partes
        final TextView textView = root.findViewById(R.id.text_send);
        final TextView textViewnombre = root.findViewById(R.id.tv_nombreFB);
        final TextView textViewcorreo = root.findViewById(R.id.tv_correoFB);

        perfilViewModel.getText().observe(this, new Observer<String>() {
            @Override

            //Aqui se les añade el texto a las partes.
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                textViewnombre.setText(usuario.getDisplayName());
                textViewcorreo.setText(usuario.getEmail());


                /*
                String correo = usuario.getEmail();
                String telefono = usuario.getPhoneNumber();
                Uri urlFoto = usuario.getPhotoUrl()
                String uid = usuario.getUid();
                */
            }
        });
        return root;
    }
}
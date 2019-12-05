package com.vicent.neverapp.ui.perfil;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
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

        //final TextView textView = root.findViewById(R.id.text_send);
        final TextView textView_nombre = root.findViewById(R.id.nombre_TV);
        final TextView textView_correo = root.findViewById(R.id.correo_TV);
        final TextView textView_proveedor = root.findViewById(R.id.proveedorFB_TB);
        final TextView textView_rol = root.findViewById(R.id.rolFB_TB);



        perfilViewModel.getText().observe(this, new Observer<String>() {
            @Override
            //Aqui se les añade el texto a las partes.
            public void onChanged(@Nullable String s) {

                // Mostrar actual información del usuario
                textView_nombre.setText(usuario.getDisplayName());
                textView_correo.setText(usuario.getEmail());
                textView_proveedor.setText(usuario.getProviderId());
                textView_rol.setText("Rol no definido.");



            }
        });
        return root;
    }



}
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

        /* Coger foto de perfil del usuario del firebase

        RequestQueue colaPeticiones = Volley.newRequestQueue(getActivity()
                .getApplicationContext());
        ImageLoader lectorImagenes = new ImageLoader(colaPeticiones,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache =
                            new LruCache<String, Bitmap>(10);
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }
                });
        // Foto de usuario
        Uri urlImagen = usuario.getPhotoUrl();
        if (urlImagen != null) {
            NetworkImageView fotoUsuario = (NetworkImageView)
                    root.findViewById(R.id.imagen);
            fotoUsuario.setImageUrl(urlImagen.toString(), lectorImagenes);
        }
        */ //No funciona por ahora

        perfilViewModel.getText().observe(this, new Observer<String>() {
            @Override

            //Aqui se les añade el texto a las partes.
            public void onChanged(@Nullable String s) {

                textView_nombre.setText(usuario.getDisplayName());
                textView_correo.setText(usuario.getEmail());
                /*
                textView.setText(s);
                textViewnombre.setText(usuario.getDisplayName());
                textViewcorreo.setText(usuario.getEmail());
                */



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
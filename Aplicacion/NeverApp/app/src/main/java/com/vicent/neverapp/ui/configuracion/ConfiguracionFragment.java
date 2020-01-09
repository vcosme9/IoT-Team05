package com.vicent.neverapp.ui.configuracion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vicent.neverapp.R;

public class ConfiguracionFragment extends Fragment {

    private ConfiguracionViewModel configuracionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configuracionViewModel =
                ViewModelProviders.of(this).get(ConfiguracionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        /*Declaramos las vistas*/
        final EditText editText_receptor = root.findViewById(R.id.receptor);
        final EditText editText_asunto = root.findViewById(R.id.asunto);
        final EditText editText_mensaje = root.findViewById(R.id.mensaje);
        final Button boton_enviarEmail = root.findViewById(R.id.enviarEmail);
        final Button boton_llamarSoporte = root.findViewById(R.id.llamarSoporte);

        configuracionViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                boton_enviarEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String recep = editText_receptor.getText().toString().trim();
                        String asun = editText_asunto.getText().toString().trim();//TRIM Elimina espacios antes y despues del texto.
                        String mensa = editText_mensaje.getText().toString().trim();
                        enviarEmail(recep, asun, mensa);
                    }

                    private void enviarEmail(String recep, String asun, String mensa) {
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recep});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asun);
                        emailIntent.putExtra(Intent.EXTRA_TEXT, mensa);

                        try {
                            startActivity(Intent.createChooser(emailIntent   , "Elige proveedor"));
                        }
                        catch (Exception e ){
                           // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
        return root;
    }



}
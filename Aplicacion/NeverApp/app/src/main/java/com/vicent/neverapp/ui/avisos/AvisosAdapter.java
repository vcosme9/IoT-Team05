package com.vicent.neverapp.ui.avisos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.vicent.neverapp.R;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AvisosAdapter extends RecyclerView.Adapter<AvisosAdapter.AvisosViewHolder> {
    private static final String TAG = "AvisosAdapter";

    ArrayList<ClaseAviso> listaAvisos;
    String aviso;





    public AvisosAdapter(ArrayList<ClaseAviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }



    @NonNull
    @Override
    public AvisosAdapter.AvisosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,null,false);

        return new AvisosViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AvisosAdapter.AvisosViewHolder holder, int position) {
        holder.txtNombre.setText(listaAvisos.get(position).getTitulo());
        holder.txtInformacion.setText(listaAvisos.get(position).getDescripcion());
        holder.foto.setImageResource(listaAvisos.get(position).getImagenId());

    }

    @Override
    public int getItemCount() {
        return listaAvisos.size();
    }

    public class AvisosViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtInformacion;
        ImageView foto;

        public AvisosViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);

        }
    }

}

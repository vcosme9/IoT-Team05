package es.upv.adrian.neverapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.upv.adrian.neverapp.Supermercado;
import es.upv.adrian.neverapp.R;

public class SupermercadoAdaptador extends RecyclerView.Adapter<SupermercadoAdaptador.SupermercadoViewHolder> {

    ArrayList<Supermercado> listaSupermercado;

    public SupermercadoAdaptador(ArrayList<Supermercado> listaSupermercado) {
        this.listaSupermercado = listaSupermercado;
    }

    @Override
    public SupermercadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new SupermercadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SupermercadoViewHolder holder, int position) {
        holder.txtNombre.setText(listaSupermercado.get(position).getNombre());
        holder.txtInformacion.setText(listaSupermercado.get(position).getInfo());
        holder.foto.setImageResource(listaSupermercado.get(position).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listaSupermercado.size();
    }

    public class SupermercadoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtInformacion;
        ImageView foto;

        public SupermercadoViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}

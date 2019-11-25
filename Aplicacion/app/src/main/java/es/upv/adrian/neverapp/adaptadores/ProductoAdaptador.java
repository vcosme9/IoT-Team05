package es.upv.adrian.neverapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.upv.adrian.neverapp.Producto;
import es.upv.adrian.neverapp.R;

public class ProductoAdaptador extends RecyclerView.Adapter<ProductoAdaptador.ProductoViewHolder> {

    ArrayList<Producto> listaProductos;

    public ProductoAdaptador(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false); //cambiar layout probablemente
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        holder.txtNombreProducto.setText(listaProductos.get(position).getNombre());
        holder.txtInformacionProducto.setText(listaProductos.get(position).getInfo());
        holder.fotoProducto.setImageResource(listaProductos.get(position).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreProducto, txtInformacionProducto;
        ImageView fotoProducto;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            txtNombreProducto = (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacionProducto = (TextView) itemView.findViewById(R.id.idInfo);
            fotoProducto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}


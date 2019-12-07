package com.vicent.neverapp.ui.productos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vicent.neverapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder> {
    private static final String TAG = "ProductosAdapter";

    ArrayList<ClaseProducto> listaProductos;
    String producto;

    public ProductosAdapter(ArrayList<ClaseProducto> listaProducto) {
        this.listaProductos = listaProducto;

    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,null,false);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position) {
        holder.txtNombre.setText(listaProductos.get(position).getNombre());
        holder.txtInformacion.setText(listaProductos.get(position).getInfo());
        holder.foto.setImageResource(listaProductos.get(position).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }
    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtInformacion;
        ImageView foto;

        public ProductosViewHolder(View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.idNombre);
            txtInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }



    


}


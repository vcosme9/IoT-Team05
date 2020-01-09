package com.vicent.neverapp.ui.avisos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.vicent.neverapp.R;


import java.util.ArrayList;

public class AvisosFragment extends Fragment {
    private static final String TAG = "AvisosFragment";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<ClaseAviso> listaAvisos ;
    RecyclerView recyclerAvisos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_avisos, container, false);
        recyclerAvisos = (RecyclerView) vista.findViewById(R.id.recyclerId);
        final View vista2 = inflater.inflate(R.layout.layout_listitem, container, false);
        listaAvisos = new ArrayList<>();
        recyclerAvisos.setLayoutManager(new LinearLayoutManager(getContext()));


        loadDataFromFirestore();


        AvisosAdapter adapter = new AvisosAdapter(listaAvisos);
        recyclerAvisos.setAdapter(adapter);

        /*recyclerAvisos.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerAvisos ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TextView itemBorrar = (TextView) view.findViewById(R.id.idNombre);
                        String item = itemBorrar.getText().toString();
                        for(int i=0; i< listaAvisos.size(); i++){
                          String itemLista =  listaAvisos.get(i).getTitulo();
                            String itemListaID =  listaAvisos.get(i).getid();

                          if(item == itemLista){
                              db.collection("Avisos").document("avisos").collection("avis").document(itemListaID).delete();
                          }
                        }


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/

        return vista;
    }

    private void llenarlista(String avisoo) {



    }

    private void loadDataFromFirestore() {

        if (listaAvisos.size() > 0) {
            listaAvisos.clear();
        }

        //referencia la coleccion de firebase
        final CollectionReference medidasInfo = db.collection("Avisos").document("avisos").collection("avis");


        //coger la fecha mas nueva
        medidasInfo.orderBy("fecha", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                            Log.d(TAG, documentSnapshot.getId() + " => " + documentSnapshot.getData());


                            ClaseAviso mimedida = new ClaseAviso(documentSnapshot.getString("titulo"),documentSnapshot.getString("descripcion"), R.drawable.iconfinder_warning_3870073, documentSnapshot.getId());
                            listaAvisos.add(mimedida);

                        }



                        //el array pasa al adaptador
                       AvisosAdapter adaptador = new AvisosAdapter(listaAvisos);
                        recyclerAvisos.setAdapter(adaptador);

                    }
                });

    }
}
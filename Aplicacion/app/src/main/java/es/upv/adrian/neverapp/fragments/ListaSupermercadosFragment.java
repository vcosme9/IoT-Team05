package es.upv.adrian.neverapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.upv.adrian.neverapp.Supermercado;
import es.upv.adrian.neverapp.adaptadores.SupermercadoAdaptador;
import es.upv.adrian.neverapp.R;

public class ListaSupermercadosFragment extends Fragment{ // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListaSupermercadosFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerSupermercados;
    ArrayList<Supermercado> listaSupermercados;

    public ListaSupermercadosFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaSupermercadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaSupermercadosFragment newInstance(String param1, String param2) {
        ListaSupermercadosFragment listaSupermercadosFragment = new ListaSupermercadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        listaSupermercadosFragment.setArguments(args);
        return listaSupermercadosFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_lista_supermercado, container, false);
        listaSupermercados= new ArrayList<>();
        recyclerSupermercados=  vista.findViewById(R.id.recyclerId);
        recyclerSupermercados.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        SupermercadoAdaptador adaptador = new SupermercadoAdaptador(listaSupermercados);
        recyclerSupermercados.setAdapter(adaptador);

        return vista;
    }

    private void llenarLista() {
        listaSupermercados.add(new Supermercado("Carrefour", "Carrefour es una cadena multinacional de distribución de origen francés", R.drawable.logocarrefour)); //CAMBIAR LAS FOTOS Y LOS PARAMETROS
        listaSupermercados.add(new Supermercado("Mercadona", "Mercadona es una compañía española de distribución con sede en el municipio de Tabernes Blanques y origen en el cercano de Puebla de Farnals, ambos pertenecientes a la provincia de Valencia", R.drawable.logomercadona));
        listaSupermercados.add(new Supermercado("masymas", "Supermercados Masymas es una marca comercial de cuatro empresas: Hijos de Luis Rodríguez, S.A. en Asturias y León Juan Fornés Fornés, S.A. en Castellón, Valencia, Alicante y Murcia. Sucesores de Pedro Soriano Buforn, S.L. en Alicante y Valencia. Luis Piña, S.A. en Córdoba y Jaén.", R.drawable.logomasymas));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductosFragment.OnFragmentInteractionListener) {
            mListener = (ListaSupermercadosFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
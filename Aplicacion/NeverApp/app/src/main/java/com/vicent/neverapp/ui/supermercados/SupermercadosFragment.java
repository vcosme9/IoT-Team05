package com.vicent.neverapp.ui.supermercados;

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

import com.vicent.neverapp.R;

public class SupermercadosFragment extends Fragment {

    private SupermercadosViewModel supermercadosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        supermercadosViewModel =
                ViewModelProviders.of(this).get(SupermercadosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_supermercados, container, false);

        return root;



    }
}
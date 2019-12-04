package com.vicent.neverapp.ui.camara;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.vicent.neverapp.R;

public class CamaraFragment extends Fragment {

    private CamaraViewModel camaraViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        camaraViewModel =
                ViewModelProviders.of(this).get(CamaraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_camara, container, false);

        return root;
    }
}
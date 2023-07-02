package com.example.yachay_aplicacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Miperfil extends Fragment {


    public Miperfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_miperfil, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View v = getView();
        if (v!=null){
            TextView title = (TextView) v.findViewById(R.id.perfil);
            title.setText("Mi perfil");
        }
    }
}
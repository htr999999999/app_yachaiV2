package com.example.yachay_aplicacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Inicio extends Fragment implements View.OnClickListener {
    CardView modulo1,modulo2,modulo3,modulo4,modulo5,modulo6;
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    public Inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference= database.getReference("users");
        String id = auth.getCurrentUser().getUid();
        DatabaseReference username = reference.child(id).child("nombre");

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_inicio, container, false);

        username.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue().toString();
                textView = v.findViewById(R.id.tvnombre);
                textView.setText("Bienvenido "+username+ " !");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        modulo1 = v.findViewById(R.id.activi1);
        modulo1.setOnClickListener(this);
        modulo2 = v.findViewById(R.id.activi2);
        modulo2.setOnClickListener(this);
        modulo3 = v.findViewById(R.id.activi3);
        modulo3.setOnClickListener(this);
        modulo4 = v.findViewById(R.id.activi4);
        modulo4.setOnClickListener(this);
        modulo5 = v.findViewById(R.id.activi5);
        modulo5.setOnClickListener(this);
        modulo6 = v.findViewById(R.id.activi6);
        modulo6.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activi1:
                Intent intent = new Intent(getContext(), Modulo1.class);
                startActivity(intent);
                break;
            case R.id.activi2:
                Intent intent1 = new Intent(getContext(), Modulo2.class);
                startActivity(intent1);
                break;
            case R.id.activi3:
                Intent intent2 = new Intent(getContext(), Modulo3.class);
                startActivity(intent2);
                break;
            case R.id.activi4:
                Intent intent3 = new Intent(getContext(), Modulo4.class);
                startActivity(intent3);
                break;
            case R.id.activi5:
                Intent intent4 = new Intent(getContext(), Modulo5.class);
                startActivity(intent4);
                break;
            case R.id.activi6:
                Intent intent5 = new Intent(getContext(), Modulo6.class);
                startActivity(intent5);
                break;
        }


    }
}
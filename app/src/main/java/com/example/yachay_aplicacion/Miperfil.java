package com.example.yachay_aplicacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Miperfil extends Fragment {
    EditText nombre, apellido, correo, telefono;
    Button editar, guardar;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth, auth;


    public Miperfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_miperfil, container, false);
        nombre = v.findViewById(R.id.tvwnombre);
        apellido = v.findViewById(R.id.tvwapeliido);
        correo = v.findViewById(R.id.tvwcorreo);
        telefono = v.findViewById(R.id.tvwtelefono);
        editar  = v.findViewById(R.id.btneditarusuario);
        guardar=v.findViewById(R.id.btneditarguardar);
        TextView title = (TextView) v.findViewById(R.id.perfil);
        title.setText("Mi perfil");
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference= database.getReference("users");
        String id = auth.getCurrentUser().getUid();
        DatabaseReference nombre1 = reference.child(id).child("nombre");
        DatabaseReference apellido1 = reference.child(id).child("apellido");
        DatabaseReference correo1 = reference.child(id).child("email");
        DatabaseReference telefono1 = reference.child(id).child("telefono");
        nombre1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre1=snapshot.getValue().toString();
                nombre.setText(nombre1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        apellido1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String apellido1=snapshot.getValue().toString();
                apellido.setText(apellido1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        correo1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String correo1=snapshot.getValue().toString();
                correo.setText(correo1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        telefono1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String telefono1=snapshot.getValue().toString();
                telefono.setText(telefono1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        nombre.setEnabled(false);
        apellido.setEnabled(false);
        correo.setEnabled(false);
        telefono.setEnabled(false);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setEnabled(true);
                apellido.setEnabled(true);
                telefono.setEnabled(true);

            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombre.getText().toString();
                String ape = apellido.getText().toString();
                String tele = telefono.getText().toString();
                reference.child(id).child("nombre").setValue(nom);
                reference.child(id).child("apellido").setValue(ape);
                reference.child(id).child("telefono").setValue(tele);
                Toast.makeText(getContext(), "Datos guaradados correctamente", Toast.LENGTH_SHORT).show();
                nombre.setEnabled(false);
                apellido.setEnabled(false);
                telefono.setEnabled(false);


            }
        });
        return v;

    }

    @Override
    public void onStart() {
        super.onStart();



    }
}
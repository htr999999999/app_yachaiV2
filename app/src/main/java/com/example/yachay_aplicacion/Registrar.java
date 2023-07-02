package com.example.yachay_aplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrar extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextpassword, editTextNombre, editTextApellido, editTextTelefono;
    Button buttonreg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.contrasena);
        editTextNombre = findViewById(R.id.nombres);
        editTextApellido = findViewById(R.id.apellidos);
        editTextTelefono = findViewById(R.id.telefono);
        progressBar = findViewById(R.id.progressbar);
        txtview = findViewById(R.id.loginNow);
        buttonreg=findViewById(R.id.btnregister);

        txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });


        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password, nombre, apellido, telefono;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextpassword.getText());
                nombre = String.valueOf(editTextNombre.getText());
                apellido = String.valueOf(editTextApellido.getText());
                telefono = String.valueOf(editTextTelefono.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Registrar.this, "Ingresa el correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Registrar.this, "Ingresa la contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nombre)){
                    Toast.makeText(Registrar.this, "Ingresa los nombres", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(apellido)){
                    Toast.makeText(Registrar.this, "Ingresa el apellido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(telefono)){
                    Toast.makeText(Registrar.this, "Ingresa el telefono", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {


                                    Toast.makeText(Registrar.this, "Cuenta Creada.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Registrar.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}
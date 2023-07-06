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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextpassword;
    Button buttonlog;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView txtview;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference= database.getReference("users");
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            String nombre = getIntent().getStringExtra("nombre");
            String apellido = getIntent().getStringExtra("apellido");
            String telefono = getIntent().getStringExtra("telefono");
            String email = getIntent().getStringExtra("email");
            String password = getIntent().getStringExtra("password");
            Helper helper = new Helper(nombre,apellido,telefono,email,password);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uuid = user.getUid();
            reference.child(uuid).setValue(helper);

        }


        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Dashboard.class );
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.contrasena);
        progressBar = findViewById(R.id.progressbar);
        txtview = findViewById(R.id.registerNow);
        buttonlog=findViewById(R.id.btnlogin);

        txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registrar.class);
                startActivity(intent);
                finish();

            }
        });

        buttonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextpassword.getText());


                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Ingresa el correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Ingresa la contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Inicio Sesion.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class );
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
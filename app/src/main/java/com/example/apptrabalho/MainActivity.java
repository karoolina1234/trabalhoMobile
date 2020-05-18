package com.example.apptrabalho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser usuario;
    private  FirebaseAuth.AuthStateListener authStateListener;
    private   EditText etEmail, etSenha;
    private Button btnCadastrar, btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       etEmail = findViewById(R.id.etEmail);
       etSenha = findViewById(R.id.etSenha);
       btnCadastrar = findViewById(R.id.btnCadastrar);
       btnEntrar = findViewById(R.id.btnEntrar);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                usuario = auth.getCurrentUser();
                if(usuario!=null){
                    Intent intent = new Intent(MainActivity.this, listaAlunos.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "erro ao efetuar o login", Toast.LENGTH_LONG ).show();

                }
            }
        };
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logar();
                Intent intent = new Intent(MainActivity.this, listaAlunos.class);
                startActivity( intent );
            }
        });
    }
    private void logar(){
        String email  = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        if(!email.isEmpty() && !senha.isEmpty()){
            auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(! task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "erro ao realizar o login", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    private void cadastrar(){
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        if(!email.isEmpty() && !senha.isEmpty()){
            auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    usuario = auth.getCurrentUser();
                }
            });
        }
    }
}

package com.example.apptrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Formulario extends AppCompatActivity {

    private EditText etNomeAluno, etMatricula;
    private Button btnSalvar;

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNomeAluno = findViewById(R.id.etNomeAluno);
        etMatricula = findViewById(R.id.etMatricula);
        btnSalvar = findViewById(R.id.btnSalvar );

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }
    private void salvar(){
        String nome = etNomeAluno.getText().toString();
        String matricula = etMatricula.getText().toString();

        if(!nome.isEmpty() && !matricula.isEmpty()){
            Aluno aluno = new Aluno();
            aluno.nome = nome;
            aluno.matricula = matricula;

            database = FirebaseDatabase.getInstance();
            reference = database.getReference();
            reference.child("alunos").push().setValue(aluno);
            finish();
        }
    }
}

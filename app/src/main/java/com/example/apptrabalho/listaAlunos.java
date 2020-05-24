package com.example.apptrabalho;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listaAlunos extends AppCompatActivity {

    private ListView alunos;
    private List<Aluno> alunosList;
    private ArrayAdapter<Aluno> adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listaAlunos.this, Formulario.class);
                startActivity( intent );
            }
        });

        alunos = findViewById( R.id.lvAlunos );
        alunosList = new ArrayList<>();
        adapter = new ArrayAdapter<Aluno>(
                listaAlunos.this, android.R.layout.simple_list_item_1, alunosList);
        alunos.setAdapter( adapter );

    }

    @Override
    protected void onStart() {
        super.onStart();

        alunosList.clear();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("alunos").orderByChild("nome");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Aluno a = new Aluno();
                a.nome = dataSnapshot.child("nome").getValue(String.class);
                a.matricula = dataSnapshot.child("matricula").getValue(String.class);

                alunosList.add( a );

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };



        query.addChildEventListener( childEventListener );
    }



}

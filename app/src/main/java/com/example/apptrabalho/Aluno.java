package com.example.apptrabalho;

import androidx.annotation.NonNull;

public class Aluno {
    String nome, matricula;

    @NonNull
    @Override
    public String toString() {
        return nome + " -" + matricula;
    }
}

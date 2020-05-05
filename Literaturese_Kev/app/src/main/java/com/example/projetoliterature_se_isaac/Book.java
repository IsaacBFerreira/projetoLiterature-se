package com.example.projetoliterature_se_isaac;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private String descricao;

    private int prioridade;

    public Book(String titulo, String descricao, int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public int getPrioridade() {
        return prioridade;
    }
}

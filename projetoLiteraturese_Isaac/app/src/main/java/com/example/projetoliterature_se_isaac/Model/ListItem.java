package com.example.projetoliterature_se_isaac.Model;

public class ListItem {
    public String nome, imageLink, autor, ano, genero;

    public ListItem(String nome, String imageLink, String autor, String ano, String genero) {
        this.nome = nome;
        this.imageLink = imageLink;
        this.autor = autor;
        this.ano = ano;
        this.genero = genero;
    }

    public ListItem() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

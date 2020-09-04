package com.example.projetoliterature_se_isaac.Model;

public class ListItem {
    public String nome, imageLink;

    public ListItem(String nome, String imageLink) {
        this.nome = nome;
        this.imageLink = imageLink;
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
}

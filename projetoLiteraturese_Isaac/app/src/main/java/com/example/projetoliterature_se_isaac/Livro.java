package com.example.projetoliterature_se_isaac;

public class Livro {
    private String autorLivro;
    private String anoLivro;
    private String generoLivro;
    private String resumoLivro;

    public Livro(String autorLivro, String anoLivro, String generoLivro, String resumoLivro) {
        this.autorLivro = autorLivro;
        this.anoLivro = anoLivro;
        this.generoLivro = generoLivro;
        this.resumoLivro = resumoLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public String getAnoLivro() {
        return anoLivro;
    }

    public String getGeneroLivro() {
        return generoLivro;
    }

    public String getResumoLivro() {
        return resumoLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public void setAnoLivro(String anoLivro) {
        this.anoLivro = anoLivro;
    }

    public void setGeneroLivro(String generoLivro) {
        this.generoLivro = generoLivro;
    }

    public void setResumoLivro(String resumoLivro) {
        this.resumoLivro = resumoLivro;
    }
}

package com.example.orkulture.modelos_banco_dados;

public class Grupo implements Cloneable{

    private int id;
    private String nome, categoria, tema, foto;

    public Grupo(String nome, String categoria, String tema, String foto){
        this.nome = nome;
        this.categoria = categoria;
        this.tema = tema;
        this.foto = foto;
    }

    public Grupo(int id, String nome, String categoria, String tema, String foto){
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.tema = tema;
        this.foto = foto;
    }

    public Grupo(Grupo g){
        this.id = g.id;
        this.nome = g.nome;
        this.categoria = g.categoria;
        this.tema = g.tema;
        this.foto = g.foto;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public Object clone(){

        Grupo clone = new Grupo(this);

        return clone;
    }
}

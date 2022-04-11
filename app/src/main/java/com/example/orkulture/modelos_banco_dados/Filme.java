package com.example.orkulture.modelos_banco_dados;

public class Filme implements Cloneable{
    private int id;
    private String nome, genero, ano, sinopse;

    public Filme(String nome, String genero, String ano, String sinopse){
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.sinopse = sinopse;
    }

    public Filme(int id, String nome, String genero, String ano, String sinopse){
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.sinopse = sinopse;
    }

    public Filme(Filme f){
        this.id = f.id;
        this.nome = f.nome;
        this.genero = f.genero;
        this.ano = f.ano;
        this.sinopse = f.sinopse;
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

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno() {
        return this.ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSinopse() {
        return this.sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public Object clone(){

        Filme clone = new Filme(this);

        return clone;
    }
}

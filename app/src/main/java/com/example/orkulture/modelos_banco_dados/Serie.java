package com.example.orkulture.modelos_banco_dados;

public class Serie implements Cloneable{
    private int id;
    private String nome, genero, ano, sinopse;

    public Serie(String nome, String genero, String ano, String sinopse){
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.sinopse = sinopse;
    }

    public Serie(int id, String nome, String genero, String ano, String sinopse){
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
        this.sinopse = sinopse;
    }

    public Serie(Serie s){
        this.id = s.id;
        this.nome = s.nome;
        this.genero = s.genero;
        this.ano = s.ano;
        this.sinopse = s.sinopse;
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

        Serie clone = new Serie(this);

        return clone;
    }
}

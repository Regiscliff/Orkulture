package com.example.orkulture.modelos_banco_dados;

public class Livro implements Cloneable{
    private int id;
    private String nome, genero, autor, ano;

    public Livro(String nome, String genero, String autor, String ano){
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
        this.ano = ano;
    }

    public Livro(int id, String nome, String genero, String autor, String ano){
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
        this.ano = ano;
    }
    public Livro(Livro l){
        this.id = l.id;
        this.nome = l.nome;
        this.genero = l.genero;
        this.autor = l.autor;
        this.ano = l.ano;
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

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return this.ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public Object clone(){

        Livro clone = new Livro(this);

        return clone;
    }
}

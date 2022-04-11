package com.example.orkulture.modelos_banco_dados;

public class Musica implements Cloneable{
    private int id;
    private String nome, cantor, genero, album;

    public Musica(String nome, String cantor, String genero, String album){
        this.nome = nome;
        this.cantor = cantor;
        this.genero = genero;
        this.album = album;
    }

    public Musica(int id, String nome, String cantor, String genero, String album){
        this.id = id;
        this.nome = nome;
        this.cantor = cantor;
        this.genero = genero;
        this.album = album;
    }

    public Musica(Musica m){
        this.id = m.id;
        this.nome = m.nome;
        this.cantor = m.cantor;
        this.genero = m.genero;
        this.album = m.album;
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

    public String getCantor() {
        return this.cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public Object clone(){

        Musica clone = new Musica(this);

        return clone;
    }
}

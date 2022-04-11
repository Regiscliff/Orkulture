package com.example.orkulture.modelos_banco_dados;

public class Usuario implements Cloneable{
    private int id;
    private String email, senha, nome, data_nascimento, telefone, foto, logado;

    public Usuario(String email, String senha, String nome, String telefone, String data_nascimento, String foto, String logado){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.foto = foto;
        this.logado = logado;
    }

    public Usuario(int id, String email, String senha, String nome, String telefone, String data_nascimento, String foto, String logado){
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.foto = foto;
        this.logado = logado;
    }

    public Usuario(String email, String senha, String nome, String telefone){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Usuario(String foto){
        this.foto = foto;
    }

    public Usuario(String nome, String logado){
        this.nome = nome;
        this.logado = logado;
    }

    public Usuario(Usuario u){
        this.id = u.id;
        this.email = u.email;
        this.senha = u.senha;
        this.nome = u.nome;
        this.telefone = u.telefone;
        this.data_nascimento = u.data_nascimento;
        this.foto = u.foto;
        this.logado = u.logado;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData_nascimento() {
        return this.data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLogado() {
        return this.logado;
    }

    public void setLogado(String logado) {
        this.logado = logado;
    }

    @Override
    public Object clone(){

        Usuario clone = new Usuario(this);

        return clone;
    }
}

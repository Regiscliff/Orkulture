package com.example.orkulture.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.orkulture.modelos_banco_dados.Filme;
import com.example.orkulture.modelos_banco_dados.Grupo;
import com.example.orkulture.modelos_banco_dados.Livro;
import com.example.orkulture.modelos_banco_dados.Musica;
import com.example.orkulture.modelos_banco_dados.Serie;
import com.example.orkulture.modelos_banco_dados.Usuario;

public class BancoSQLite extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Orkulture.db";

    private static final String TABELA_USUARIO = "usuario";
    private static final String ID_USUARIO = "id_usuario";
    private static final String EMAIL_USUARIO = "email_usuario";
    private static final String SENHA_USUARIO = "senha_usuario";
    private static final String NOME_USUARIO = "nome_usuario";
    private static final String TELEFONE_USUARIO = "telefone_usuario";
    private static final String DATA_NASCIMENTO_USUARIO = "data_nascimento_usuario";
    private static final String FOTO_USUARIO = "foto_usuario";
    private static final String USUARIO_LOGADO = "usuario_logado";

    private static final String TABELA_FILME = "filme";
    private static final String ID_FILME = "id_filme";
    private static final String NOME_FILME = "nome_filme";
    private static final String GENERO_FILME = "genero_filme";
    private static final String ANO_FILME = "ano_filme";
    private static final String SINOPSE_FILME = "sinopse_filme";

    private static final String TABELA_SERIE = "serie";
    private static final String ID_SERIE = "id_serie";
    private static final String NOME_SERIE = "nome_serie";
    private static final String GENERO_SERIE = "genero_serie";
    private static final String ANO_SERIE = "ano_serie";
    private static final String SINOPSE_SERIE = "sinopse_serie";

    private static final String TABELA_LIVRO = "livro";
    private static final String ID_LIVRO = "id_livro";
    private static final String NOME_LIVRO = "nome_livro";
    private static final String GENERO_LIVRO = "genero_livro";
    private static final String AUTOR_LIVRO = "autor_livro";
    private static final String ANO_LIVRO = "ano_livro";

    private static final String TABELA_MUSICA = "musica";
    private static final String ID_MUSICA = "id_musica";
    private static final String NOME_MUSICA = "nome_musica";
    private static final String CANTOR_MUSICA = "cantor_musica";
    private static final String GENERO_MUSICA = "genero_musica";
    private static final String ALBUM_MUSICA = "album_musica";

    private static final String TABELA_GRUPO = "grupo";
    private static final String ID_GRUPO = "id_grupo";
    private static final String NOME_GRUPO = "nome_grupo";
    private static final String CATEGORIA_GRUPO = "categoria_grupo";
    private static final String TEMA_GRUPO = "tema_grupo";
    private static final String FOTO_GRUPO = "foto_grupo";

    private static final int VERSAO =1;

    public BancoSQLite(Context context){
        super(context, NOME_BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABELA_USUARIO + " ( " +
                ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL_USUARIO + " TEXT, " + SENHA_USUARIO + " TEXT, " + NOME_USUARIO + " TEXT, " +
                TELEFONE_USUARIO + " TEXT, " + DATA_NASCIMENTO_USUARIO + " TEXT, " +
                FOTO_USUARIO + " VARCHAR(500), " + USUARIO_LOGADO + " TEXT )";
        db.execSQL(CREATE_TABLE_USUARIO);
        String CREATE_TABLE_FILME = "CREATE TABLE " + TABELA_FILME + " (" +
                ID_FILME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_FILME + " TEXT, " + GENERO_FILME + " TEXT, " +
                ANO_FILME + " TEXT, " + SINOPSE_FILME + " TEXT )";
        db.execSQL(CREATE_TABLE_FILME);
        String CREATE_TABLE_SERIE = "CREATE TABLE " + TABELA_SERIE + " (" +
                ID_SERIE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_SERIE + " TEXT, " + GENERO_SERIE + " TEXT, " +
                ANO_SERIE + " TEXT, " + SINOPSE_SERIE + " TEXT )";
        db.execSQL(CREATE_TABLE_SERIE);
        String CREATE_TABLE_LIVRO = "CREATE TABLE " + TABELA_LIVRO + " (" +
                ID_LIVRO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_LIVRO + " TEXT, " + GENERO_LIVRO + " TEXT, " +
                AUTOR_LIVRO + " TEXT, " + ANO_LIVRO + " TEXT )";
        db.execSQL(CREATE_TABLE_LIVRO);
        String CREATE_TABLE_MUSICA = "CREATE TABLE " + TABELA_MUSICA + " (" +
                ID_MUSICA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_MUSICA + " TEXT, " + CANTOR_MUSICA + " TEXT, " +
                GENERO_MUSICA + " TEXT, " + ALBUM_MUSICA + " TEXT )";
        db.execSQL(CREATE_TABLE_MUSICA);
        String CREATE_TABLE_GRUPO = "CREATE TABLE " + TABELA_GRUPO + " (" +
                ID_GRUPO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME_GRUPO + " TEXT, " + CATEGORIA_GRUPO + " TEXT, " +
                TEMA_GRUPO + " TEXT, " + FOTO_GRUPO + " TEXT )";
        db.execSQL(CREATE_TABLE_GRUPO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FILME);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_SERIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LIVRO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_MUSICA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_GRUPO);
        onCreate(db);

    }

    public boolean inserirUsuario(Usuario u){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_USUARIO, u.getEmail());
        values.put(SENHA_USUARIO, u.getSenha());
        values.put(NOME_USUARIO, u.getNome());
        values.put(TELEFONE_USUARIO, u.getTelefone());
        values.put(DATA_NASCIMENTO_USUARIO, u.getData_nascimento());
        values.put(FOTO_USUARIO, u.getFoto());
        values.put(USUARIO_LOGADO, u.getLogado());

        result = db.insert(TABELA_USUARIO, null, values);
        db.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean atualizarDadosUsuario(Usuario u, String id){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_USUARIO, u.getEmail());
        values.put(SENHA_USUARIO, u.getSenha());
        values.put(NOME_USUARIO, u.getNome());
        values.put(TELEFONE_USUARIO, u.getTelefone());

        result = db.update(TABELA_USUARIO, values, ID_USUARIO + " = ?", new String[]{id});
        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean atualizarFotoUsuario(Usuario u, String id){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOTO_USUARIO, u.getFoto());

        result = db.update(TABELA_USUARIO, values, ID_USUARIO + " = ?", new String[]{id});
        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean atualizarUsuarioLogado(Usuario u, String id){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USUARIO_LOGADO, u.getLogado());

        result = db.update(TABELA_USUARIO, values, ID_USUARIO + " = ?", new String[]{id});
        db.close();

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean inserirFilme(Filme f){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME_FILME, f.getNome());
        values.put(GENERO_FILME, f.getGenero());
        values.put(ANO_FILME, f.getAno());
        values.put(SINOPSE_FILME, f.getSinopse());

        result = db.insert(TABELA_FILME, null, values);
        db.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean inserirSerie(Serie s){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME_SERIE, s.getNome());
        values.put(GENERO_SERIE, s.getGenero());
        values.put(ANO_SERIE, s.getAno());
        values.put(SINOPSE_SERIE, s.getSinopse());

        result = db.insert(TABELA_SERIE, null, values);
        db.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean inserirLivro(Livro l){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME_LIVRO, l.getNome());
        values.put(GENERO_LIVRO, l.getGenero());
        values.put(AUTOR_LIVRO, l.getAutor());
        values.put(ANO_LIVRO, l.getAno());

        result = db.insert(TABELA_LIVRO, null, values);
        db.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean inserirMusica(Musica m){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME_MUSICA, m.getNome());
        values.put(CANTOR_MUSICA, m.getCantor());
        values.put(GENERO_MUSICA, m.getGenero());
        values.put(ALBUM_MUSICA, m.getAlbum());

        result = db.insert(TABELA_MUSICA, null, values);
        db.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean inserirGrupo(Grupo g){

        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME_GRUPO, g.getNome());
        values.put(CATEGORIA_GRUPO, g.getCategoria());
        values.put(TEMA_GRUPO, g.getTema());
        values.put(FOTO_GRUPO, g.getFoto());

        result = db.insert(TABELA_GRUPO, null, values);
        db.close();

        if(result == -1)
            return false;
        else
            return true;

    }

    public Usuario selecionarUsuario(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_USUARIO,
                new String[]{ID_USUARIO,EMAIL_USUARIO,SENHA_USUARIO,NOME_USUARIO,TELEFONE_USUARIO,
                        DATA_NASCIMENTO_USUARIO,FOTO_USUARIO,USUARIO_LOGADO},
                EMAIL_USUARIO + " = ?",
                new String[]{ String.valueOf(email) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Usuario user = new Usuario(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));

            return (Usuario) user.clone();
        }else
            return null;
    }

    public Usuario selecionarUsuarioPorID(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_USUARIO,
                new String[]{ID_USUARIO,EMAIL_USUARIO,SENHA_USUARIO,NOME_USUARIO,TELEFONE_USUARIO,
                        DATA_NASCIMENTO_USUARIO,FOTO_USUARIO,USUARIO_LOGADO},
                ID_USUARIO + " = ?",
                new String[]{ String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Usuario user = new Usuario(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));

            return (Usuario) user.clone();
        }else{
            return null;
        }
    }

    public Usuario selecionarUsuarioLogado(String logado){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_USUARIO,
                new String[]{ID_USUARIO,EMAIL_USUARIO,SENHA_USUARIO,NOME_USUARIO,TELEFONE_USUARIO,
                        DATA_NASCIMENTO_USUARIO,FOTO_USUARIO,USUARIO_LOGADO},
                USUARIO_LOGADO + " = ?",
                new String[]{ String.valueOf(logado) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Usuario user = new Usuario(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));

            return (Usuario) user.clone();
        }else{
            return null;
        }
    }

    public Usuario selecionarUsuarioPorNome(String nome){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_USUARIO,
                new String[]{ID_USUARIO,EMAIL_USUARIO,SENHA_USUARIO,NOME_USUARIO,TELEFONE_USUARIO,
                        DATA_NASCIMENTO_USUARIO,FOTO_USUARIO,USUARIO_LOGADO},
                NOME_USUARIO + " = ?",
                new String[]{ String.valueOf(nome) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Usuario user = new Usuario(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));

            return (Usuario) user.clone();
        }else{
            return null;
        }
    }

    public Filme selecionarFilme(String nomeFilme){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_FILME,
                new String[]{ID_FILME,NOME_FILME,GENERO_FILME,ANO_FILME,SINOPSE_FILME},
                NOME_FILME + " = ?",
                new String[]{ String.valueOf(nomeFilme) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Filme movie = new Filme(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Filme) movie.clone();
        }else
            return null;
    }

    public Filme selecionarFilmePorID(String idFilme){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_FILME,
                new String[]{ID_FILME,NOME_FILME,GENERO_FILME,ANO_FILME,SINOPSE_FILME},
                ID_FILME + " = ?",
                new String[]{ String.valueOf(idFilme) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Filme movie = new Filme(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Filme) movie.clone();
        }else
            return null;
    }

    public Serie selecionarSerie(String nomeSerie){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_SERIE,
                new String[]{ID_SERIE,NOME_SERIE,GENERO_SERIE,ANO_SERIE,SINOPSE_SERIE},
                NOME_SERIE + " = ?",
                new String[]{ String.valueOf(nomeSerie) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Serie serie = new Serie(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Serie) serie.clone();
        }else
            return null;
    }

    public Serie selecionarSeriePorID(String idSerie){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_SERIE,
                new String[]{ID_SERIE,NOME_SERIE,GENERO_SERIE,ANO_SERIE,SINOPSE_SERIE},
                ID_SERIE + " = ?",
                new String[]{ String.valueOf(idSerie) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Serie serie = new Serie(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Serie) serie.clone();
        }else
            return null;
    }

    public Livro selecionarLivro(String nomeLivro){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_LIVRO,
                new String[]{ID_LIVRO,NOME_LIVRO,GENERO_LIVRO,AUTOR_LIVRO,ANO_LIVRO},
                NOME_LIVRO + " = ?",
                new String[]{ String.valueOf(nomeLivro) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Livro livro = new Livro(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Livro) livro.clone();
        }else
            return null;
    }

    public Livro selecionarLivroPorID(String idLivro){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_LIVRO,
                new String[]{ID_LIVRO,NOME_LIVRO,GENERO_LIVRO,AUTOR_LIVRO,ANO_LIVRO},
                ID_LIVRO + " = ?",
                new String[]{ String.valueOf(idLivro) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Livro livro = new Livro(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Livro) livro.clone();
        }else
            return null;
    }

    public Musica selecionarMusica(String nomeMusica){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_MUSICA,
                new String[]{ID_MUSICA,NOME_MUSICA,CANTOR_MUSICA,GENERO_MUSICA,ALBUM_MUSICA},
                NOME_MUSICA + " = ?",
                new String[]{ String.valueOf(nomeMusica) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Musica musica = new Musica(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Musica) musica.clone();
        }else
            return null;
    }

    public Musica selecionarMusicaPorID(String idMusica){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_MUSICA,
                new String[]{ID_MUSICA,NOME_MUSICA,CANTOR_MUSICA,GENERO_MUSICA,ALBUM_MUSICA},
                ID_MUSICA + " = ?",
                new String[]{ String.valueOf(idMusica) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Musica musica = new Musica(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Musica) musica.clone();
        }else
            return null;
    }

    public Grupo selecionarGrupo(String nomeGrupo){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_GRUPO,
                new String[]{ID_GRUPO,NOME_GRUPO,CATEGORIA_GRUPO,TEMA_GRUPO,FOTO_GRUPO},
                NOME_GRUPO + " = ?",
                new String[]{ String.valueOf(nomeGrupo) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Grupo grupo = new Grupo(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Grupo) grupo.clone();
        }else
            return null;
    }

    public Grupo selecionarGrupoPorID(String idGrupo){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_GRUPO,
                new String[]{ID_GRUPO,NOME_GRUPO,CATEGORIA_GRUPO,TEMA_GRUPO,FOTO_GRUPO},
                ID_GRUPO + " = ?",
                new String[]{ String.valueOf(idGrupo) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();

            Grupo grupo = new Grupo(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));

            return (Grupo) grupo.clone();
        }else
            return null;
    }

}

package com.example.orkulture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.itens_menu_inferior.ListaConversas;
import com.example.orkulture.itens_menu_inferior.TelaBusca;
import com.example.orkulture.itens_menu_inferior.TelaConfiguracoes;
import com.example.orkulture.itens_menu_inferior.TelaGruposAmigos;
import com.example.orkulture.itens_menu_inferior.TelaInicial;
import com.example.orkulture.itens_menu_lateral.TelaFilmes;
import com.example.orkulture.itens_menu_lateral.TelaLivros;
import com.example.orkulture.itens_menu_lateral.TelaMusicas;
import com.example.orkulture.itens_menu_lateral.TelaSeries;
import com.example.orkulture.modelos_banco_dados.Filme;
import com.example.orkulture.modelos_banco_dados.Livro;
import com.example.orkulture.modelos_banco_dados.Musica;
import com.example.orkulture.modelos_banco_dados.Serie;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Perfil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txt_nomeUsuario, txt_filme, txt_serie, txt_livro, txt_musica;
    private View btn_fotoUsuario;
    private Uri mSelectedUri;
    private ImageView img_foto, btn_deslogar, btn_menu, btn_configuracoes, btn_chat, btn_grupos, btn_buscar, btn_inicio;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {

        btn_fotoUsuario.setOnClickListener(view -> selecionarFoto());

        btn_deslogar.setOnClickListener(view -> {
                BancoSQLite db = new BancoSQLite(this);
                if(db.atualizarUsuarioLogado(new Usuario(
                        "", "deslogado"), usuarioID)
                ){
                    Intent intent = new Intent(Perfil.this, TelaLogin.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
        });

        btn_menu.setOnClickListener(view -> {
            if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            drawerLayout.openDrawer(GravityCompat.START);
        });

        btn_chat.setOnClickListener(view -> {
            Intent intent = new Intent(Perfil.this, ListaConversas.class);
            startActivity(intent);
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(Perfil.this, TelaBusca.class);
            startActivity(intent);
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(Perfil.this, TelaInicial.class);
            startActivity(intent);
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(Perfil.this, TelaGruposAmigos.class);
            startActivity(intent);
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(Perfil.this, TelaConfiguracoes.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        BancoSQLite db = new BancoSQLite(this);
        Usuario usuario = db.selecionarUsuarioLogado("logado");
        usuarioID = String.valueOf(usuario.getId());
        txt_nomeUsuario.setText(usuario.getNome());
        CarregarImagem(usuario.getFoto());
        CarregarPreferencias();
        super.onStart();
    }

    private void CarregarPreferencias() {
        String filme = "", serie = "", livro = "", musica = "";
        BancoSQLite db = new BancoSQLite(this);

        try{
            for (int i = 1; i <= 3; i++) {
                Filme f = db.selecionarFilmePorID(String.valueOf(i));
                filme += f.getNome() + "\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            for (int i = 1; i <= 3; i++) {
                Serie s = db.selecionarSeriePorID(String.valueOf(i));
                serie += s.getNome() + "\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            for (int i = 1; i <= 3; i++) {
                Livro l = db.selecionarLivroPorID(String.valueOf(i));
                livro += l.getNome() + "\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            for (int i = 1; i <= 3; i++) {
                Musica m = db.selecionarMusicaPorID(String.valueOf(i));
                musica += m.getNome() + " - " + m.getCantor() + "\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        txt_filme.setText(filme);
        txt_serie.setText(serie);
        txt_livro.setText(livro);
        txt_musica.setText(musica);
    }

    private void selecionarFoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            mSelectedUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                img_foto.setImageDrawable(new BitmapDrawable(bitmap));
                btn_fotoUsuario.setAlpha(0);
                SalvarFoto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void SalvarFoto(){

       String filename = UUID.randomUUID().toString();
       final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/" + filename);
       ref.putFile(mSelectedUri)
               .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               Log.i("Teste", uri.toString());
                               InserirFotoConta(uri.toString());
                           }

                       });
                   }
               }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast toast = Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_SHORT);
               toast.show();
           }
       });
    }

    private void InserirFotoConta(String idFoto){
        BancoSQLite db = new BancoSQLite(this);
        Log.i("Teste", idFoto);
        if(db.atualizarFotoUsuario(new Usuario(
                idFoto), usuarioID)
        ){
            CarregarImagem(idFoto);
        }else{
            Toast.makeText(this, "Não foi possível salvar a foto de perfil", Toast.LENGTH_SHORT).show();
        }
    }

    private void CarregarImagem(String url_img){
        if (!url_img.equals("teste") && !url_img.equals("")){
            Picasso.get().load(url_img).into(img_foto);
        }
    }

    private void IniciarComponentes(){
        txt_nomeUsuario = findViewById(R.id.txt_nome_usuario_TelaPerfil);
        txt_filme = findViewById(R.id.txt_filme_TelaPerfil);
        txt_serie = findViewById(R.id.txt_serie_TelaPerfil);
        txt_livro = findViewById(R.id.txt_livro_TelaPerfil);
        txt_musica = findViewById(R.id.txt_musica_TelaPerfil);
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaPerfil);
        btn_fotoUsuario = findViewById(R.id.btn_foto_usuario_TelaPerfil);
        img_foto = findViewById(R.id.img_foto_TelaPerfil);
        btn_menu = findViewById(R.id.btn_menu_TelaPerfil);
        navigationView = findViewById(R.id.navigation_view_TelaPerfil);
        drawerLayout = findViewById(R.id.drawer_layout_TelaPerfil);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaPerfil);
        btn_chat = findViewById(R.id.btn_chat_TelaPerfil);
        btn_grupos = findViewById(R.id.btn_grupos_TelaPerfil);
        btn_buscar = findViewById(R.id.btn_buscar_TelaPerfil);
        btn_inicio = findViewById(R.id.btn_inicio_TelaPerfil);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(Perfil.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(Perfil.this, TelaFilmes.class);
                startActivity(intent);
                break;
            case R.id.field_series:
                intent = new Intent(Perfil.this, TelaSeries.class);
                startActivity(intent);
                break;
            case R.id.field_livros:
                intent = new Intent(Perfil.this, TelaLivros.class);
                startActivity(intent);
                break;
            case R.id.field_musicas:
                intent = new Intent(Perfil.this, TelaMusicas.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}
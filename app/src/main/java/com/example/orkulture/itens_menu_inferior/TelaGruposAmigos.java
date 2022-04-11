package com.example.orkulture.itens_menu_inferior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orkulture.Perfil;
import com.example.orkulture.R;
import com.example.orkulture.TelaLogin;
import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.itens_menu_lateral.TelaFilmes;
import com.example.orkulture.itens_menu_lateral.TelaLivros;
import com.example.orkulture.itens_menu_lateral.TelaMusicas;
import com.example.orkulture.itens_menu_lateral.TelaSeries;
import com.example.orkulture.itens_tela_chat.AdaptadorListas;
import com.example.orkulture.modelos_banco_dados.Grupo;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TelaGruposAmigos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    List<String> fotos_usuarios = new ArrayList<>();
    List<String> nomes_usuarios = new ArrayList<>();
    List<String> fotos_grupos = new ArrayList<>();
    List<String> nomes_grupos = new ArrayList<>();
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_grupos, btn_buscar, btn_inicio;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView btn_novo_grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_grupos_amigos);

        getSupportActionBar().hide();
        IniciarComponentes();
        buscarAmigos();
        buscarGrupos();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_novo_grupo.setOnClickListener(view -> {
            Intent intent = new Intent(TelaGruposAmigos.this, CriarGrupo.class);
            startActivity(intent);
            finish();
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaGruposAmigos.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaGruposAmigos.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaGruposAmigos.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaGruposAmigos.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaGruposAmigos.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaGruposAmigos.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void IniciarComponentes() {
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaGruposAmigos);
        btn_menu = findViewById(R.id.btn_menu_TelaGruposAmigos);
        navigationView = findViewById(R.id.navigation_view_TelaGruposAmigos);
        drawerLayout = findViewById(R.id.drawer_layout_TelaGruposAmigos);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaGruposAmigos);
        btn_chat = findViewById(R.id.btn_chat_TelaGruposAmigos);
        btn_grupos = findViewById(R.id.btn_grupos_TelaGruposAmigos);
        btn_buscar = findViewById(R.id.btn_buscar_TelaGruposAmigos);
        btn_inicio = findViewById(R.id.btn_inicio_TelaGruposAmigos);
        btn_novo_grupo = findViewById(R.id.btn_novo_grupo_TelaGruposAmigos);
    }

    private void buscarAmigos() {

        BancoSQLite db = new BancoSQLite(this);

            //Usuario usuario = db.selecionarUsuarioPorID("1");
        try{
            for (int i = 1; i <= 10 ; i++) {
                Usuario usuario = db.selecionarUsuarioPorID(String.valueOf(i));
                    if(!usuario.getLogado().equals("logado")){
                        nomes_usuarios.add(usuario.getNome());
                        fotos_usuarios.add(usuario.getFoto());
                    }
                }
        }catch (Exception e) {
            e.printStackTrace();
        }
        gerarListaAmigos();
    }

    private void buscarGrupos() {

        BancoSQLite db = new BancoSQLite(this);
        try{
            for (int i = 1; i <= 10; i++) {
                Grupo grupo = db.selecionarGrupoPorID(String.valueOf(i));
                nomes_grupos.add(grupo.getNome());
                fotos_grupos.add(grupo.getFoto());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        gerarListaGrupos();
    }

    private void gerarListaAmigos(){
        rv = findViewById(R.id.rcv_amigos_TelaGruposAmigos);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new AdaptadorListas(this, fotos_usuarios, nomes_usuarios, "Amigo");
        rv.setAdapter(adapter);
    }

    private void gerarListaGrupos(){
        rv = findViewById(R.id.rcv_grupos_TelaGruposAmigos);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new AdaptadorListas(this, fotos_grupos, nomes_grupos, "Grupo");
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaGruposAmigos.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaGruposAmigos.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaGruposAmigos.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaGruposAmigos.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaGruposAmigos.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
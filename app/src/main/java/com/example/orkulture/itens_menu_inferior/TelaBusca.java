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
import android.widget.EditText;
import android.widget.ImageView;
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

public class TelaBusca extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_buscar, btn_grupos, btn_inicio, btn_buscarPessoaGrupo;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private EditText edt_busca;
    List<String> foto = new ArrayList<>();
    List<String> nome = new ArrayList<>();
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_busca);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_buscarPessoaGrupo.setOnClickListener(view -> {
            foto.clear();
            nome.clear();
            String busca = edt_busca.getText().toString();
            BuscarUsuario(busca);
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaBusca.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaBusca.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaBusca.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaBusca.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaBusca.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaBusca.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void BuscarGrupo(String busca) {
        BancoSQLite db = new BancoSQLite(this);

        try{
            Grupo grupo = db.selecionarGrupo(busca);

            nome.add(grupo.getNome());
            foto.add(grupo.getFoto());
            gerarLista("Grupo");
        }catch (Exception e){
            Toast.makeText(this, "Não foi encontrado nenhum grupo/usuario com esse nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void BuscarUsuario(String busca) {
        BancoSQLite db = new BancoSQLite(this);

        try{
            Usuario usuario = db.selecionarUsuarioPorNome(busca);

            if(usuario.getLogado().equals("deslogado")){
                nome.add(usuario.getNome());
                foto.add(usuario.getFoto());
                gerarLista("Amigo");
            }

        }catch (Exception e){
            BuscarGrupo(busca);
        }
    }

    private void gerarLista(String tela) {
        rv = findViewById(R.id.rcv_resultado_TelaBusca);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new AdaptadorListas(this, foto, nome, tela);
        rv.setAdapter(adapter);
    }

    private void IniciarComponentes() {
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaBusca);
        btn_menu = findViewById(R.id.btn_menu_TelaBusca);
        navigationView = findViewById(R.id.navigation_view_TelaBusca);
        drawerLayout = findViewById(R.id.drawer_layout_TelaBusca);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaBusca);
        btn_chat = findViewById(R.id.btn_chat_TelaBusca);
        btn_buscar = findViewById(R.id.btn_buscar_TelaBusca);
        btn_grupos = findViewById(R.id.btn_grupos_TelaBusca);
        btn_inicio = findViewById(R.id.btn_inicio_TelaBusca);
        btn_buscarPessoaGrupo = findViewById(R.id.btn_buscar_grupos_pessoas_TelaBusca);
        edt_busca = findViewById(R.id.edt_busca_TelaBusca);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaBusca.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaBusca.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaBusca.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaBusca.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaBusca.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
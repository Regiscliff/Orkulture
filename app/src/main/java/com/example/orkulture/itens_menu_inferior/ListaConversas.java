package com.example.orkulture.itens_menu_inferior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orkulture.Perfil;
import com.example.orkulture.R;
import com.example.orkulture.TelaLogin;
import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.itens_menu_lateral.TelaFilmes;
import com.example.orkulture.itens_menu_lateral.TelaLivros;
import com.example.orkulture.itens_menu_lateral.TelaMusicas;
import com.example.orkulture.itens_menu_lateral.TelaSeries;
import com.example.orkulture.itens_tela_chat.ListaContatos;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;

public class ListaConversas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_grupos, btn_buscar, btn_inicio;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView btn_nova_conversa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_conversas);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_nova_conversa.setOnClickListener(view -> {
            Intent intent = new Intent(ListaConversas.this, ListaContatos.class);
            startActivity(intent);
            finish();
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(ListaConversas.this, TelaLogin.class);
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
            Intent intent = new Intent(ListaConversas.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(ListaConversas.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(ListaConversas.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(ListaConversas.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(ListaConversas.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar_ListaConversas);
        btn_menu = findViewById(R.id.btn_menu_ListaConversas);
        navigationView = findViewById(R.id.navigation_view_ListaConversas);
        drawerLayout = findViewById(R.id.drawer_layout_ListaConversas);
        btn_nova_conversa = findViewById(R.id.txt_nova_conversa_ListaConversas);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_ListaConveras);
        btn_chat = findViewById(R.id.btn_chat_ListaConversas);
        btn_grupos = findViewById(R.id.btn_grupos_ListaConversas);
        btn_buscar = findViewById(R.id.btn_buscar_ListaConversas);
        btn_inicio = findViewById(R.id.btn_inicio_ListaConversas);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(ListaConversas.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(ListaConversas.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(ListaConversas.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(ListaConversas.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(ListaConversas.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
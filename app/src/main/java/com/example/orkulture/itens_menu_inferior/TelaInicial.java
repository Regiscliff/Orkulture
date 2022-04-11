package com.example.orkulture.itens_menu_inferior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.orkulture.Perfil;
import com.example.orkulture.R;
import com.example.orkulture.TelaLogin;
import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.itens_menu_lateral.TelaFilmes;
import com.example.orkulture.itens_menu_lateral.TelaLivros;
import com.example.orkulture.itens_menu_lateral.TelaMusicas;
import com.example.orkulture.itens_menu_lateral.TelaSeries;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;

public class TelaInicial extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_grupos, btn_buscar, btn_inicio;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaInicial.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaInicial.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaInicial.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaInicial.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaInicial.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaInicial.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void IniciarComponentes() {
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaInicial);
        btn_menu = findViewById(R.id.btn_menu_TelaInicial);
        navigationView = findViewById(R.id.navigation_view_TelaInicial);
        drawerLayout = findViewById(R.id.drawer_layout_TelaInicial);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaInicial);
        btn_chat = findViewById(R.id.btn_chat_TelaInicial);
        btn_grupos = findViewById(R.id.btn_grupos_TelaInicial);
        btn_buscar = findViewById(R.id.btn_buscar_TelaInicial);
        btn_inicio = findViewById(R.id.btn_inicio_TelaInicial);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaInicial.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaInicial.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaInicial.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaInicial.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaInicial.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
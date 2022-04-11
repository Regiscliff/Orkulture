package com.example.orkulture.itens_menu_inferior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TelaConfiguracoes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_grupos, btn_buscar, btn_inicio;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button btn_salvar;
    private EditText edt_nome, edt_email, edt_senha, edt_telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_configuracoes);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        BancoSQLite db = new BancoSQLite(this);
        Usuario usuario = db.selecionarUsuarioLogado("logado");

        edt_nome.setText(usuario.getNome());
        edt_email.setText(usuario.getEmail());
        edt_senha.setText(usuario.getSenha());
        edt_telefone.setText(usuario.getTelefone());

        super.onStart();
    }

    private void AcoesClique() {
        btn_salvar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            if(db.atualizarDadosUsuario(new Usuario(
                  edt_email.getText().toString(), edt_senha.getText().toString(), edt_nome.getText().toString(),
                  edt_telefone.getText().toString()
            ), "1")
            ){
                Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TelaConfiguracoes.this, Perfil.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Erro ao atualizar dados", Toast.LENGTH_SHORT).show();
            }
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaConfiguracoes.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaConfiguracoes.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaConfiguracoes.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaConfiguracoes.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaConfiguracoes.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaConfiguracoes.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaConfiguracoes);
        btn_menu = findViewById(R.id.btn_menu_TelaConfiguracoes);
        navigationView = findViewById(R.id.navigation_view_TelaConfiguracoes);
        drawerLayout = findViewById(R.id.drawer_layout_TelaConfiguracoes);
        btn_salvar = findViewById(R.id.btn_salvar_TelaConfiguracoes);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaConfiguracoes);
        btn_chat = findViewById(R.id.btn_chat_TelaConfiguracoes);
        btn_grupos = findViewById(R.id.btn_grupos_TelaConfiguracoes);
        btn_buscar = findViewById(R.id.btn_buscar_TelaConfiguracoes);
        btn_inicio = findViewById(R.id.btn_inicio_TelaConfiguracoes);
        edt_email = findViewById(R.id.edt_email_TelaConfiguracoes);
        edt_senha = findViewById(R.id.edt_senha_TelaConfiguracoes);
        edt_nome = findViewById(R.id.edt_nome_TelaConfiguracoes);
        edt_telefone = findViewById(R.id.edt_telefone_TelaConfiguracoes);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaConfiguracoes.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaConfiguracoes.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaConfiguracoes.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaConfiguracoes.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaConfiguracoes.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
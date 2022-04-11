package com.example.orkulture.itens_menu_lateral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orkulture.Perfil;
import com.example.orkulture.R;
import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.itens_menu_inferior.ListaConversas;
import com.example.orkulture.TelaLogin;
import com.example.orkulture.itens_menu_inferior.TelaBusca;
import com.example.orkulture.itens_menu_inferior.TelaConfiguracoes;
import com.example.orkulture.itens_menu_inferior.TelaGruposAmigos;
import com.example.orkulture.itens_menu_inferior.TelaInicial;
import com.example.orkulture.modelos_banco_dados.Musica;
import com.example.orkulture.modelos_banco_dados.Serie;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TelaMusicas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_buscar, btn_grupos, btn_inicio, btn_buscarMusica;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button btn_salvar, btn_remover;
    private EditText edt_musica;
    private TextView txt_detalhes_musica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_musicas);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_buscarMusica.setOnClickListener(view -> {
            btn_salvar.setEnabled(false);
            SalvarMusica(edt_musica.getText().toString());
        });

        btn_salvar.setOnClickListener(view -> {
            Toast.makeText(this, "Música salva com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TelaMusicas.this, Perfil.class);
            startActivity(intent);
            finish();
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaMusicas.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaMusicas.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaMusicas.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaMusicas.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaMusicas.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaMusicas.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void SalvarMusica(String nome) {
        BancoSQLite db = new BancoSQLite(this);
        if(nome.equals("Blue")){
            if(db.inserirMusica(new Musica(
                    "BLUE", "Troye Sivan", "Pop", "Blue Neighbourhood"))
            ){
                String nomeMusica = "BLUE";
                ExibirMusica(nomeMusica);
            }else{
                Toast.makeText(this, "Não foi possível buscar a música", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("8 letters")){
            if(db.inserirMusica(new Musica(
                    "8 Letters", "Why Don't We", "Pop", ""))
            ){
                String nomeMusica = "8 Letters";
                ExibirMusica(nomeMusica);
            }else{
                Toast.makeText(this, "Não foi possível buscar a música", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("Arcade")){
            if(db.inserirMusica(new Musica(
                    "Arcade", "Duncan Laurence", "Dance/Eletrónica, Pop, UK R&B", "Small Town Boy"))
            ){
                String nomeMusica = "Arcade";
                ExibirMusica(nomeMusica);
            }else{
                Toast.makeText(this, "Não foi possível buscar a música", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ExibirMusica(String nomeMusica) {
        BancoSQLite db = new BancoSQLite(this);
        Musica musica = db.selecionarMusica(nomeMusica);

        txt_detalhes_musica.setText("Nome: " + musica.getNome() + "\n\nCantor: " + musica.getCantor() +
                "\n\nGênero: " + musica.getGenero() + "\n\nÁlbum: " + musica.getAlbum());
        btn_salvar.setEnabled(true);
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaMusicas);
        btn_menu = findViewById(R.id.btn_menu_TelaMusicas);
        navigationView = findViewById(R.id.navigation_view_TelaMusicas);
        drawerLayout = findViewById(R.id.drawer_layout_TelaMusicas);
        btn_salvar = findViewById(R.id.btn_salvar_TelaMusicas);
        btn_remover = findViewById(R.id.btn_remover_TelaMusicas);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaMusicas);
        btn_chat = findViewById(R.id.btn_chat_TelaMusicas);
        btn_buscar = findViewById(R.id.btn_buscar_TelaMusicas);
        btn_inicio = findViewById(R.id.btn_inicio_TelaMusicas);
        btn_grupos = findViewById(R.id.btn_grupos_TelaMusicas);
        btn_buscarMusica = findViewById(R.id.btn_buscar_musica_TelaMusicas);
        edt_musica = findViewById(R.id.edt_musica_TelaMusicas);
        txt_detalhes_musica = findViewById(R.id.txt_detalhes_musica_TelaMusicas);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaMusicas.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaMusicas.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaMusicas.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaMusicas.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaMusicas.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
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
import com.example.orkulture.modelos_banco_dados.Filme;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TelaFilmes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_buscar, btn_grupos, btn_inicio, btn_buscarFilme;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button btn_salvar, btn_remover;
    private EditText edt_filme;
    private TextView txt_detalhes_filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_filmes);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_buscarFilme.setOnClickListener(view -> {
            btn_salvar.setEnabled(false);
            SalvarFilme(edt_filme.getText().toString());
        });

        btn_salvar.setOnClickListener(view -> {
            Toast.makeText(this, "Filme salvo com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TelaFilmes.this, Perfil.class);
            startActivity(intent);
            finish();
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaFilmes.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaFilmes.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaFilmes.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaFilmes.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaFilmes.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaFilmes.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void SalvarFilme(String nome) {
        BancoSQLite db = new BancoSQLite(this);
        if(nome.equals("V de vinganca")){
            if(db.inserirFilme(new Filme(
                    "V de Vingança", "Ação/Thriller", "2005", "Após uma guerra mundial, a Inglaterra " +
                    "é ocupada por um governo fascista e vive sob um regime totalitário. Na luta pela liberdade, " +
                    "um vigilante, conhecido apenas como V, utiliza-se de táticas terroristas para enfrentar os " +
                    "opressores da sociedade. V salva uma jovem chamada Evey da polícia secreta e encontra nela " +
                    "uma nova aliada em busca de liberdade e justiça para o seu país."))
            ){
                String nomeFilme = "V de Vingança";
                ExibirFilme(nomeFilme);
            }else{
                Toast.makeText(this, "Não foi possível buscar o filme", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("Kingsman")){
            if(db.inserirFilme(new Filme(
                    "Kingsman: Serviço Secreto", "Ação/Comédia", "2014", "Uma organização de " +
                    "espionagem recruta um jovem de rua rebelde, mas com um futuro promissor, para um programa de " +
                    "treinamento ultracompetitivo. O elegante agente Harry Hart vê muito potencial no jovem Eggsy, " +
                    "apesar do temperamento. Após passar pela intensa preparação do serviço secreto, Eggsy tem de " +
                    "enfrentar uma ameaça global que emerge de um gênio da tecnologia. O plano do vilanesco Richmond " +
                    "Valentine envolve erradicar o problema do aquecimento global por meio de uma matança em larga escala."))
            ){
                String nomeFilme = "Kingsman: Serviço Secreto";
                ExibirFilme(nomeFilme);
            }else{
                Toast.makeText(this, "Não foi possível buscar o filme", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("Viva")){
            if(db.inserirFilme(new Filme(
                    "Viva – A Vida É uma Festa", "Infantil/Aventura", "2017", "Apesar da proibição " +
                    "da música por gerações de sua família, o jovem Miguel sonha em se tornar um músico talentoso como seu " +
                    "ídolo Ernesto de la Cruz. Desesperado para provar seu talento, Miguel se encontra na deslumbrante e " +
                    "colorida Terra dos Mortos. Depois de conhecer um charmoso malandro chamado Héctor, os dois novos amigos " +
                    "embarcam em uma jornada extraordinária para desvendar a verdadeira história por trás da história da " +
                    "família de Miguel."))
            ){
                String nomeFilme = "Viva – A Vida É uma Festa";
                ExibirFilme(nomeFilme);
            }else{
                Toast.makeText(this, "Não foi possível buscar o filme", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ExibirFilme(String nomeFilme) {
        BancoSQLite db = new BancoSQLite(this);
        Filme filme = db.selecionarFilme(nomeFilme);

        txt_detalhes_filme.setText("Nome: " + filme.getNome() + "\n\nGênero: " + filme.getGenero() + "\n\nAno: " +
                filme.getAno() + "\n\nSinopse: " + filme.getSinopse());
        btn_salvar.setEnabled(true);
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaFilmes);
        btn_menu = findViewById(R.id.btn_menu_TelaFilmes);
        navigationView = findViewById(R.id.navigation_view_TelaFilmes);
        drawerLayout = findViewById(R.id.drawer_layout_TelaFilmes);
        btn_salvar = findViewById(R.id.btn_salvar_TelaFilmes);
        btn_remover = findViewById(R.id.btn_remover_TelaFilmes);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaFilmes);
        btn_chat = findViewById(R.id.btn_chat_TelaFilmes);
        btn_buscar = findViewById(R.id.btn_buscar_TelaFilmes);
        btn_inicio = findViewById(R.id.btn_inicio_TelaFilmes);
        btn_grupos = findViewById(R.id.btn_grupos_TelaFilmes);
        btn_buscarFilme = findViewById(R.id.btn_buscar_filme_TelaFilmes);
        edt_filme = findViewById(R.id.edt_filme_TelaFilmes);
        txt_detalhes_filme = findViewById(R.id.txt_detalhes_filme_TelaFilmes);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaFilmes.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaFilmes.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaFilmes.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaFilmes.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaFilmes.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
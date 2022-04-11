package com.example.orkulture.itens_menu_lateral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
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
import com.example.orkulture.modelos_banco_dados.Serie;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TelaSeries extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_buscar, btn_grupos, btn_inicio, btn_buscarSerie;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button btn_salvar, btn_remover;
    private EditText edt_serie;
    private TextView txt_detalhes_serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_series);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesCliques();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesCliques() {
        btn_buscarSerie.setOnClickListener(view -> {
            btn_salvar.setEnabled(false);
            SalvarSerie(edt_serie.getText().toString());
        });

        btn_salvar.setOnClickListener(view -> {
            Toast.makeText(this, "Série salva com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TelaSeries.this, Perfil.class);
            startActivity(intent);
            finish();
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaSeries.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaSeries.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaSeries.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaSeries.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaSeries.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaSeries.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void SalvarSerie(String nome) {
        BancoSQLite db = new BancoSQLite(this);
        if(nome.equals("Stranger Things")){
            if(db.inserirSerie(new Serie(
                    "Stranger Things", "Terror", "2016", "Will, um garoto de 12 anos, " +
                    "desaparece em Montauk, Long Island. Enquanto a polícia, família e amigos procuram respostas, " +
                    "eles mergulham em um extraordinário mistério, envolvendo um experimento secreto do governo, " +
                    "forças sobrenaturais e uma garotinha."))
            ){
                String nomeSerie = "Stranger Things";
                ExibirSerie(nomeSerie);
            }else{
                Toast.makeText(this, "Não foi possível buscar a série", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("Euphoria")){
            if(db.inserirSerie(new Serie(
                    "Euphoria", "Drama", "2019", "Um grupo de estudantes do ensino médio " +
                    "lida com diferentes situações típicas da idade como drogas, sexo, busca pela identidade, " +
                    "traumas, comportamento nas redes sociais e amizade."))
            ){
                String nomeSerie = "Euphoria";
                ExibirSerie(nomeSerie);
            }else{
                Toast.makeText(this, "Não foi possível buscar a série", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("HIMYM")){
            if(db.inserirSerie(new Serie(
                    "How I Met Your Mother", "Sitcom", "2005", "Ted se apaixonou. Tudo começou " +
                    "quando seu melhor amigo, Marshall, soltou a bomba de que planejava pedir em casamento a namorada de " +
                    "longa data, Lily, uma professora de jardim de infância. Ted percebeu que era melhor se mexer se " +
                    "esperava encontrar o verdadeiro amor. Para ajudá-lo na missão está Barney, um amigo com opiniões sem " +
                    "fim -- e às vezes absurdas --, uma queda por ternos e uma fórmula infalível para conhecer garotas. " +
                    "Quando Ted conhece Robin, tem certeza que é amor à primeira vista, mas o relacionamento esfria e se " +
                    "transforma em uma amizade."))
            ){
                String nomeSerie = "How I Met Your Mother";
                ExibirSerie(nomeSerie);
            }else{
                Toast.makeText(this, "Não foi possível buscar a série", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ExibirSerie(String nomeSerie) {
        BancoSQLite db = new BancoSQLite(this);
        Serie serie = db.selecionarSerie(nomeSerie);

        txt_detalhes_serie.setText("Nome: " + serie.getNome() + "\n\nGênero: " + serie.getGenero() + "\n\nAno: " +
                serie.getAno() + "\n\nSinopse: " + serie.getSinopse());
        btn_salvar.setEnabled(true);
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaSeries);
        btn_menu = findViewById(R.id.btn_menu_TelaSeries);
        navigationView = findViewById(R.id.navigation_view_TelaSeries);
        drawerLayout = findViewById(R.id.drawer_layout_TelaSeries);
        btn_salvar = findViewById(R.id.btn_salvar_TelaSeries);
        btn_remover = findViewById(R.id.btn_remover_TelaSeries);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaSeries);
        btn_chat = findViewById(R.id.btn_chat_TelaSeries);
        btn_buscar = findViewById(R.id.btn_buscar_TelaSeries);
        btn_inicio = findViewById(R.id.btn_inicio_TelaSeries);
        btn_grupos = findViewById(R.id.btn_grupos_TelaSeries);
        btn_buscarSerie = findViewById(R.id.btn_buscar_series_TelaSeries);
        edt_serie = findViewById(R.id.edt_serie_TelaSeries);
        txt_detalhes_serie = findViewById(R.id.txt_detalhes_serie_TelaSeries);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaSeries.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaSeries.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaSeries.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaSeries.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaSeries.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
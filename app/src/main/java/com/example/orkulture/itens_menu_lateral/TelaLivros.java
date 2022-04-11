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
import com.example.orkulture.modelos_banco_dados.Livro;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TelaLivros extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView btn_menu, btn_deslogar, btn_configuracoes, btn_chat, btn_buscar, btn_grupos, btn_inicio, btn_buscarLivro;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button btn_salvar, btn_remover;
    private EditText edt_livro;
    private TextView txt_detalhes_livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_livros);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void AcoesClique() {
        btn_buscarLivro.setOnClickListener(view -> {
            btn_salvar.setEnabled(false);
            SalvarLivro(edt_livro.getText().toString());
        });

        btn_salvar.setOnClickListener(view -> {
            Toast.makeText(this, "Livro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TelaLivros.this, Perfil.class);
            startActivity(intent);
            finish();
        });

        btn_deslogar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            Usuario usuario = db.selecionarUsuarioLogado("logado");

            if(db.atualizarUsuarioLogado(new Usuario(
                    "", "deslogado"), String.valueOf(usuario.getId()))
            ){
                Intent intent = new Intent(TelaLivros.this, TelaLogin.class);
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
            Intent intent = new Intent(TelaLivros.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });

        btn_buscar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaLivros.this, TelaBusca.class);
            startActivity(intent);
            finish();
        });

        btn_inicio.setOnClickListener(view -> {
            Intent intent = new Intent(TelaLivros.this, TelaInicial.class);
            startActivity(intent);
            finish();
        });

        btn_grupos.setOnClickListener(view -> {
            Intent intent = new Intent(TelaLivros.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_configuracoes.setOnClickListener(view -> {
            Intent intent = new Intent(TelaLivros.this, TelaConfiguracoes.class);
            startActivity(intent);
            finish();
        });
    }

    private void SalvarLivro(String nome) {
        BancoSQLite db = new BancoSQLite(this);
        if(nome.equals("Divergente")){
            if(db.inserirLivro(new Livro(
                    "Divergent", "Aventura/Distopia/Fição Científica/Ação", "Veronica Roth",
                    "2011"))
            ){
                String nomeLivro = "Divergent";
                ExibirLivro(nomeLivro);
            }else{
                Toast.makeText(this, "Não foi possível buscar o livro", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("O morro dos ventos uivantes")){
            if(db.inserirLivro(new Livro(
                    "Wuthering Heights", "Romance/Romance de amor/Ficção gótica/Tragédia",
                    "Emily Brontë", "1847"))
            ){
                String nomeLivro = "Wuthering Heights";
                ExibirLivro(nomeLivro);
            }else{
                Toast.makeText(this, "Não foi possível buscar o livro", Toast.LENGTH_SHORT).show();
            }
        }else if(nome.equals("O assassinato")){
            if(db.inserirLivro(new Livro(
                    "O Assassinato de Roger Ackroyd", "Mistério/História de detetives/Ficção policial",
                    "Agatha Christie", "1926"))
            ){
                String nomeLivro = "O Assassinato de Roger Ackroyd";
                ExibirLivro(nomeLivro);
            }else{
                Toast.makeText(this, "Não foi possível buscar o livro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ExibirLivro(String nomeLivro) {
        BancoSQLite db = new BancoSQLite(this);
        Livro livro = db.selecionarLivro(nomeLivro);

        txt_detalhes_livro.setText("Nome: " + livro.getNome() + "\n\nGênero: " + livro.getGenero() +
                "\n\nAutor: " + livro.getAutor() + "\n\nAno: " + livro.getAno());
        btn_salvar.setEnabled(true);
    }

    private void IniciarComponentes(){
        btn_deslogar = findViewById(R.id.btn_deslogar_TelaLivros);
        btn_menu = findViewById(R.id.btn_menu_TelaLivros);
        navigationView = findViewById(R.id.navigation_view_TelaLivros);
        drawerLayout = findViewById(R.id.drawer_layout_TelaLivros);
        btn_salvar = findViewById(R.id.btn_salvar_TelaLivros);
        btn_remover = findViewById(R.id.btn_remover_TelaLivros);
        btn_configuracoes = findViewById(R.id.btn_configuracoes_TelaLivros);
        btn_chat = findViewById(R.id.btn_chat_TelaLivros);
        btn_grupos = findViewById(R.id.btn_grupos_TelaLivros);
        btn_buscar = findViewById(R.id.btn_buscar_TelaLivros);
        btn_inicio = findViewById(R.id.btn_inicio_TelaLivros);
        btn_buscarLivro = findViewById(R.id.btn_buscar_livro_TelaLivros);
        edt_livro = findViewById(R.id.edt_livro_TelaLivros);
        txt_detalhes_livro = findViewById(R.id.txt_detalhes_livro_TelaLivros);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.field_perfil:
                Intent intent = new Intent(TelaLivros.this, Perfil.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_filmes:
                intent = new Intent(TelaLivros.this, TelaFilmes.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_series:
                intent = new Intent(TelaLivros.this, TelaSeries.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_livros:
                intent = new Intent(TelaLivros.this, TelaLivros.class);
                startActivity(intent);
                finish();
                break;
            case R.id.field_musicas:
                intent = new Intent(TelaLivros.this, TelaMusicas.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }
}
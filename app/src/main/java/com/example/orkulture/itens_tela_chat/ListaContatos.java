package com.example.orkulture.itens_tela_chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.itens_menu_inferior.ListaConversas;
import com.example.orkulture.R;
import com.example.orkulture.modelos_banco_dados.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ListaContatos extends AppCompatActivity {

    List<String> fotos = new ArrayList<>();
    List<String> nomes = new ArrayList<>();
    ImageView btn_voltar;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        getSupportActionBar().hide();
        buscarContatos();
        btn_voltar = findViewById(R.id.btn_voltar_ListaContatos);

        btn_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(ListaContatos.this, ListaConversas.class);
            startActivity(intent);
            finish();
        });
    }

    private void buscarContatos() {

        BancoSQLite db = new BancoSQLite(this);
        try{
            for (int i = 1; i <= 10 ; i++) {
                Usuario usuario = db.selecionarUsuarioPorID(String.valueOf(i));
                if(!usuario.getLogado().equals("logado")){
                    nomes.add(usuario.getNome());
                    fotos.add(usuario.getFoto());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        gerarListaContatos();
    }

    private void gerarListaContatos(){
        rv = findViewById(R.id.rcv_contatos);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new AdaptadorListas(this, fotos, nomes, "ListaContatos");
        rv.setAdapter(adapter);
    }
}
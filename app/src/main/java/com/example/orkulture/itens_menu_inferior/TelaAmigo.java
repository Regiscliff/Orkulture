package com.example.orkulture.itens_menu_inferior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.orkulture.R;
import com.example.orkulture.itens_tela_chat.TelaChat;

public class TelaAmigo extends AppCompatActivity {

    ImageView bnt_voltar, btn_adicionar_amigo, btn_remover_amigo;
    String adicionado = "n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_amigo);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

    }

    private void AcoesClique() {
        bnt_voltar.setOnClickListener(view -> {
            finish();
        });

        btn_adicionar_amigo.setOnClickListener(view -> {
            if(adicionado.equals("n")){
                Toast.makeText(this, "Perfil adicionado à lista de amigos!", Toast.LENGTH_SHORT).show();
                btn_remover_amigo.setVisibility(View.VISIBLE);
                btn_adicionar_amigo.setImageDrawable(getDrawable(R.drawable.ic_chat));
                adicionado = "s";
            }else {
                Intent intent = new Intent(TelaAmigo.this,TelaChat.class);
                startActivity(intent);
            }
        });

        btn_remover_amigo.setOnClickListener(view -> {
            Toast.makeText(this, "Perfil removido da lista de amigos", Toast.LENGTH_SHORT).show();
            btn_adicionar_amigo.setImageDrawable(getDrawable(R.drawable.ic_adicionar_amigo));
            btn_remover_amigo.setVisibility(View.INVISIBLE);
            adicionado = "n";
        });
    }

    private void IniciarComponentes() {
        bnt_voltar = findViewById(R.id.btn_voltar_TelaAmigo);
        btn_adicionar_amigo = findViewById(R.id.btn_adicionar_amigo_TelaAmigo);
        btn_remover_amigo = findViewById(R.id.btn_remover_amigo_TelaAmigo);
    }
}
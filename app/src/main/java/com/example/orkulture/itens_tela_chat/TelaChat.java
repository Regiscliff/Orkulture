package com.example.orkulture.itens_tela_chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orkulture.R;
import com.example.orkulture.itens_menu_inferior.ListaConversas;

public class TelaChat extends AppCompatActivity {

    ImageView btn_voltar, btn_enviar;
    EditText edt_mensagem;
    TextView txt_mensagem_enviada, txt_mensagem_recebida;
    ImageView img_mensagem_enviada, img_mensagem_recebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_chat);

        getSupportActionBar().hide();
        IniciarComponentes();

        btn_voltar.setOnClickListener(view -> {
            finish();
        });

        btn_enviar.setOnClickListener(view -> {
            String mensagem = edt_mensagem.getText().toString();
            txt_mensagem_enviada.setText(mensagem);
            txt_mensagem_recebida.setText(mensagem);
            txt_mensagem_enviada.setVisibility(View.VISIBLE);
            txt_mensagem_recebida.setVisibility(View.VISIBLE);
            img_mensagem_enviada.setVisibility(View.VISIBLE);
            img_mensagem_recebida.setVisibility(View.VISIBLE);
            edt_mensagem.setText("");
        });
    }

    private void IniciarComponentes() {
        btn_voltar = findViewById(R.id.btn_voltar_TelaChat);
        btn_enviar = findViewById(R.id.btn_enviar_mensagem_TelaChat);
        edt_mensagem = findViewById(R.id.edt_mensagem_TelaChat);
        txt_mensagem_enviada = findViewById(R.id.txt_mensagem_enviada_TelaChat);
        txt_mensagem_recebida = findViewById(R.id.txt_mensagem_recebida_TelaChat);
        img_mensagem_enviada = findViewById(R.id.img_mensagem_enviada_TelaChat);
        img_mensagem_recebida = findViewById(R.id.img_mensagem_recebida_TelaChat);
    }

}
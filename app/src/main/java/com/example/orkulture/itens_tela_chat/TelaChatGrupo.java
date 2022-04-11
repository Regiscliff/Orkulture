package com.example.orkulture.itens_tela_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orkulture.R;
import com.example.orkulture.itens_menu_inferior.TelaGruposAmigos;

public class TelaChatGrupo extends AppCompatActivity {

    ImageView btn_voltar, btn_enviar;
    EditText edt_mensagem;
    TextView txt_mensagem_enviada;
    ImageView img_mensagem_enviada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_chat_grupo);

        getSupportActionBar().hide();
        IniciarComponentes();

        btn_enviar.setOnClickListener(view -> {
            txt_mensagem_enviada.setText(edt_mensagem.getText().toString());
            txt_mensagem_enviada.setVisibility(View.VISIBLE);
            img_mensagem_enviada.setVisibility(View.VISIBLE);
            edt_mensagem.setText("");
        });

        btn_voltar.setOnClickListener(view -> {
            finish();
        });
    }

    private void IniciarComponentes() {
        btn_voltar = findViewById(R.id.btn_voltar_TelaChatGrupo);
        btn_enviar = findViewById(R.id.btn_enviar_mensagem_TelaChatGrupo);
        edt_mensagem = findViewById(R.id.edt_mensagem_TelaChatGrupo);
        txt_mensagem_enviada = findViewById(R.id.txt_mensagem_enviada_TelaChatGrupo);
        img_mensagem_enviada = findViewById(R.id.img_mensagem_enviada_TelaChatGrupo);
    }
}
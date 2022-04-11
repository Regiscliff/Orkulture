package com.example.orkulture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RecuperarSenha extends AppCompatActivity {

    EditText edt_campo1, edt_campo2;
    Button btn_verificar;
    TextView txt_titulo1, txt_titulo2;
    ImageView btn_voltar;
    private int aux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();

    }

    private void AcoesClique() {
        btn_verificar.setOnClickListener(view -> {
            if(aux == 0){
                edt_campo1.setEnabled(false);
                edt_campo2.setVisibility(View.VISIBLE);
                txt_titulo2.setVisibility(View.VISIBLE);
                btn_verificar.setText("Verificar");
                aux = 1;
            }else if(aux == 1){
                txt_titulo1.setText("Informe a nova senha:");
                edt_campo1.setEnabled(true);
                edt_campo1.setHint("Senha");
                txt_titulo2.setText("Confirme a senha:");
                edt_campo2.setHint("Confirmar senha");
                btn_verificar.setText("Salvar");
                aux = 2;
            }else{
                Toast.makeText(RecuperarSenha.this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                aux = 0;

                Intent intent = new Intent(RecuperarSenha.this, TelaLogin.class);
                startActivity(intent);
                finish();
            }
        });

        btn_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(RecuperarSenha.this, TelaLogin.class);
            startActivity(intent);
            finish();
        });
    }

    private void IniciarComponentes(){
        edt_campo1 = findViewById(R.id.edt_primeiro_campo_RecuperarSenha);
        edt_campo2 = findViewById(R.id.edt_segundo_campo_RecuperarSenha);
        btn_verificar = findViewById(R.id.btn_verificar_senha_RecuperarSenha);
        txt_titulo1 = findViewById(R.id.txt_primeiro_titulo_RecuperarSenha);
        txt_titulo2 = findViewById(R.id.txt_segundo_titulo_RecuperarSenha);
        btn_voltar = findViewById(R.id.btn_voltar_RecuperarSenha);
    }
}
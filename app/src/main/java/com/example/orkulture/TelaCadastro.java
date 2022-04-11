package com.example.orkulture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class TelaCadastro extends AppCompatActivity {

    private EditText edt_nome, edt_email, edt_senha, edt_confirmarSenha, edt_telefone, edt_dtNascimento;
    private Button btn_cadastrar;
    private CheckBox cbx_termos;
    private ImageView btn_voltar;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso!",
            "As senhas informadas não conferem", "Para realizar o cadastro, aceite os termos e condições"};

    String nome, email, senha, confirmarSenha, telefone, nascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();
    }

    private void AcoesClique() {
        btn_cadastrar.setOnClickListener(view -> {

            nome = edt_nome.getText().toString();
            email = edt_email.getText().toString();
            senha = edt_senha.getText().toString();
            confirmarSenha = edt_confirmarSenha.getText().toString();
            telefone = edt_telefone.getText().toString();
            nascimento = edt_dtNascimento.getText().toString();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() ||
                    confirmarSenha.isEmpty() || telefone.isEmpty() || nascimento.isEmpty()){
                Toast.makeText(TelaCadastro.this, mensagens[0], Toast.LENGTH_SHORT).show();
            }else if (!senha.equals(confirmarSenha)){
                Toast.makeText(TelaCadastro.this, mensagens[2], Toast.LENGTH_SHORT).show();
            }else if(!cbx_termos.isChecked()){
                Toast.makeText(TelaCadastro.this, mensagens[3], Toast.LENGTH_SHORT).show();
            } else{
                CadastrarUsuario();
            }
        });

        btn_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
            startActivity(intent);
        });
    }

    private void CadastrarUsuario(){
        BancoSQLite db = new BancoSQLite(this);

        if(db.inserirUsuario(new Usuario(
                email, senha, nome, telefone, nascimento, "teste", "deslogado"))
        ){
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            TelaLogin();
        }else{
            Toast.makeText(this, "Não foi possível realizar o cadastro", Toast.LENGTH_SHORT).show();
        }
    }

    private void TelaLogin(){
        Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        edt_nome = findViewById(R.id.edt_nome_TelaCadastro);
        edt_email = findViewById(R.id.edt_email_TelaCadastro);
        edt_senha = findViewById(R.id.edt_senha_TelaCadastro);
        edt_confirmarSenha = findViewById(R.id.edt_confirmar_senha_TelaCadastro);
        edt_telefone = findViewById(R.id.edt_telefone_TelaCadastro);
        edt_dtNascimento = findViewById(R.id.edt_data_nascimento_TelaCadastro);
        btn_cadastrar = findViewById(R.id.btn_salvar_TelaCadastro);
        btn_voltar = findViewById(R.id.btn_voltar_TelaCadastro);
        cbx_termos = findViewById(R.id.cbx_termos_TelaCadastro);
    }
}
package com.example.orkulture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.modelos_banco_dados.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class TelaLogin extends AppCompatActivity {

    private TextView btn_cadastrar, btn_esqueciSenha;
    private EditText edt_email, edt_senha;
    private Button btn_entrar;
    private ProgressBar barraProgresso;
    String[] mensagens = {"Preencha todos os campos", "E-mail e/ou senha inválidos!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        getSupportActionBar().hide();
        IniciarComponentes();
        AcoesClique();
        VerificarUsuarioLogado();
    }

    @Override
    protected void onStart() {
        super.onStart();
        VerificarUsuarioLogado();
    }

    private void VerificarUsuarioLogado() {
        BancoSQLite db = new BancoSQLite(this);

        try{
            for (int i = 1; i <= 10 ; i++) {
                Usuario usuario = db.selecionarUsuarioPorID(String.valueOf(i));
                if(usuario.getLogado().equals("logado")){
                    TelaUsuario();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AutenticarUsuario(){
        BancoSQLite db = new BancoSQLite(this);
        try{
            Usuario usuario = db.selecionarUsuario(edt_email.getText().toString());

            if(usuario.getSenha().equals(edt_senha.getText().toString())){

                if(db.atualizarUsuarioLogado(new Usuario(
                        usuario.getNome(), "logado"), String.valueOf(usuario.getId()))
                ){
                    barraProgresso.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaUsuario();
                        }
                    }, 3000);
                }

            }else{
                Toast.makeText(this, "E-mail e/ou senha inválidos!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "E-mail e/ou senha inválidos! ", Toast.LENGTH_SHORT).show();
        }
    }

    private void TelaUsuario(){
        Intent intent = new Intent(TelaLogin.this, Perfil.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        btn_cadastrar = findViewById(R.id.btn_cadastrar_TelaLogin);
        edt_email = findViewById(R.id.edt_email_TelaLogin);
        edt_senha = findViewById(R.id.edt_senha_TelaLogin);
        btn_entrar = findViewById(R.id.btn_entrar_TelaLogin);
        barraProgresso = findViewById(R.id.barra_progresso_TelaLogin);
        btn_esqueciSenha = findViewById(R.id.btn_esqueci_senha_TelaLogin);
    }

    private void AcoesClique(){
        btn_cadastrar.setOnClickListener(view -> {

            Intent intent = new Intent(TelaLogin.this,TelaCadastro.class);
            startActivity(intent);
        });

        btn_entrar.setOnClickListener(view -> {

            String email = edt_email.getText().toString();
            String senha = edt_senha.getText().toString();

            if(email.isEmpty() || senha.isEmpty()){
                Toast.makeText(TelaLogin.this, mensagens[0], Toast.LENGTH_SHORT).show();
            }else{
                AutenticarUsuario();
            }
        });

        btn_esqueciSenha.setOnClickListener(view -> {
            Intent intent = new Intent(TelaLogin.this, RecuperarSenha.class);
            startActivity(intent);
        });
    }
}
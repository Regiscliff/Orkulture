package com.example.orkulture.itens_menu_inferior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.orkulture.R;
import com.example.orkulture.database.BancoSQLite;
import com.example.orkulture.modelos_banco_dados.Grupo;

public class CriarGrupo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edt_nome_grupo, edt_nome_obra;
    Button btn_salvar;
    ImageView btn_voltar;
    String categoria = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_grupo);

        getSupportActionBar().hide();
        IniciarComponentes();

        Spinner spinner = findViewById(R.id.spn_categoria_grupo_CriarGrupo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btn_voltar.setOnClickListener(view -> {
            Intent intent = new Intent(CriarGrupo.this, TelaGruposAmigos.class);
            startActivity(intent);
            finish();
        });

        btn_salvar.setOnClickListener(view -> {
            BancoSQLite db = new BancoSQLite(this);
            if(db.inserirGrupo(new Grupo(
                    edt_nome_grupo.getText().toString(), categoria, edt_nome_obra.getText().toString(), ""))
            ){
                Toast.makeText(this, "Grupo criado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CriarGrupo.this, TelaGruposAmigos.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Erro ao criar o grupo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IniciarComponentes() {
        edt_nome_grupo = findViewById(R.id.edt_nome_grupo_CriarGrupo);
        edt_nome_obra = findViewById(R.id.edt_nome_obra_CriarGrupo);
        btn_salvar = findViewById(R.id.btn_salvar_CriarGrupo);
        btn_voltar = findViewById(R.id.btn_voltar_CriarGrupo);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selecionado = adapterView.getItemAtPosition(i).toString();
        if(selecionado.equals("Filme")){
            edt_nome_obra.setHint("Nome do filme");
            categoria = "Filme";
        }else if(selecionado.equals("Série")){
            edt_nome_obra.setHint("Nome da série");
            categoria = "Série";
        }else if(selecionado.equals("Livro")){
            edt_nome_obra.setHint("Nome do livro");
            categoria = "Livro";
        }else{
            edt_nome_obra.setHint("Nome do(a) cantor(a), banda, grupo ou música");
            categoria = "Música";
        }
        edt_nome_obra.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
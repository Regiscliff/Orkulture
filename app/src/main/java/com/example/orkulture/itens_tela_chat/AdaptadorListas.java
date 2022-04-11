package com.example.orkulture.itens_tela_chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orkulture.R;
import com.example.orkulture.itens_menu_inferior.TelaAmigo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorListas extends RecyclerView.Adapter<AdaptadorListas.ViewHolder> {

    Context context;
    List<String> caminhoFoto = new ArrayList<>();
    List<String> nomeUsuarios = new ArrayList<>();
    String tela;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoUsuario;
        TextView nomeUsuario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoUsuario = itemView.findViewById(R.id.img_icone_usuario);
            nomeUsuario = itemView.findViewById(R.id.txt_nome_usuario);
        }
    }

    public AdaptadorListas(Context context, List<String> caminhoFoto, List<String> nomeUsuarios, String tela){
        this.context = context;
        this.caminhoFoto = caminhoFoto;
        this.nomeUsuarios= nomeUsuarios;
        this.tela = tela;
    }

    @NonNull
    @Override
    public AdaptadorListas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_usuario, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListas.ViewHolder holder, int position) {
        if(tela.equals("ListaContatos") || tela.equals("Amigo")){
            Picasso.get().load(caminhoFoto.get(position)).into(holder.fotoUsuario);
        }else{
            holder.fotoUsuario.setBackgroundResource(R.drawable.grupo);
        }
        holder.nomeUsuario.setText(nomeUsuarios.get(position));

        holder.fotoUsuario.setOnClickListener(view -> {
            if(tela.equals("ListaContatos")){
                Intent intent = new Intent(this.context, TelaChat.class);
                context.startActivity(intent);
            }else if(tela.equals("Grupo")){
                Intent intent = new Intent(this.context, TelaChatGrupo.class);
                context.startActivity(intent);
            }else if(tela.equals("Amigo")){
                Intent intent = new Intent(this.context, TelaAmigo.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nomeUsuarios.size();
    }
}

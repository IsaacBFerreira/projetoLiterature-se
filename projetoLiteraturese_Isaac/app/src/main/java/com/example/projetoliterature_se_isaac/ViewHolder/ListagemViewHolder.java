package com.example.projetoliterature_se_isaac.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoliterature_se_isaac.R;

public class ListagemViewHolder extends RecyclerView.ViewHolder {
    public TextView t1;
    public ImageView i1;

    public ListagemViewHolder(@NonNull View itemView) {
        super(itemView);

        t1 = (TextView)itemView.findViewById(R.id.tituloLivro);
        i1 = (ImageView)itemView.findViewById(R.id.imgCapaLivro);

    }
}

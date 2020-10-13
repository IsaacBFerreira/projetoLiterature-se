package com.example.projetoliterature_se_isaac;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DadosLivro extends AppCompatActivity {

    ImageView capaLivro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_livro);

        capaLivro = (ImageView)findViewById(R.id.capaLivro);
    }


}
package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DadosLivro extends AppCompatActivity {

    ImageView capaLivro;
    Button btnQuestoes;
    TextView generoLivro, autorLivro, anoLivro, resumoLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_livro);

        //Inicializando o BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setar o Home
        bottomNavigationView.setSelectedItemId(R.id.home);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.desempenho:
                        startActivity(new Intent(getApplicationContext(), DesempenhoDoUsuario.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), ListagemLivros.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext(), Perfil.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });

        capaLivro = findViewById(R.id.capaLivro);
        generoLivro = findViewById(R.id.generoLivro);
        autorLivro = findViewById(R.id.autorLivro);
        anoLivro = findViewById(R.id.anoLivro);
        resumoLivro = findViewById(R.id.resumoLivro);
        btnQuestoes = findViewById(R.id.btnQuestoes);

        String imageLink = getIntent().getExtras().getString("imageLink");
        String autor = getIntent().getExtras().getString("autor");
        String genero = getIntent().getExtras().getString("genero");
        String ano = getIntent().getExtras().getString("ano");
        String resumo = getIntent().getExtras().getString("resumo");

        Picasso.get().load(imageLink).into(capaLivro, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnQuestoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuestoesActivity.class));
            }
        });


        generoLivro.setText(genero);
        autorLivro.setText(autor);
        anoLivro.setText(ano);
        resumoLivro.setText(resumo);
    }

}
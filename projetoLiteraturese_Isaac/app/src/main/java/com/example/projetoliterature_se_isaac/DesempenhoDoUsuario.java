package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DesempenhoDoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desempenho_do_usuario);

        //Inicializando o BottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Setar o Home
        bottomNavigationView.setSelectedItemId(R.id.desempenho);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.desempenho:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), ListagemLivros.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext(), Perfil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
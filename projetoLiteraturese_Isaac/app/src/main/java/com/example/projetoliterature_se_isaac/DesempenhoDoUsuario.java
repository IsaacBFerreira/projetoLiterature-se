package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.BlockingDeque;

public class DesempenhoDoUsuario extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    TextView txtQuestoesResolvidas, txtQuestoesCertas, txtQuestoesErradas;
    String resolvidas, certas, erradas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desempenho_do_usuario);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        txtQuestoesResolvidas = findViewById(R.id.txtQuestoesResolvidas);
        txtQuestoesCertas = findViewById(R.id.txtQuestoesCertas);
        txtQuestoesErradas = findViewById(R.id.txtQuestoesErradas);


        myRef.child("Usuarios").child(firebaseUser.getUid()).child("resolvidas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                resolvidas = snapshot.getValue().toString();
                txtQuestoesResolvidas.setText(resolvidas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Usuarios").child(firebaseUser.getUid()).child("certas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                certas = snapshot.getValue().toString();
                txtQuestoesCertas.setText(certas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("Usuarios").child(firebaseUser.getUid()).child("erradas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                erradas = snapshot.getValue().toString();
                txtQuestoesErradas.setText(erradas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext(), Perfil.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}
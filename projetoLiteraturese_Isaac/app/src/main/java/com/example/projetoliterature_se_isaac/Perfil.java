package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    private TextView textEmail, textId;
    private Button btnLogOut;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializarComponentes();
        eventoClick();
    }

    private void eventoClick(){
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                Intent it = new Intent(Perfil.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    private void inicializarComponentes(){
        textEmail = findViewById(R.id.txtPerfilEmail);
        textId = findViewById(R.id.txtPerfil_Id);
        btnLogOut = findViewById(R.id.btnLogOut);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser(){
        if(user==null){
            finish();
        }else{
            textEmail.setText("Email: " + user.getEmail());
            textId.setText("ID: "+ user.getUid());
        }
    }
}

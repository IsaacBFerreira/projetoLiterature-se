package com.example.projetoliterature_se_isaac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etSenha;
    TextView tvCadastro;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.email);
        etSenha = (EditText) findViewById(R.id.senha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvCadastro = (TextView) findViewById(R.id.txtCadastre_se);

        tvCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                    startActivity(it);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }


}

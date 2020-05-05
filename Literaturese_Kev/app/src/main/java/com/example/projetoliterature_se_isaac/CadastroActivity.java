package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha, editCSenha;
    private Button btnCadastrar, btnVoltar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();

        eventoClicks();
    }

    private void eventoClicks(){
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((editNome.equals("")) || (editEmail.equals("")) || (editSenha.equals("")) || (editCSenha.equals(""))){
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }else if(editCSenha!=editSenha){
                    Toast.makeText(CadastroActivity.this, "As senhas não coincidem!", Toast.LENGTH_LONG).show();
                }else{
                    String email = editEmail.getText().toString().trim();
                    String senha = editSenha.getText().toString().trim();
                    criarUser(email, senha);
                }
            }
        });
    }

    private void criarUser(String email, String senha){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuário cadastrado com sucesso!");
                    Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                    startActivity(it);
                    finish();
                }else{
                    alert("Erro de cadastro!");
                }
            }
        });
    }

    private void alert(String msg){
        Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    private void inicializarComponentes(){
        editNome = findViewById(R.id.et_name);
        editEmail = findViewById(R.id.et_email);
        editSenha = findViewById(R.id.et_senha);
        editCSenha = findViewById(R.id.et_csenha);
        btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}

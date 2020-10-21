package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etSenha;
    TextView tvCadastro, tvResetSenha;
    Button btnLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.email);
        etSenha = findViewById(R.id.senha);
        btnLogin = findViewById(R.id.btnLogin);
        tvCadastro = findViewById(R.id.txtCadastre_se);
        tvResetSenha = findViewById(R.id.txtResetSenha);

        tvCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        tvResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ResetarSenha.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                if(email.equals("")){
                    alert("Preencha os campos!");
                }else{
                    login(email,senha);
                }
            }
        });
    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(MainActivity.this, ListagemLivros.class);
                            startActivity(i);
                        }else{
                            alert("E-mail ou senha incorretos");
                        }
                    }
                });
    }

    private void alert(String s){
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth(); // conectar com firebase

    }
}

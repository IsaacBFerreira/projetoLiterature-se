package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    private EditText editEmail, editSenha, editCSenha;
    private Button btnCadastrar, btnVoltar;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();

        eventoClicks();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    private void eventoClicks(){
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                String confirmar_senha = editCSenha.getText().toString().trim();

                if(confirmar_senha.equals(senha)) {
                    criarUser(email, senha);
                }else{
                    alert("As senhas não coincidem!");
                }
            }
        });
    }

    private void criarUser(String email, String senha){
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String key = myRef.child("Usuarios").push().getKey();
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    myRef.child("Usuarios").child(firebaseUser.getUid()).child("resolvidas").setValue("0");
                    myRef.child("Usuarios").child(firebaseUser.getUid()).child("certas").setValue("0");
                    myRef.child("Usuarios").child(firebaseUser.getUid()).child("erradas").setValue("0");
                    alert("Usuário cadastrado com sucesso!");
                    Intent it = new Intent(CadastroActivity.this, ListagemLivros.class);
                    startActivity(it);
                    finish();
                }else{
                    alert("Verifique se o e-mail está escrito corretamente");
                }
            }
        });
    }

    private void alert(String msg){
        Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    private void inicializarComponentes(){
        editEmail = findViewById(R.id.et_email);
        editSenha = findViewById(R.id.et_senha);
        editCSenha = findViewById(R.id.et_csenha);
        btnCadastrar = findViewById(R.id.btn_cadastrar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}

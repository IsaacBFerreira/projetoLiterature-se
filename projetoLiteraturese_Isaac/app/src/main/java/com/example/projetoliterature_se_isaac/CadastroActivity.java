package com.example.projetoliterature_se_isaac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, email, senha, csenha;
    private Button btnCadastrar;
    private static String URL_CADASTRO = "http://192.168.0.1/android_cadastro_login/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        senha = findViewById(R.id.et_senha);
        csenha = findViewById(R.id.et_csenha);
        btnCadastrar = findViewById(R.id.btn_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((nome.equals("")) || (email.equals("")) || (senha.equals("")) || (csenha.equals(""))){
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos!",Toast.LENGTH_SHORT).show();
                }else{
                    Cadastrar();
                }
            }
        });
    }

    private void Cadastrar(){
        final String name = this.nome.getText().toString();
        final String email = this.email.getText().toString();
        final String password = this.senha.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CADASTRO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("sucess");

                            if(sucess.equals("1")){
                                Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(CadastroActivity.this, "Erro no Registro!" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroActivity.this, "Erro no Registro!" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

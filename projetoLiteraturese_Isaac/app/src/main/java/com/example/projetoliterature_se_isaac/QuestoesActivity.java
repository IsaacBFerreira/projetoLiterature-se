package com.example.projetoliterature_se_isaac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestoesActivity extends AppCompatActivity {

    TextView txtEnunciado;
    Button btNext, btPrev,btConfirmar;
    DatabaseReference livrosReference;
    FirebaseUser user;
    ArrayList<Questoes> questoes;
    String resposta;
    RadioGroup radioGroup;
    RadioButton txtA, txtB, txtC, txtD, txtE;

    int i = 0;
    int aux,aux2,aux3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);

        radioGroup = findViewById(R.id.rdGroup);

        txtEnunciado = findViewById(R.id.txtEnunciado);
        btConfirmar = findViewById(R.id.btnConfirmar);
        btNext = findViewById(R.id.btNext);
        btPrev = findViewById(R.id.btPrev);
        btPrev.setVisibility(View.GONE);

        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        txtC = findViewById(R.id.txtC);
        txtD = findViewById(R.id.txtD);
        txtE = findViewById(R.id.txtE);

        user = FirebaseAuth.getInstance().getCurrentUser();

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemSelecionadoRadioGroup = radioGroup.getCheckedRadioButtonId();

                if ((itemSelecionadoRadioGroup == R.id.txtA && questoes.get(i).getGabarito().equals("a")) ||
                        (itemSelecionadoRadioGroup == R.id.txtB && questoes.get(i).getGabarito().equals("b")) ||
                        (itemSelecionadoRadioGroup == R.id.txtC && questoes.get(i).getGabarito().equals("c")) ||
                        (itemSelecionadoRadioGroup == R.id.txtD && questoes.get(i).getGabarito().equals("d")) ||
                        (itemSelecionadoRadioGroup == R.id.txtE && questoes.get(i).getGabarito().equals("e"))) {
                    Toast.makeText(getApplicationContext(), "Você acertou!", Toast.LENGTH_SHORT).show();

                    livrosReference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid());
                    livrosReference.child("certas").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            aux = Integer.parseInt((String) snapshot.getValue());
                            aux++;
                            livrosReference.child("certas").setValue(Integer.toString(aux));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    livrosReference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid());
                    livrosReference.child("resolvidas").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            aux3 = Integer.parseInt((String) snapshot.getValue());
                            aux3++;
                            livrosReference.child("resolvidas").setValue(Integer.toString(aux3));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Você errou!", Toast.LENGTH_SHORT).show();

                    livrosReference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid());
                    livrosReference.child("erradas").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            aux2 = Integer.parseInt((String) snapshot.getValue());
                            aux2++;
                            livrosReference.child("erradas").setValue(Integer.toString(aux2));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    livrosReference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(user.getUid());
                    livrosReference.child("resolvidas").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            aux3 = Integer.parseInt((String) snapshot.getValue());
                            aux3++;
                            livrosReference.child("resolvidas").setValue(Integer.toString(aux3));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();

                if (i >= questoes.size() - 1) {
                    i = questoes.size() - 1;
                } else {
                    i++;
                    txtEnunciado.setText(questoes.get(i).getEnunciado());
                    txtA.setText(questoes.get(i).getA());
                    txtB.setText(questoes.get(i).getB());
                    txtC.setText(questoes.get(i).getC());
                    txtD.setText(questoes.get(i).getD());
                    txtE.setText(questoes.get(i).getE());
                }
            }
        });

        btPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    i = 0;
                } else {
                    i--;
                    txtEnunciado.setText(questoes.get(i).getEnunciado());
                    txtA.setText(questoes.get(i).getA());
                    txtB.setText(questoes.get(i).getB());
                    txtC.setText(questoes.get(i).getC());
                    txtD.setText(questoes.get(i).getD());
                    txtE.setText(questoes.get(i).getE());
                }
            }
        });

        String id = getIntent().getExtras().getString("id");
        String autor = getIntent().getExtras().getString("autor");

        questoes = new ArrayList<>();

        livrosReference = FirebaseDatabase.getInstance().getReference("Livros");

        livrosReference.child(autor).child(id).child("questoes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dado : dataSnapshot.getChildren()) {
                        String a = dado.child("a").getValue().toString();
                        String b = dado.child("b").getValue().toString();
                        String c = dado.child("c").getValue().toString();
                        String d = dado.child("d").getValue().toString();
                        String e = dado.child("e").getValue().toString();

                        String enunciado = dado.child("enunciado").getValue().toString();
                        resposta = dado.child("gabarito").getValue().toString();

                        Questoes questao = new Questoes(enunciado, a, b, c, d, e, resposta);
                        questoes.add(questao);
                    }

                    txtEnunciado.setText(questoes.get(i).getEnunciado());
                    txtA.setText(questoes.get(i).getA());
                    txtB.setText(questoes.get(i).getB());
                    txtC.setText(questoes.get(i).getC());
                    txtD.setText(questoes.get(i).getD());
                    txtE.setText(questoes.get(i).getE());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
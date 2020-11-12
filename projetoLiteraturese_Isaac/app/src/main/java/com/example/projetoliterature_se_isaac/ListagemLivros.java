package com.example.projetoliterature_se_isaac;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoliterature_se_isaac.Model.ListItem;
import com.example.projetoliterature_se_isaac.ViewHolder.ListagemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ListagemLivros extends AppCompatActivity {


    RecyclerView recyclerView, recyclerView2;
    String id;
    DatabaseReference databaseReference,databaseReference2;
    FirebaseRecyclerOptions<ListItem> options, options2;
    FirebaseRecyclerAdapter<ListItem, ListagemViewHolder> adapter, adapter2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_livros);

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
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
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

        recyclerView = findViewById(R.id.recyclerview);//Machado de Assis
        recyclerView2 = findViewById(R.id.recyclerview2);//José de Alencar
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Livros").child("Machado de Assis");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Livros").child("José de Alencar");

        options = new FirebaseRecyclerOptions.Builder<ListItem>()
                .setQuery(databaseReference, ListItem.class).build();

        options2 = new FirebaseRecyclerOptions.Builder<ListItem>()
                .setQuery(databaseReference2, ListItem.class).build();

        adapter = new FirebaseRecyclerAdapter<ListItem, ListagemViewHolder>(options) { //Machado de Assis
            @Override
            protected void onBindViewHolder(@NonNull final ListagemViewHolder holder, int position, @NonNull final ListItem model) {
                Picasso.get().load(model.getImageLink()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {
                        
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                holder.t1.setText(model.getNome());
                holder.i1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), DadosLivro.class);
                        intent.putExtra("imageLink", model.getImageLink());
                        intent.putExtra("autor", model.getAutor());
                        intent.putExtra("genero", model.getGenero());
                        intent.putExtra("ano", model.getAno());
                        intent.putExtra("resumo", model.getResumo());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ListagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);

                return new ListagemViewHolder(view);
            }
        };

        adapter2 = new FirebaseRecyclerAdapter<ListItem, ListagemViewHolder>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull ListagemViewHolder holder, int position, @NonNull final ListItem model) {
                Picasso.get().load(model.getImageLink()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                holder.t1.setText(model.getNome());
                holder.i1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), DadosLivro.class);
                        intent.putExtra("imageLink", model.getImageLink());
                        intent.putExtra("autor", model.getAutor());
                        intent.putExtra("genero", model.getGenero());
                        intent.putExtra("ano", model.getAno());
                        intent.putExtra("resumo", model.getResumo());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ListagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);

                return new ListagemViewHolder(view);
            }
        };
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //Machado de Assis
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); //Jose de Alencar
        adapter.startListening();
        adapter2.startListening();
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null){
            adapter.startListening();
            adapter2.startListening();
        }
    }

    @Override
    protected void onStop() {
        if(adapter!=null){
            adapter.stopListening();
            adapter2.stopListening();
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){
            adapter.startListening();
            adapter2.startListening();
        }
    }
}
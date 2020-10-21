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


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<ListItem> options;
    FirebaseRecyclerAdapter<ListItem, ListagemViewHolder> adapter;

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
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.perfil:
                        startActivity(new Intent(getApplicationContext(), Perfil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("CategoryBackground");

        options = new FirebaseRecyclerOptions.Builder<ListItem>()
                .setQuery(databaseReference, ListItem.class).build();

        adapter = new FirebaseRecyclerAdapter<ListItem, ListagemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ListagemViewHolder holder, int position, @NonNull ListItem model) {
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
                        Intent it = new Intent(getApplicationContext(), DadosLivro.class);
                        startActivity(it);
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null){
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        if(adapter!=null){
            adapter.stopListening();
        }
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){
            adapter.startListening();
        }
    }
}
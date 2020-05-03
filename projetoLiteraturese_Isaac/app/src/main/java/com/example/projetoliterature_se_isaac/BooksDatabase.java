package com.example.projetoliterature_se_isaac;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BooksDatabase extends RoomDatabase {

    private static BooksDatabase instance;
    public abstract BookDao bookDao();

    public static synchronized BooksDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BooksDatabase.class, "book_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private BookDao bookDao;

        private PopulateDbAsyncTask (BooksDatabase db){
            bookDao = db.bookDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookDao.insert(new Book("Os Lusíadas", "Em dez cantos, subdivididos em estrofes de oito versos, Os Lusíadas trata das viagens dos portugueses por “mares nunca dantes navegados”. Uma das características da épica é a narração de episódios históricos ou lendários de heróis que possuem qualidade superior", 1));
            bookDao.insert(new Book("Dom Casmurro", "Publicado pela primeira vez em 1899, “Dom Casmurro” é uma das grandes obras de Machado de Assis e confirma o olhar certeiro e crítico que o autor estendia sobre toda a sociedade brasileira. Também a temática do ciúme, abordada com brilhantismo nesse livro, provoca polêmicas em torno do caráter de uma das principais personagens femininas da literatura brasileira: Capitu.", 2));
            bookDao.insert(new Book("Dom Quixote", "É uma obra escrita pelo escritor espanhol Miguel de Cervantes e Saavedra (1547-1616).\n" +
                    "\n" +
                    "Trata-se de uma sátira às antigas novelas de cavalaria, considerada uma das maiores obras da literatura espanhola e um clássico da literatura universal.", 3));
            return null;
        }
    }
}

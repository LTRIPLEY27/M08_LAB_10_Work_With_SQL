package isabelcalzadilla.ioc.roomwordssample;


/*import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;*/
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
// **********    TASK 5.1 : IMPLEMENTACIÓN DE LA CLASE ABSTRACTA PARA EL DATABASE  **********

// LAS ANOTACIONES PARA LOS 'OYENTES', DEBE DECLARAR LA CLASE A LA QUE PERTENECERÁ LA DATABASE, LA VERSIÍON Y EL 'EXPOSTSCHEMA' COMO FALSE
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    // DECLARACIÓN DE LOS 'DAO' A LOS QUE ENLAZARÁ

    public abstract WordDao wordDao();

    // DEBEN DE DECLARAR EN 'SINGLETON' PARA EVITAR MULTIPLES INSTANCIAS DE LA BASE DE DATOS AL MISMO INSTANTE
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    //creación de la DATABASE CON UN BUILD
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // ESTRATEGIA DE MIGRACIÓN DE LA DATABASE
                            .fallbackToDestructiveMigration()
                            // LLAMDO AL CALLBACK CLASE INTERNA PopulateDbAsync CON OTRO HILO DE EJECUCIÓN
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    // **********    TASK 10.1 :CREACIÓN DEL ROOMDATABASE CALLBACK  **********
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // LLAMADO A NUEVA INSTANCIA DE LA CLASE INTERNA 'POPULATEDBASYNC'
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final WordDao mDao;
        String[] words = {"2001 a Space Odissey", "GodFather", "Mulholland Drive", "The Shinning", "Casablanca", "A Clockwork Orange"};

        PopulateDbAsync(WordRoomDatabase db){
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();

           /* for (String i : words){
                mDao.insert(new Word(i));
            }*/
            for( int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);
            }
            return null;
        }
    }
}

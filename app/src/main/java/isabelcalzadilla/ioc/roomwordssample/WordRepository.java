package isabelcalzadilla.ioc.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

// **********    TASK 6.1 : IMPLEMENTACIÓN DEL REPOSITORIO PARA LA LÓGICA DE LA DATABASE  **********
public class WordRepository {

    private WordDao mWorkDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application){
        // INICIALIZACIÓN DEL ROOM DATABASE DESDE EL CONSTRUCTOR
        WordRoomDatabase dataB = WordRoomDatabase.getDatabase(application);
        mWorkDao = dataB.wordDao();
        mAllWords = mWorkDao.getAllWords();
    }

    // 6.4 mÉTODO QUE OBTIENE LAS QUERYS EN HILOS DISTINTOS
    LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }

    public void delete(){
        new deleteAsynTask(mWorkDao).execute();
    }

    public void insert(Word word){
        new insertAsyncTask(mWorkDao).execute(word);
    }

    // CLASE INTERNA PARA EL 'INSERTASYNCTASK'
    private static class insertAsyncTask extends AsyncTask <Word, Void, Void>{

        private WordDao mAsyncTaskDao;

        //CONSTRUCTOR DE LA CLASE INTERNA
        insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    private static class deleteAsynTask extends AsyncTask<Void, Void, Void>{

        private WordDao mDeleteDao;

        deleteAsynTask(WordDao dao){
            mDeleteDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDeleteDao.deleteAll();
            return null;
        }
    }
}

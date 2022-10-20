package isabelcalzadilla.ioc.roomwordssample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// **********    TASK 7.1 : CREACIÓN DE LA CLASE VIEWMODEL -> ENLACE ENTRE LA INTERFAZ Y LA DATABASE **********
public class WordViewModel extends AndroidViewModel {

    // VARIABLE REFERENCIA AL REPOSITORIO
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;


    // CONSTRUCTOR QUE TOMA COMO PARAMTERO LA APLICACION
    public WordViewModel(@NonNull Application application) {
        super(application);
        // INCIALIZACIÓN DE LA REFERENCIA A REPOSITORY,
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }

    // GETTER DEL LIVEDATA
    LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        mRepository.insert(word);
    }
}

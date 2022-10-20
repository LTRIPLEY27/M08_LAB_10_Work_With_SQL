package isabelcalzadilla.ioc.roomwordssample;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// **********    TASK 3.1 : CREACIÓN DE LA interface 'dao'  **********
@Dao
public interface WordDao {

    //MÉTODO PARA INSERTAR PALABRAS
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    // **********    TASK 4.1 : inserción del LIVEDATA en WordDAO  **********
    // OBSERVADORES
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}

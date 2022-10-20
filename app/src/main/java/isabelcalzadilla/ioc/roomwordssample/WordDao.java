package isabelcalzadilla.ioc.roomwordssample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

// **********    TASK 3.1 : CREACIÓN DE LA interface 'dao'  **********
@Dao
public interface WordDao {

    //MÉTODO PARA INSERTAR PALABRAS
    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    @Delete /// verificar pues difiere del lab
    void deleteAll();


    @Query("SELECT * FROM word_table ORDER BY word ASC")
    List<Word> getAllWords();
}

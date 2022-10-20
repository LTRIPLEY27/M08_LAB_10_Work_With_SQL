package isabelcalzadilla.ioc.roomwordssample;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

// **********    TASK 2 : CREACIÓN DE LA CLASE 'WORD'  **********
// **********    TASK 2.2 : ANOTACIONES DE LA CLASE 'WORD'  **********
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }

    public String getmWord() {
        return mWord;
    }
}

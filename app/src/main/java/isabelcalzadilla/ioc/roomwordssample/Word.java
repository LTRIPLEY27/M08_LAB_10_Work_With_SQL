package isabelcalzadilla.ioc.roomwordssample;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// **********    TASK 2 : CREACIÓN DE LA CLASE 'WORD'  **********
// **********    TASK 2.2 : ANOTACIONES DE LA CLASE 'WORD'  **********
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}

}

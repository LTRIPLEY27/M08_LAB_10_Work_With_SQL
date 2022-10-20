package isabelcalzadilla.ioc.roomwordssample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

// **********    TASK 9.2 : INSTANCIACION DEL RECYCLERVIEW EN MAINACTIVITY  **********
public class MainActivity extends AppCompatActivity {

    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView recycler;
    private WordListAdapter adapter;
    // **********    TASK 11 : conexión de la interfaz con la database  **********
    // INSERCIÓN DE VARIABLE VIEW MODEL PARA EL OBSERVADOR
    private WordViewModel modelView;
    private FloatingActionButton delete, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startApp();
    }

    void startApp(){
        recycler = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //11.1 INSERCIÓN DEL VIEWMODEL  (CICLO DE VIDA)
        // SE CREA LA ASOCIACIÓN DEL VIEWMODEL CON EL CONTROLADOR (persistencia)
        modelView = ViewModelProviders.of(this).get(WordViewModel.class);

        // OBSERVER
        modelView.getmAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setmWords(words);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    /// METODO NEW WORD
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            modelView.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}
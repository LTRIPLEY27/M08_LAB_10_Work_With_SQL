package isabelcalzadilla.ioc.roomwordssample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// **********    TASK 9.2 : INSTANCIACION DEL RECYCLERVIEW EN MAINACTIVITY  **********
public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private WordListAdapter adapter;
    // **********    TASK 11 : conexión de la interfaz con la database  **********
    // INSERCIÓN DE VARIABLE VIEW MODEL PARA EL OBSERVADOR
    private WordViewModel modelView;

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
    }
}
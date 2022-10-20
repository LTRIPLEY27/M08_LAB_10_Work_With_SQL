package isabelcalzadilla.ioc.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// **********    TASK 9.1 : CREACIÓN DE ADAPTADOR DEL RECYCLER **********
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LayoutInflater mInflater;
    private List<Word> mWords;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

    public void setmWords(List<Word> mWords) {
        this.mWords = mWords;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        if(mWords == null){
            holder.wordItemView.setText("No Word");
        }
        Word current = mWords.get(position);
        holder.wordItemView.setText(current.getWord());

    }

    @Override
    public int getItemCount() {
        if(mWords != null){
            return  mWords.size();
        }
        return 0;
    }

    // CLASE INTERNA WORDVIEWHOLDER
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View view){
            super(view);
            wordItemView = view.findViewById(R.id.textView);
        }
    }
}

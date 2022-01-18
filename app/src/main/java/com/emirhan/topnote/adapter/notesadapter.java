package com.emirhan.topnote.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emirhan.topnote.databinding.RecyclerRowBinding;
import com.emirhan.topnote.model.notes;

import java.util.ArrayList;

public class notesadapter extends RecyclerView.Adapter<notesadapter.NotesHolder> {
   private ArrayList<notes> notesarrayList;

    public notesadapter(ArrayList<notes> notesarrayList) {
        this.notesarrayList = notesarrayList;
    }


    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
return new NotesHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
holder.recyclerRowBinding.subject.setText(notesarrayList.get(position).subject);
    }

    @Override
    public int getItemCount() {
        return notesarrayList.size();
    }

    class NotesHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;

        public NotesHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }

}

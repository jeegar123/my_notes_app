package com.app.mynotes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mynotes.R;
import com.app.mynotes.model.NoteItem;
import com.app.mynotes.viewholder.NoteViewHolder;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private ArrayList<NoteItem> noteItems;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public NoteAdapter(ArrayList<NoteItem> noteItems) {
        this.noteItems = noteItems;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_view, parent, false), onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        setHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return noteItems.size();
    }

    // set view
    private void setHolder(@NonNull NoteViewHolder holder, int position) {
        NoteItem noteItem = noteItems.get(position);
        holder.note.setText(noteItem.getData());
        holder.date.setText(noteItem.getDate());
    }

    public void removeNoteItem(int position) {
        noteItems.remove(position);
        notifyItemRemoved(position);
    }

    public void updateNoteItem(int position) {
        notifyItemChanged(position);
    }

    public interface OnClickListener {
        public void setOnClick(View itemView,int position);

    }

}

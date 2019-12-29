package com.app.mynotes.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mynotes.R;
import com.app.mynotes.adapter.NoteAdapter;

public class NoteViewHolder extends RecyclerView.ViewHolder {
   public TextView note, date;

    public NoteViewHolder(@NonNull final View itemView, final NoteAdapter.OnClickListener onClickListener) {
        super(itemView);
        note = itemView.findViewById(R.id.text_note_data);
        date = itemView.findViewById(R.id.text_note_today_date);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int poistion=getAdapterPosition();
                if(poistion!=RecyclerView.NO_POSITION)
                    onClickListener.setOnClick(itemView,poistion);

            }
        });
    }
}

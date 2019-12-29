package com.app.mynotes;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mynotes.adapter.NoteAdapter;
import com.app.mynotes.dialogs.EditOrDeleteNoteDialog;
import com.app.mynotes.dialogs.NoteDataDialog;
import com.app.mynotes.model.NoteItem;
import com.app.mynotes.support_class.DeleteSwipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NoteDataDialog.InputListerner, EditOrDeleteNoteDialog.InputEditListener {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    NoteDataDialog noteDataDialog;
    NoteAdapter noteAdapter;
    ArrayList<NoteItem> noteItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialized
        recyclerView = findViewById(R.id.recycle_note_list);
        floatingActionButton = findViewById(R.id.btn_add);
        noteItems = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteItems);
        setRecyclerView();
        setFloatingActionButtonListener();
        onSwipeItemView();
        setNoteAdapterListener();
    }

    public void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(noteAdapter);
    }

    public void setFloatingActionButtonListener() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDataDialog = new NoteDataDialog("Add Note");
                noteDataDialog.show(getSupportFragmentManager(), "add_dialog");
            }
        });
    }

    @Override
    public void addData(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            noteItems.add(new NoteItem(text, new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
        }
    }

    public void onSwipeItemView() {
        DeleteSwipe deleteSwipe = new DeleteSwipe(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteAdapter.removeNoteItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(deleteSwipe);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void setNoteAdapterListener() {
        noteAdapter.setOnClickListener(new NoteAdapter.OnClickListener() {
            @Override
            public void setOnClick(View itemView, int position) {
                String data = ((TextView) itemView.findViewById(R.id.text_note_data)).getText().toString();
                EditOrDeleteNoteDialog editOrDeleteNoteDialog = new EditOrDeleteNoteDialog("Edit", data);
                editOrDeleteNoteDialog.show(getSupportFragmentManager(), "edtNote");
                positionUpdateData = position;
            }
        });
    }

    int positionUpdateData;

    @Override
    public void setText(String data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            noteItems.set(positionUpdateData, new NoteItem(data, new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
            noteAdapter.updateNoteItem(positionUpdateData);
        }
    }
}

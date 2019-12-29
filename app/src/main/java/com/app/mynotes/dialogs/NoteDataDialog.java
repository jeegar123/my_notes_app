package com.app.mynotes.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.app.mynotes.R;

public class NoteDataDialog extends AppCompatDialogFragment {

    private String title;
    private EditText note_data;
    private InputListerner inputListerner;

    public NoteDataDialog(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext()).inflate(R.layout.add_message, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle(title)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String data = note_data.getText().toString();
                        if (!data.isEmpty()) {
                            inputListerner.addData(data);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        note_data = view.findViewById(R.id.edt_note_data);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {

        try {
            inputListerner = (InputListerner) context;
        } catch (Exception ex) {
//            Log.i("error",ex.getMessage());
        }

        super.onAttach(context);
    }

    public interface InputListerner {
        void addData(String text);

    }
}

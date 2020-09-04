package com.lupinesoft.roomdatabase_note_demo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    public static final String NOTE_ID = "note_id";
    public static final String UPDATE_NOTE = "update_text";
    EditText editText;
    Button buttonUpdate, buttonCancel;
    EditNoteViewModel editNoteViewModel;

    Bundle bundle;
    int noteID;
    LiveData<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editText = findViewById(R.id.edit_updateBox_ID);
        buttonUpdate = findViewById(R.id.button_actionUpdate_ID);
        buttonCancel = findViewById(R.id.button_UpdateCancel_ID);

        bundle = getIntent().getExtras();
        if(bundle!=null){
            noteID = bundle.getInt("note_id");
        }

        editNoteViewModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);
        note = editNoteViewModel.getNote(noteID);

        note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                editText.setText(note.getNote());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUpNote = editText.getText().toString();
                String strNoteId = String.valueOf(noteID);
                Intent resIntent = new Intent();
                resIntent.putExtra(NOTE_ID, strNoteId);
                resIntent.putExtra(UPDATE_NOTE, strUpNote);
                setResult(RESULT_OK, resIntent);
                finish();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

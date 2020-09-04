package com.lupinesoft.roomdatabase_note_demo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesListAdapter.OnDeleteClickListener {
    private static final int NEW_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_ACTIVITY_REQUEST_CODE = 2;
    ImageButton imageButton;
    NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = findViewById(R.id.imageButton_addNote_ID);
        recyclerView = findViewById(R.id.recyclerview_ID);
        notesListAdapter = new NotesListAdapter(this, this);
        recyclerView.setAdapter(notesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
            }
        });
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getmAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                notesListAdapter.setNotes(notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEW_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){

            Note note = new Note(0,data.getStringExtra(InsertActivity.NOTE_ADDED));
            noteViewModel.insertNote(note);
            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();

        }else if(requestCode==UPDATE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){
            Note note = new Note(
                    Integer.parseInt(data.getStringExtra(EditNoteActivity.NOTE_ID)),
                    data.getStringExtra(EditNoteActivity.UPDATE_NOTE));
            noteViewModel.update(note);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Not Save", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnDeleteClickListener(Note myNote) {
        noteViewModel.delete(myNote);
    }
}

package com.lupinesoft.roomdatabase_note_demo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public class EditNoteViewModel extends AndroidViewModel {

    private NoteDAO noteDAO;
    private NoteRoomDatabase db;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        db = NoteRoomDatabase.getDatabase(application);
        noteDAO = db.noteDAO();
    }

    public LiveData<Note> getNote(int noteID){
        return noteDAO.getNote(noteID);
    }
}

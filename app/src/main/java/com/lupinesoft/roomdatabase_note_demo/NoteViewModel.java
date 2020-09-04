package com.lupinesoft.roomdatabase_note_demo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteDAO noteDAO;
    private NoteRoomDatabase noteRoomDB;
    LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRoomDB = NoteRoomDatabase.getDatabase(application);
        noteDAO = noteRoomDB.noteDAO();
        mAllNotes = noteDAO.getAllNotes();
    }

    public void insertNote(Note note){
        new InsertAsyncTask(noteDAO).execute(note);
    }

    public void update(Note note){
        new UpdateAsynchTask(noteDAO).execute(note);
    }

    public void delete(Note note){
        new DeleteAsynchTask(noteDAO).execute(note);
    }

    LiveData<List<Note>> getmAllNotes(){
        return mAllNotes;
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDAO nNoteDao;
        public  InsertAsyncTask(NoteDAO nNoteDao){
            this.nNoteDao=nNoteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            nNoteDao.insertNote(notes[0]);
            return null;
        }
    }

    private class UpdateAsynchTask extends AsyncTask<Note, Void, Void>{
        NoteDAO mNoteDao;
        public UpdateAsynchTask(NoteDAO noteDAO) {
            this.mNoteDao=noteDAO;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsynchTask extends AsyncTask<Note, Void, Void>{
        NoteDAO mNoteDAO;
        public DeleteAsynchTask(NoteDAO noteDAO) {
            this.mNoteDAO=noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDAO.delete(notes[0]);
            return null;
        }
    }
}

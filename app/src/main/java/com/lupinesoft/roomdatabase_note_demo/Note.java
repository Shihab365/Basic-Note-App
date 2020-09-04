package com.lupinesoft.roomdatabase_note_demo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int mID;

    @ColumnInfo(name = "Name")
    private String mNote;

    public Note(int mID, String mNote) {
        this.mID = mID;
        this.mNote = mNote;
    }

    public int getID() {
        return this.mID;
    }

    public String getNote() {
        return this.mNote;
    }
}

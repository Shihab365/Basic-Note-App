package com.lupinesoft.roomdatabase_note_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteViewHolder> {

    public interface OnDeleteClickListener{
        void OnDeleteClickListener(Note myNote);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    List<Note> mNotes;
    private OnDeleteClickListener onDeleteClickListener;

    public NotesListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent ,false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int i) {
        if(mNotes!=null){
            Note note = mNotes.get(i);
            holder.setData(note.getNote(), i);
            holder.setListeners();
        }else{
            holder.noteItemView.setText("No Note!");
        }
    }

    @Override
    public int getItemCount() {
        if(mNotes!=null){
            return mNotes.size();
        }else {
            return 0;
        }
    }

    public void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView noteItemView;
        ImageButton btnUpdate, btnDelete;
        int mPosition;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.text_recy_mynote_ID);
            btnUpdate = itemView.findViewById(R.id.button_recy_edit_ID);
            btnDelete = itemView.findViewById(R.id.button_recy_delete_ID);
        }

        public void setData(String note, int position){
            noteItemView.setText(note);
            mPosition=position;
        }

        public void setListeners() {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, EditNoteActivity.class);
                    intent.putExtra("note_id", mNotes.get(mPosition).getID());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_ACTIVITY_REQUEST_CODE);
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onDeleteClickListener!=null){
                        onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPosition));
                    }
                }
            });
        }
    }
}

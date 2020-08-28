package com.example.notesforme.listeners;

import com.example.notesforme.entities.Note;

public interface NotesListener {
	void onNoteClicked(Note note, int position);
}

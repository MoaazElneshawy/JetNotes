package com.moaazelneshawy.jetnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaazelneshawy.jetnotes.db.repository.NotesRepository
import com.moaazelneshawy.jetnotes.models.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repo: NotesRepository) : ViewModel() {

    fun getAllNotes(isDeleted:Boolean) = repo.getAllNotes(isDeleted)

    fun insertNote(noteModel: NoteModel) {
        viewModelScope.launch {
            repo.insertNote(noteModel)
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            repo.deleteNote(noteModel)
        }
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            repo.updateNote(noteModel)
        }
    }

}
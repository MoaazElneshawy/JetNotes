package com.moaazelneshawy.jetnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaazelneshawy.jetnotes.db.repository.NotesRepository
import com.moaazelneshawy.jetnotes.models.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repo: NotesRepository) : ViewModel() {

    private val _selectedNote = MutableLiveData<NoteModel>(null)
    val selectedNote: LiveData<NoteModel>
        get() = _selectedNote

    fun selectNote(model: NoteModel?) {
        _selectedNote.postValue(model)
    }

    fun getAllNotes(isDeleted: Boolean) = repo.getAllNotes(isDeleted)

    fun insertNote(noteModel: NoteModel) {
        viewModelScope.launch {
            repo.insertNote(noteModel)
        }
    }

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            repo.updateNote(noteModel)
        }
    }

}
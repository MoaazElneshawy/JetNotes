package com.moaazelneshawy.jetnotes.db.repository

import com.moaazelneshawy.jetnotes.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getAllNotes(isDeleted:Boolean): Flow<List<NoteModel>>
    suspend fun insertNote(nodeModel: NoteModel)
    suspend fun updateNote(nodeModel: NoteModel)

}
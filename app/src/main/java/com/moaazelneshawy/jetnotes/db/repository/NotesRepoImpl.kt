package com.moaazelneshawy.jetnotes.db.repository

import com.moaazelneshawy.jetnotes.db.NotesDao
import com.moaazelneshawy.jetnotes.models.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepoImpl @Inject constructor(val dao: NotesDao) : NotesRepository {

    override fun getAllNotes(isDeleted: Boolean): Flow<List<NoteModel>> {
        return dao.getAllNotes(isDeleted)
    }

    override suspend fun insertNote(nodeModel: NoteModel) {
        dao.addNewNote(nodeModel)
    }

    override suspend fun deleteNote(nodeModel: NoteModel) {
        dao.deleteNote(nodeModel)
    }

    override suspend fun updateNote(nodeModel: NoteModel) {
        dao.updateNote(nodeModel)
    }
}
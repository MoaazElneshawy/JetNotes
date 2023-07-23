package com.moaazelneshawy.jetnotes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moaazelneshawy.jetnotes.models.NoteModel
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Query("SELECT * FROM NOTEMODEL where isDeleted=:isDeleted")
    fun getAllNotes(isDeleted: Boolean = false): Flow<List<NoteModel>>

    @Insert
    suspend fun addNewNote(noteModel: NoteModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(noteModel: NoteModel)
}
package com.moaazelneshawy.jetnotes.di

import android.content.Context
import com.moaazelneshawy.jetnotes.db.NotesDao
import com.moaazelneshawy.jetnotes.db.NotesDatabase
import com.moaazelneshawy.jetnotes.db.repository.NotesRepoImpl
import com.moaazelneshawy.jetnotes.db.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesNotesDatabase(@ApplicationContext context: Context): NotesDao {
        return NotesDatabase.getDatabase(context = context).notesDao()
    }

    @Provides
    fun providesNotesRepository(dao: NotesDao): NotesRepository {
        return NotesRepoImpl(dao)
    }

}
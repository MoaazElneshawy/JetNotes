package com.moaazelneshawy.jetnotes.models

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "color") val color: Int?,
    @ColumnInfo(name = "isChecked") val isChecked: Boolean? = false,
    @ColumnInfo(name = "isDeleted") val isDeleted: Boolean? = false,
    @PrimaryKey(autoGenerate = true) val id: Long? = null

)

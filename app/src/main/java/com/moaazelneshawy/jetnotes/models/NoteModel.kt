package com.moaazelneshawy.jetnotes.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") val content: String?,
    @Embedded
    val color: ColorModel?,
    @ColumnInfo(name = "isChecked") val isChecked: Boolean? = false,
    @ColumnInfo(name = "isDeleted") val isDeleted: Boolean? = false,
    @PrimaryKey(autoGenerate = true) val id: Long? = null

)

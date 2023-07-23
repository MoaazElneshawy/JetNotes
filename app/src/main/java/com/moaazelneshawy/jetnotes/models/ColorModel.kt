package com.moaazelneshawy.jetnotes.models

import android.graphics.Color


data class ColorModel(
    val colorName: String,
    val color: Int
) {
    companion object {
        val YELLOW = ColorModel("Yellow", Color.YELLOW)
        val COLORS = listOf(
            ColorModel.YELLOW,
            ColorModel("White", Color.WHITE),
            ColorModel("Black", Color.BLACK),
            ColorModel("Blue", Color.BLUE),
            ColorModel("Red", Color.RED),
            ColorModel("Cyan", Color.CYAN),
            ColorModel("Magenta", Color.MAGENTA),
            ColorModel("Gray", Color.GRAY),
            ColorModel("Green", Color.GREEN),
        )
    }
}

//class Converters {
//    @TypeConverter
//    fun fromTimestamp(model: ColorModel?): ColorModel? {
//        return model?.let { ColorModel(it.colorName,it.color) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(model: ColorModel?): ColorModel? {
//        return model
//    }
//}
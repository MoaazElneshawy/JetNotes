package com.moaazelneshawy.jetnotes.models

import android.graphics.Color


data class ColorModel(
    val colorName: String,
    val color: Int
) {
    companion object {
        val YELLOW = ColorModel("Yellow", Color.YELLOW)
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
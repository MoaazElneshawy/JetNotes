package com.moaazelneshawy.jetnotes

import com.google.gson.Gson

fun <T> String.fromJson(type: Class<T>): T {
    return Gson().fromJson(this, type)
}

fun <T> T.toJson(): String? {
    return Gson().toJson(this)
}
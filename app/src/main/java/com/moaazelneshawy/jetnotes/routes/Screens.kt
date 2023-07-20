package com.moaazelneshawy.jetnotes.routes

sealed class Screens(val routeName: String) {
    companion object {
        private const val ROUTE_NOTES = "notes"
        private const val ROUTE_TRASH = "trash"
        private const val ROUTE_NOTE_INFO = "info"
        const val NOTE_MODEL = "noteModel"
        const val IS_NEW = "isNew"
    }

    object Notes : Screens(ROUTE_NOTES)
    object NoteInfo : Screens(ROUTE_NOTE_INFO)
    object Trash : Screens(ROUTE_TRASH)

    fun withArg(vararg args: Any?): String {
        return buildString {
            append(routeName)
            args.forEach {
                if (it != null)
                    append("/$it")
                else append("?$it={$it}")
            }
        }
    }
}

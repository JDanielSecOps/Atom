package com.app.note.screens.trashScreen

import com.app.note.components.globalComponents.sortType
import com.app.note.source.roomDatabase.TodoTable

sealed interface trashScreenIntent {

    data object delete : trashScreenIntent
    data class restore(val Id: String, val status: Boolean) : trashScreenIntent
    data class deleteSingle(val todo : TodoTable) : trashScreenIntent
    data class  sorting (val sort : sortType) : trashScreenIntent
}
package com.app.note.screens.todoListScreen

import com.app.note.components.globalComponents.sortType
import com.app.note.screens.trashScreen.trashScreenIntent
import com.app.note.source.roomDatabase.TodoTable

sealed interface todoListScreenIntent {

    data class deleteSingle(val id : String) : todoListScreenIntent
    data class sort(val sortType : sortType) : todoListScreenIntent
}